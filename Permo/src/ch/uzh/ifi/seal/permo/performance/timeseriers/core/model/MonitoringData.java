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
package ch.uzh.ifi.seal.permo.performance.timeseriers.core.model;

import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.Procedure;
import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.Project;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.Equals;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.HashCodes;

/**
 * Aggregates the {@link Project} and {@link Procedure} that together define the entity that shall be monitored and
 * therefore the input of the Performance View.
 */
public class MonitoringData {

  private final Project project;
  private final Procedure procedure;

  private MonitoringData(final Project project, final Procedure procedure) {
    this.project = project;
    this.procedure = procedure;
  }

  public Project getProject() {
    return project;
  }

  public Procedure getProcedure() {
    return procedure;
  }

  @Override
  public boolean equals(final Object obj) {
    Equals.equals(this, obj, () -> {
      final MonitoringData monitoringData = (MonitoringData) obj;
      return this.getProject().equals(monitoringData.getProject()) && this.getProcedure().equals(monitoringData.getProcedure());
    });
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return HashCodes.hashCode(getProject().hashCode(), getProcedure().hashCode());
  }

  public static MonitoringData of(final String projectName, final Procedure procedure) {
    return of(Project.of(projectName), procedure);
  }

  public static MonitoringData of(final Project project, final Procedure procedure) {
    return new MonitoringData(project, procedure);
  }

}
