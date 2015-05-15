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

import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;

import ch.uzh.ifi.seal.permo.history.git.core.model.Commit;
import ch.uzh.ifi.seal.permo.history.git.core.model.Diff;
import ch.uzh.ifi.seal.permo.history.git.core.model.DiffType;
import ch.uzh.ifi.seal.permo.history.git.core.model.impl.AbstractDiff;

/**
 * A JGit-specific implementation of {@link Diff}.
 */
public class JGitDiff extends AbstractDiff implements Diff {

  private static final String EMPTY = "";

  private final Repository repository;
  private final DiffEntry diffEntry;

  private DiffType type;
  private String oldPath;
  private String newPath;
  private String oldContent;
  private String newContent;

  private JGitDiff(final Commit oldCommit, final Commit newCommit, final Repository repository, final DiffEntry diffEntry) {
    super(oldCommit, newCommit);
    this.repository = repository;
    this.diffEntry = diffEntry;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DiffType getType() {
    if (type == null) {
      switch (diffEntry.getChangeType()) {
        case ADD:
          type = DiffType.ADD;
          break;
        case COPY:
          type = DiffType.ADD;
          break;
        case DELETE:
          type = DiffType.DELETE;
          break;
        case MODIFY:
          type = DiffType.MODIFY;
          break;
        case RENAME:
          type = DiffType.MODIFY;
          break;
        default:
          type = null;
          break;
      }
    }
    return type;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getOldPathIfNotAdd() {
    if (oldPath == null) {
      oldPath = diffEntry.getOldPath();
    }
    return oldPath;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getNewPathIfNotDelete() {
    if (newPath == null) {
      newPath = diffEntry.getNewPath();
    }
    return newPath;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getOldContentIfNotAdd() {
    if (oldContent == null) {
      oldContent = getContent(diffEntry.getOldId().toObjectId());
    }
    return oldContent;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getNewContentIfNotDelete() {
    if (newContent == null) {
      newContent = getContent(diffEntry.getNewId().toObjectId());
    }
    return newContent;
  }

  /*
   * Fetches the content of the file with the given ID.
   */
  private String getContent(final ObjectId id) {
    try {
      final ObjectLoader loader = repository.open(id);
      return new String(loader.getBytes());
    }
    catch (final IOException e) {
      return EMPTY;
    }
  }

  /**
   * A static factory method to create a new instance of this class.
   * 
   * @param oldCommit
   *          the old commit
   * @param newCommit
   *          the new commit
   * @param repository
   *          the repository
   * @param diffEntry
   *          the JGit {@link DiffEntry}
   * @return a new instance of {@link Diff}
   */
  public static Diff of(final Commit oldCommit, final Commit newCommit, final Repository repository, final DiffEntry diffEntry) {
    return new JGitDiff(oldCommit, newCommit, repository, diffEntry);
  }

}
