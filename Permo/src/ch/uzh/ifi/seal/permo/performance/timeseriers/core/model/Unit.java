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

import ch.uzh.ifi.seal.permo.lib.core.stdlib.Equals;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.HashCodes;

/**
 * Represents the unit of a time series.
 */
public class Unit {

  private final String name;
  private final String abbreviation;

  public Unit(final String name, final String abbreviation) {
    this.name = name;
    this.abbreviation = abbreviation;
  }

  /**
   * Returns the name of the unit.
   * 
   * @return the name of the unit
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the abbreviation of the unit.
   * 
   * @return the abbreviation of the unit
   */
  public String getAbbreviation() {
    return abbreviation;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    return Equals.equals(this, obj, () -> {
      final Unit unit = (Unit) obj;
      return unit.getName().equals(this.getName()) && unit.getAbbreviation().equals(this.getAbbreviation());
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return HashCodes.hashCode(getName().hashCode(), getAbbreviation().hashCode());
  }

}
