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
package ch.uzh.ifi.seal.permo.history.main.ui.trigger;

import ch.uzh.ifi.seal.permo.common.core.events.SelectedRangeChangedEvent;
import ch.uzh.ifi.seal.permo.common.ui.trigger.AbstractViewTrigger;
import ch.uzh.ifi.seal.permo.history.common.core.model.ProjectHistoryData;
import ch.uzh.ifi.seal.permo.history.main.ui.view.ProjectHistoryView;

/**
 * A trigger (see {@link AbstractViewTrigger}) for the Project History View.
 */
public class ProjectHistoryViewTrigger extends AbstractViewTrigger<ProjectHistoryView> {

  private static final String PROJECT_HISTORY_VIEW__ID = "ch.uzh.ifi.seal.permo.views.ProjectHistory";

  private final SelectedRangeChangedEvent event;

  public ProjectHistoryViewTrigger(final SelectedRangeChangedEvent event) {
    this.event = event;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String viewId() {
    return PROJECT_HISTORY_VIEW__ID;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String secondaryViewId() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean allowMultipleViews() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean isContentAvailable() {
    return event.getTimePeriod() != null && event.getTimePeriod().isPresent();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void updateContent(final ProjectHistoryView view) {
    update(view);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void clearContent(final ProjectHistoryView view) {
    update(view);
  }

  private void update(final ProjectHistoryView view) {
    final ProjectHistoryData data = isContentAvailable() ? ProjectHistoryData.of(event.getProject(), event.getTimePeriod().get()) : null;
    view.updateView(data);
  }
}
