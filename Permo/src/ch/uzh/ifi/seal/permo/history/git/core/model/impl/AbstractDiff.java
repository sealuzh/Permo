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
package ch.uzh.ifi.seal.permo.history.git.core.model.impl;

import ch.uzh.ifi.seal.permo.history.git.core.model.Commit;
import ch.uzh.ifi.seal.permo.history.git.core.model.Diff;
import ch.uzh.ifi.seal.permo.history.git.core.model.DiffType;

/**
 * Abstract implementation of {@link Diff}. Provides the common functionality that is independent of any concrete
 * diff-implementation.
 */
public abstract class AbstractDiff implements Diff {

  private static final String EMPTY = "";
  private static final String SLASH = "/";

  private Commit oldCommit;
  private Commit newCommit;

  private String oldFileName;
  private String newFileName;

  public AbstractDiff(final Commit oldCommit, final Commit newCommit) {
    super();
    this.oldCommit = oldCommit;
    this.newCommit = newCommit;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Commit getOldCommit() {
    return oldCommit;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Commit getNewCommit() {
    return newCommit;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPath() {
    return getType() != DiffType.DELETE ? getNewPath() : getOldPath();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getOldPath() {
    if (getType().equals(DiffType.ADD)) {
      return EMPTY;
    }
    return getOldPathIfNotAdd();
  }

  /**
   * Returns the old path if the diff type is not equal to {@link DiffType#ADD}.
   * 
   * @return the old path if the diff type is not equal to {@link DiffType#ADD}
   */
  protected abstract String getOldPathIfNotAdd();

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getNewPath() {
    if (getType().equals(DiffType.DELETE)) {
      return EMPTY;
    }
    return getNewPathIfNotDelete();
  }

  /**
   * Returns the new path if the diff type is not equal to {@link DiffType#DELETE}.
   * 
   * @return the new path if the diff type is not equal to {@link DiffType#DELETE}
   */
  protected abstract String getNewPathIfNotDelete();

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getOldFileName() {
    if (oldFileName == null) {
      oldFileName = getFileNameFromPath(getOldPath());

    }
    return oldFileName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getNewFileName() {
    if (newFileName == null) {
      newFileName = getFileNameFromPath(getNewPath());
    }
    return newFileName;
  }

  /*
   * Distills the file name from the given path.
   */
  private String getFileNameFromPath(final String path) {
    if (path != null) {
      if (!path.isEmpty()) {
        final int lastSlash = path.lastIndexOf(SLASH);
        if (lastSlash < path.length()) {
          return path.substring(lastSlash + 1);
        }
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getOldContent() {
    if (getType().equals(DiffType.ADD)) {
      return EMPTY;
    }
    return getOldContentIfNotAdd();
  }

  /**
   * Returns the old content if the diff type is not equal to {@link DiffType#ADD}.
   * 
   * @return the old content if the diff type is not equal to {@link DiffType#ADD}
   */
  protected abstract String getOldContentIfNotAdd();

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getNewContent() {
    if (getType().equals(DiffType.DELETE)) {
      return EMPTY;
    }
    return getNewContentIfNotDelete();
  }

  /**
   * Returns the new content if the diff type is not equal to {@link DiffType#DELETE}.
   * 
   * @return the new content if the diff type is not equal to {@link DiffType#DELETE}
   */
  protected abstract String getNewContentIfNotDelete();

}
