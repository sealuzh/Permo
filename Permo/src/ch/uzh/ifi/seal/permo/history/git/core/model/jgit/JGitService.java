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

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.Project;
import ch.uzh.ifi.seal.permo.history.git.core.model.GitService;
import ch.uzh.ifi.seal.permo.history.git.core.model.Repository;

/**
 * Implementation of {@link GitService} that connects to the local git repository using JGit.
 */
public class JGitService implements GitService {

  private static final String GIT_FOLDER = ".git";

  /**
   * {@inheritDoc}
   */
  @Override
  public Optional<Repository> getRepository(final Project project) {
    final IProject eclipseProject = getEclipseProject(project);
    if (eclipseProject.exists()) {
      try {
        final Optional<File> gitFolder = getGitFolder(eclipseProject);
        if (gitFolder.isPresent()) {
          final org.eclipse.jgit.lib.Repository repository = new FileRepositoryBuilder().setGitDir(gitFolder.get()).readEnvironment().findGitDir().build();
          return Optional.of(JGitRepository.of(repository));
        }
      }
      catch (final IOException e) {}
    }
    return Optional.empty();
  }

  /*
   * Returns the corresponding Eclipse project of the given Permo-project.
   */
  private IProject getEclipseProject(final Project project) {
    final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
    return root.getProject(project.getName());
  }

  /*
   * Returns the git folder (.git folder in the root folder of the repository) of the given project.
   */
  private Optional<File> getGitFolder(final IProject project) {
    project.getFullPath().toOSString();
    return getGitFolder(project.getLocation().toFile());
  }

  /*
   * Returns the git-folder (.git folder in the root folder of the repository) of the given repository.
   */
  private Optional<File> getGitFolder(final File file) {
    if (file != null && file.isDirectory()) {
      final File gitFolder = new File(file, GIT_FOLDER);
      if (gitFolder.exists()) {
        return Optional.of(gitFolder);
      }
      final File parent = file.getParentFile();
      return getGitFolder(parent);
    }
    return Optional.empty();
  }
}
