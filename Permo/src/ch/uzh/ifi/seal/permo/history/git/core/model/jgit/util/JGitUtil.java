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
package ch.uzh.ifi.seal.permo.history.git.core.model.jgit.util;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;

import com.google.common.collect.Lists;

/**
 * Provides utility methods to deal with JGit. JGit is a Java implementation of git that provides an API to execute git
 * command inside Java code (https://eclipse.org/jgit/).
 */
public class JGitUtil {

  private static final String NO_REMOTE_BRANCH_EXCEPTION = "No remote branch exists.";
  private static final String NO_REMOTE_REPOSITORY_EXCEPTION = "No remote repository exists.";
  private static final String REMOTE_PREFIX = "refs/remotes/";
  private static final String REMOTE = "remote";
  private static final String ORIGIN = "origin";
  private static final String MASTER = "master";
  private static final String SLASH = "/";

  /**
   * Returns the default branch of a given repository. First, the default remote branch is determined with
   * {@link #getDefaultRemote(Repository)}. If the default remote reference has only one branch, this one is chosen as
   * default. If there are multiple branches, it is checked whether one of them is called 'master'. If yes, that one is
   * token. Otherwise the first branch in the list is chosen as default branch.
   * 
   * Summarized, if there are multiple remote branches and multiple branches, 'refs/remotes/origin/master' is chosen as
   * default if available.
   * 
   * @param repository
   *          the repository
   * @return the default remote branch of the given repository
   */
  public static String getDefaultRemoteBranch(final Repository repository) {
    final String remoteRef = REMOTE_PREFIX + getDefaultRemote(repository);
    final List<String> remoteBranches = getBranchesOfRemote(repository, remoteRef);
    if (remoteBranches.size() == 0) {
      throw new RuntimeException(NO_REMOTE_BRANCH_EXCEPTION);
    }
    for (final String remoteBranch : remoteBranches) {
      if (remoteBranch.endsWith(SLASH + MASTER)) {
        return remoteBranch;
      }
    }
    return remoteBranches.get(0);
  }

  /**
   * Returns the default remote reference of a given repository. If there is only one remote reference, this one is
   * chosen. If there are multiple remote references, it is checked whether one of them is called 'origin'. If yes, this
   * one is token as default. Otherwise, the first one in the list is token.
   * 
   * @param repository
   *          the repository
   * @return the default remote reference.
   */
  public static String getDefaultRemote(final Repository repository) {
    final Collection<String> remotes = getRemotes(repository);
    if (remotes.size() == 0) {
      throw new RuntimeException(NO_REMOTE_REPOSITORY_EXCEPTION);
    }
    for (final String remote : remotes) {
      if (remote.equals(ORIGIN)) {
        return remote;
      }
    }
    return remotes.iterator().next();
  }

  /**
   * Returns a list of all remote references (e.g. 'refs/remotes/origin') of the given repository.
   * 
   * @param repository
   *          the repository
   * @return the list of all remote references of the given repository.
   */
  public static Set<String> getRemotes(final Repository repository) {
    final Config storedConfig = repository.getConfig();
    return storedConfig.getSubsections(REMOTE);
  }

  /**
   * Returns a list of all branches of a given remote references.
   * 
   * @param repository
   *          the repository
   * @param remoteRef
   *          the remote reference (e.g. 'refs/remotes/origin')
   * @return the list of all branches of the given remote reference.
   */
  public static List<String> getBranchesOfRemote(final Repository repository, final String remoteRef) {
    final List<String> remoteBranches = Lists.newArrayList();
    for (final Entry<String, Ref> remoteBranch : repository.getAllRefs().entrySet()) {
      if (remoteBranch.getKey().startsWith(remoteRef)) {
        remoteBranches.add(remoteBranch.getKey());
      }
    }
    return remoteBranches;
  }
}
