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
package ch.uzh.ifi.seal.permo.history.git.ui.viewmodel;

import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import ch.uzh.ifi.seal.permo.app.activator.core.Activator;
import ch.uzh.ifi.seal.permo.history.common.ui.viewmodel.ProjectHistoryEventViewModel;
import ch.uzh.ifi.seal.permo.history.git.core.model.Commit;
import ch.uzh.ifi.seal.permo.history.git.core.model.Person;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.ListUtil;

/**
 * A view model providing access to an underlying {@link Commit}.
 * 
 * This is an implementation of the generic {@link ProjectHistoryEventViewModel} interface. Implementing this interface
 * allows to show the commit information in the event table.
 */
public class CommitViewModel implements ProjectHistoryEventViewModel {

  private static final String ICON__COMMIT = "icons/icon.commit.png";
  private static final String ICON__MERGE = "icons/icon.merge.png";

  private final Commit commit;

  public CommitViewModel(final Commit commit) {
    this.commit = commit;
  }

  /**
   * Returns the ID of the commit.
   * 
   * @return the ID of the commit.
   */
  public String getId() {
    return commit.getId();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LocalDateTime getDateTime() {
    return commit.getDateTime();
  }

  /**
   * Returns the author of the commit.
   * 
   * @return the author of the commit.
   */
  public Person getAuthor() {
    return commit.getAuthor();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTitle() {
    return commit.getShortMessage();
  }

  /**
   * Returns the full message of the commit.
   * 
   * @return the full message of the commit
   */
  public String getFullMessage() {
    return commit.getFullMessage();
  }

  /**
   * Checks whether the commit is a merge.
   * 
   * @return <code>true</code> if the commit is a merge, <code>false</code> otherwise
   */
  public boolean isMerge() {
    return commit.isMerge();
  }

  /**
   * Returns the list of file diffs of the commit.
   * 
   * @return the list of file diffs of the commit
   */
  public List<DiffViewModel> getFileDiffs() {
    return ListUtil.map(commit.getDiffs(), (diff) -> new DiffViewModel(diff));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage() {
    final String iconPath = isMerge() ? ICON__MERGE : ICON__COMMIT;
    return Activator.getImageDescriptor(iconPath).createImage();
  }

}
