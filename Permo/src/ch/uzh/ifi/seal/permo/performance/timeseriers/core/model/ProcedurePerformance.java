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

import java.util.List;

import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.Procedure;

/**
 * An extension of the generic {@link TimeSeries} entity that is related to performance monitoring of {@link Procedure}
 * 's.
 */
public class ProcedurePerformance extends TimeSeries {

  private Procedure procedure;

  public ProcedurePerformance(final Procedure procedure, final Unit unit, final List<Measurement> measurements) {
    super(procedure.toShortString(), unit, measurements);
    this.procedure = procedure;
  }

  /**
   * Returns the monitored {@link Procedure}.
   * 
   * @return the monitored {@link Procedure}
   */
  public Procedure getProcedure() {
    return procedure;
  }

}
