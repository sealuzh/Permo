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

import com.google.common.eventbus.Subscribe;

/**
 * Is responsible to synchronize the Project History View with the selected time range in the Performance View. Changes
 * are communicated via the {@link SelectedRangeChangedEvent}. To receive those events, this class is registered to them
 * in the plug-in activator.
 */
public class ProjectHistoryViewSynchronizer {

  /**
   * Synchronizes the Project History View with the selected time range in the Performance View.
   * 
   * @param event
   *          the {@link SelectedRangeChangedEvent} that triggered the synchronization
   */
  @Subscribe
  public void synchronize(final SelectedRangeChangedEvent event) {
    new ProjectHistoryViewTrigger(event).execute();
  }

}
