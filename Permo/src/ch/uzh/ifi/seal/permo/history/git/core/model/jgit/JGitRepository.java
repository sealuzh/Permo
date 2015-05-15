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
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.revwalk.RevCommit;

import ch.uzh.ifi.seal.permo.app.preferences.core.Preferences;
import ch.uzh.ifi.seal.permo.history.git.core.model.Commit;
import ch.uzh.ifi.seal.permo.history.git.core.model.Repository;
import ch.uzh.ifi.seal.permo.history.git.core.model.jgit.util.JGitUtil;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateTimes;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.TimePeriod;

import com.google.common.collect.Lists;

/**
 * A JGit-specific of {@link Repository}.
 */
public class JGitRepository implements Repository {

  private final org.eclipse.jgit.lib.Repository repository;

  private List<Commit> commits;

  private JGitRepository(final org.eclipse.jgit.lib.Repository repository) {
    this.repository = repository;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<Commit> getCommits(final TimePeriod timePeriod) {
    if (commits == null) {
      commits = Lists.newArrayList();
      try {
        fetchFromUpstream();
        final ObjectId idOfLatestCommit = repository.getRef(JGitUtil.getDefaultRemoteBranch(repository)).getObjectId();
        final Git git = new Git(repository);
        for (final RevCommit revCommit : git.log().add(idOfLatestCommit).call()) {
          final LocalDateTime commitTime = DateTimes.ofEpochSeconds(revCommit.getCommitTime());
          if (commitTime.isBefore(timePeriod.getStartDateTime())) {
            break;
          }
          else if (!commitTime.isAfter(timePeriod.getEndDateTime())) {
            commits.add(JGitCommit.of(repository, revCommit));
          }
        }
      }
      catch (final IOException | GitAPIException e) {}
    }
    return commits;
  }

  private void fetchFromUpstream() throws InvalidRemoteException, TransportException, GitAPIException {
    final boolean fetchAutomatically = Preferences.getStore().getBoolean(Preferences.PERMO__FETCH_AUTOMATICALLY);
    if (fetchAutomatically) {
      final Git git = new Git(repository);
      git.fetch().setRemote(JGitUtil.getDefaultRemote(repository)).call();
    }
  }

  /**
   * A static factory method to create a new instance of this class.
   * 
   * @param repository
   *          the JGit {@link org.eclipse.jgit.lib.Repository}
   * @return a new instance of {@link Repository}
   */
  public static Repository of(final org.eclipse.jgit.lib.Repository repository) {
    return new JGitRepository(repository);
  }

}
