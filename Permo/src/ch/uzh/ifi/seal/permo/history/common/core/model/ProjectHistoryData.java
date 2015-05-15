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
package ch.uzh.ifi.seal.permo.history.common.core.model;

import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.Project;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.Equals;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.HashCodes;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.TimePeriod;

/**
 * Aggregates the {@link Project} and {@link TimePeriod} that together define the input of the Project History View.
 */
public class ProjectHistoryData {

  private final Project project;
  private final TimePeriod timePeriod;

  private ProjectHistoryData(final Project project, final TimePeriod timePeriod) {
    this.project = project;
    this.timePeriod = timePeriod;
  }

  public Project getProject() {
    return project;
  }

  public TimePeriod getTimePeriod() {
    return timePeriod;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    return Equals.equals(this, obj, () -> {
      final ProjectHistoryData data = (ProjectHistoryData) obj;
      return this.getProject().equals(data.getProject()) && this.getTimePeriod().equals(data.getTimePeriod());
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return HashCodes.hashCode(getProject().hashCode(), getTimePeriod().hashCode());
  }

  public static ProjectHistoryData of(final Project project, final TimePeriod timePeriod) {
    return new ProjectHistoryData(project, timePeriod);
  }

}
