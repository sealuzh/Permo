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
package ch.uzh.ifi.seal.permo.history.main.ui.viewmodel;

import java.util.List;
import java.util.Optional;

import com.google.common.collect.Lists;

import ch.uzh.ifi.seal.permo.common.ui.viewmodel.AbstractStatefulViewModel;
import ch.uzh.ifi.seal.permo.history.common.core.model.ProjectHistoryData;
import ch.uzh.ifi.seal.permo.history.common.ui.viewmodel.ProjectHistoryEventViewModel;
import ch.uzh.ifi.seal.permo.history.git.core.model.Commit;
import ch.uzh.ifi.seal.permo.history.git.core.model.Repository;
import ch.uzh.ifi.seal.permo.history.git.core.model.jgit.JGitService;
import ch.uzh.ifi.seal.permo.history.git.ui.viewmodel.CommitViewModel;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.ListUtil;

/**
 * The root view model of the Project History View. Provides access to the data from the model.
 */
public class ProjectHistoryViewViewModel extends AbstractStatefulViewModel {

  public static final String PROPERTY__PROJECT_HISTORY_DATA = "projectHistoryData";
  public static final String PROPERTY__EVENTS = "events";
  public static final String PROPERTY__SELECTED_EVENT = "selectedEvent";

  private ProjectHistoryData projectHistoryData;
  private List<ProjectHistoryEventViewModel> events;
  private ProjectHistoryEventViewModel selectedEvent;

  public ProjectHistoryViewViewModel() {}

  /**
   * Returns the underlying data that is visualized in the Project History View.
   * 
   * @return the underlying data that is visualized in the Project History View
   */
  public ProjectHistoryData getProjectHistoryData() {
    return projectHistoryData;
  }

  /**
   * Sets the data to be visualized in the Project History View.
   * 
   * @param projectHistoryData
   *          the data to be visualized
   */
  public void setProjectHistoryData(final ProjectHistoryData projectHistoryData) {
    firePropertyChange(PROPERTY__PROJECT_HISTORY_DATA, this.projectHistoryData, this.projectHistoryData = projectHistoryData);
    updateEvents();
  }

  /**
   * Checks whether the Project History View is empty (i.e. no data is shown).
   * 
   * @return <code>true</code> if the Project History View is empty, <code>false</code> otherwise
   */
  public boolean isEmpty() {
    return projectHistoryData == null;
  }

  /**
   * Returns the list of displayed events.
   * 
   * @return the list of displayed events
   */
  public List<ProjectHistoryEventViewModel> getEvents() {
    return events;
  }

  /**
   * Sets the list of displayed events.
   * 
   * @param events
   *          the new list of displayed events.
   */
  public void setEvents(final List<ProjectHistoryEventViewModel> events) {
    clearSelectedEvent();
    firePropertyChange(PROPERTY__EVENTS, this.events, this.events = events);
  }

  /**
   * Returns the currently selected event in the event table.
   * 
   * @return the currently selected event in the event table
   */
  public ProjectHistoryEventViewModel getSelectedEvent() {
    return this.selectedEvent;
  }

  /**
   * Sets the currently selected event in the event table.
   * 
   * @param selectedEvent
   *          the currently selected event
   */
  public void setSelectedEvent(final ProjectHistoryEventViewModel selectedEvent) {
    firePropertyChange(PROPERTY__SELECTED_EVENT, this.selectedEvent, this.selectedEvent = selectedEvent);
  }

  /**
   * Clears the current selection in the event table.
   */
  public void clearSelectedEvent() {
    setSelectedEvent(null);
  }

  private void updateEvents() {

    final List<ProjectHistoryEventViewModel> events = Lists.newArrayList();
    if (!isEmpty()) {
      final Optional<Repository> repo = new JGitService().getRepository(getProjectHistoryData().getProject());
      if (repo.isPresent()) {
        final List<Commit> commits = repo.get().getCommits(getProjectHistoryData().getTimePeriod());
        final List<CommitViewModel> commitViewModels = ListUtil.map(commits, (commit) -> new CommitViewModel(commit));
        events.addAll(commitViewModels);
      }
    }
    setEvents(events);
  }

}
