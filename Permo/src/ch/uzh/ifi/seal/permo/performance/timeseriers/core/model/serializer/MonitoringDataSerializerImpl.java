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
package ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.serializer;

import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.ProcedureSerializer;
import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.ProcedureSerializerImpl;
import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.ProjectSerializer;
import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.ProjectSerializerImpl;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.MonitoringData;

/**
 * Implementation of {@link MonitoringDataSerializer}.
 */
public class MonitoringDataSerializerImpl implements MonitoringDataSerializer {

  private static final String SEPARATOR = "@";

  ProjectSerializer projectSerializer;
  ProcedureSerializer procedureSerializer;

  public MonitoringDataSerializerImpl() {
    this.projectSerializer = new ProjectSerializerImpl();
    this.procedureSerializer = new ProcedureSerializerImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String serialize(final MonitoringData monitoringData) {
    return projectSerializer.serialize(monitoringData.getProject()) + SEPARATOR + procedureSerializer.serialize(monitoringData.getProcedure());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MonitoringData deserialize(final String serializedMonitoringData) {
    final int separatorPosition = serializedMonitoringData.indexOf(SEPARATOR);
    final String serializedProject = serializedMonitoringData.substring(0, separatorPosition);
    final String serializedProcedure = serializedMonitoringData.substring(separatorPosition + 1);
    return MonitoringData.of(projectSerializer.deserialize(serializedProject), procedureSerializer.deserialize(serializedProcedure));
  }
}
