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
package ch.uzh.ifi.seal.permo.history.git.core.model;

/**
 * Represents the difference of a file between two commits (old commit and new commit).
 */
public interface Diff {

  /**
   * Returns the old commit of the diff.
   * 
   * @return the old commit of the diff
   */
  public Commit getOldCommit();

  /**
   * Returns the new commit of the diff.
   * 
   * @return the new commit of the diff
   */
  public Commit getNewCommit();

  /**
   * Returns the type of diff (either {@link DiffType#ADD}, {@link DiffType#DELETE}, or {@link DiffType#MODIFY}).
   * 
   * @return the type of diff
   */
  public DiffType getType();

  /**
   * Returns the old file-path.
   * 
   * @return the old file-path
   */
  public String getOldPath();

  /**
   * Returns the new file-path.
   * 
   * @return the new file-path
   */
  public String getNewPath();

  /**
   * Returns the file-path.
   * <ul>
   * <li>If {@link #getType()} is {@link DiffType#ADD}, this corresponds to {@link #getNewPath()}.</li>
   * <li>If {@link #getType()} is {@link DiffType#DELETE}, this corresponds to {@link #getOldPath()}</li>
   * <li>If {@link #getType()} is {@link DiffType#MODIFY}, {@link #getOldPath()}, {@link #getNewPath()} and
   * {@link #getPath()} are all equal.</li>
   * </ul>
   * 
   * @return the file-path
   */
  public String getPath();

  /**
   * Returns the old file-name.
   * 
   * @return the old file-name
   */
  public String getOldFileName();

  /**
   * Returns the new file-name.
   * 
   * @return the new file-name
   */
  public String getNewFileName();

  /**
   * Returns the old content.
   * 
   * @return the old content
   */
  public String getOldContent();

  /**
   * Returns the new content.
   * 
   * @return the new content
   */
  public String getNewContent();

}
