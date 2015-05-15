/*******************************************************************************
 * Copyright 2015 Software Evolution and Architecture Lab, University of Zurich
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ch.uzh.ifi.seal.permo.history.git.core.model.jgit;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.EmptyTreeIterator;

import ch.uzh.ifi.seal.permo.history.git.core.model.Commit;
import ch.uzh.ifi.seal.permo.history.git.core.model.Diff;
import ch.uzh.ifi.seal.permo.history.git.core.model.Person;
import ch.uzh.ifi.seal.permo.history.git.core.model.impl.PersonImpl;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateTimes;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.ListUtil;

import com.google.common.collect.Lists;

/**
 * A JGit-specific implementation of {@link Commit}.
 */
public class JGitCommit implements Commit {

  private final RevCommit revCommit;
  private final Repository repository;

  private String id;
  private LocalDateTime dateTime;
  private Person author;
  private String shortMessage;
  private String fullMessage;

  private List<Diff> diffs;

  private JGitCommit(final Repository repository, final RevCommit revCommit) {
    this.repository = repository;
    this.revCommit = revCommit;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {
    if (id == null) {
      id = getAbbreviatedHash(7);
    }
    return id;
  }

  private String getAbbreviatedHash(final int lenght) {
    return revCommit.getId().name().substring(0, lenght);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LocalDateTime getDateTime() {
    if (dateTime == null) {
      dateTime = DateTimes.ofEpochSeconds(revCommit.getCommitTime());
    }
    return dateTime;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Person getAuthor() {
    if (author == null) {
      author = PersonImpl.of(revCommit.getAuthorIdent().getName(), revCommit.getAuthorIdent().getEmailAddress());
    }
    return author;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getShortMessage() {
    if (shortMessage == null) {
      shortMessage = revCommit.getShortMessage();
    }
    return shortMessage;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getFullMessage() {
    if (fullMessage == null) {
      fullMessage = revCommit.getFullMessage();
    }
    return fullMessage;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Diff> getDiffs() {
    if (diffs == null) {
      diffs = isMerge() ? Lists.newArrayList() : createDiffs();
    }
    return diffs;
  }

  /*
   * Iterates over the commit tree and creates respective file diffs.
   */
  private List<Diff> createDiffs() {
    try {
      final RevCommit parent = isRoot() ? null : revCommit.getParent(0);
      final AbstractTreeIterator newTreeIt = getTreeIteratorOfCommit(repository, revCommit);
      final AbstractTreeIterator oldTreeIt = isRoot() ? new EmptyTreeIterator() : getTreeIteratorOfCommit(repository, parent);
      final List<DiffEntry> diffEntries = new Git(repository).diff().setNewTree(newTreeIt).setOldTree(oldTreeIt).call();
      final Commit parentCommit = isRoot() ? null : JGitCommit.of(repository, parent);
      return ListUtil.map(diffEntries, (diffEntry) -> JGitDiff.of(parentCommit, this, repository, diffEntry));
    }
    catch (final IOException | GitAPIException e) {
      return Lists.newArrayList();
    }
  }

  /*
   * Returns an iterator to iterate over the the diff entries of a commit.
   */
  private AbstractTreeIterator getTreeIteratorOfCommit(final Repository repo, final RevCommit commit) throws IncorrectObjectTypeException, IOException {
    final ObjectId head = commit.getTree().getId();
    final ObjectReader reader = repo.newObjectReader();
    final CanonicalTreeParser treeIt = new CanonicalTreeParser();
    treeIt.reset(reader, head);
    return treeIt;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMerge() {
    return revCommit.getParentCount() > 1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isRoot() {
    return revCommit.getParentCount() == 0;
  }

  /**
   * A static factory method to create a new instance of this class.
   * 
   * @param repo
   *          the {@link Repository} of the commit
   * @param revCommit
   *          the {@link RevCommit} fetched with JGit
   * @return a new instance of {@link JGitCommit}
   */
  public static JGitCommit of(final Repository repo, final RevCommit revCommit) {
    return new JGitCommit(repo, revCommit);
  }

}
