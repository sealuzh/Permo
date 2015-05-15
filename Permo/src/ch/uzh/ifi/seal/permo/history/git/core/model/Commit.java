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

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a git commit.
 */
public interface Commit {

  /**
   * Returns the ID of the commit.
   * 
   * @return the ID of the commit
   */
  public String getId();

  /**
   * Returns the point in time of the commit.
   * 
   * @return the point in time of the commit
   */
  public LocalDateTime getDateTime();

  /**
   * Returns the author of the commit.
   * 
   * @return the author of the commit
   */
  public Person getAuthor();

  /**
   * Returns the short message of the commit.
   * 
   * @return the short message of the commit
   */
  public String getShortMessage();

  /**
   * Returns the full message of the commit.
   * 
   * @return the full message of the commit
   */
  public String getFullMessage();

  /**
   * Returns the list of file diff's of the commit.
   * 
   * @return the list of file diff's of the commit
   */
  public List<Diff> getDiffs();

  /**
   * Checks whether the commit is a merge. Merge commits have to be handled different in some situations since they have
   * multiple parent commits.
   * 
   * @return <code>true</code> if the commit is a merge, <code>false</code> otherwise.
   */
  public boolean isMerge();

  /**
   * Checks whether the commit is a root commit (i.e. the first commit in a repository).
   * 
   * @return <code>true</code> if the commit is a root commit, <code>false</code> otherwise
   */
  public boolean isRoot();

}
