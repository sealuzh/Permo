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
package ch.uzh.ifi.seal.permo.common.core.events;

import java.time.LocalDateTime;
import java.util.Optional;

import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.Project;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateConverter;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.Dates;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.TimePeriod;

/**
 * This event is raised by the Performance View to inform the application that the selected range has changed.
 */
public class SelectedRangeChangedEvent {

  private Project project;
  private Optional<TimePeriod> timePeriod;

  public SelectedRangeChangedEvent(final Project project, final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
    this.project = project;
    this.timePeriod = isRange(startDateTime, endDateTime) ? Optional.of(TimePeriod.of(startDateTime, endDateTime)) : Optional.empty();
  }

  private boolean isRange(final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
    return startDateTime != null && Dates.isSet(DateConverter.convert(startDateTime)) && endDateTime != null && Dates.isSet(DateConverter.convert(endDateTime));
  }

  /**
   * Returns the {@link Project} related to the current selection.
   * 
   * @return the {@link Project} related to the current selection or null, if no time period is selected
   */
  public Project getProject() {
    return project;
  }

  /**
   * Returns the currently selected {@link TimePeriod}.
   * 
   * @return the currently selected {@link TimePeriod} or {@link Optional#empty()} if no time period is selected
   */
  public Optional<TimePeriod> getTimePeriod() {
    return timePeriod;
  }

}
