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

import java.time.LocalDateTime;

import ch.uzh.ifi.seal.permo.lib.core.stdlib.Equals;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.HashCodes;

/**
 * This class stores a measured value together with the point in time it has been measured.
 */
public class Measurement {

  private final LocalDateTime dateTime;
  private final Number value;

  private Measurement(final LocalDateTime dateTime, final Number value) {
    this.dateTime = dateTime;
    this.value = value;
  }

  /**
   * Returns the point in time of the measurement.
   * 
   * @return the point in time of the measurement
   */
  public LocalDateTime getDateTime() {
    return dateTime;
  }

  /**
   * Returns the value of the measurement.
   * 
   * @return the value of the measurement
   */
  public Number getValue() {
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    return Equals.equals(this, obj, () -> {
      final Measurement measurement = (Measurement) obj;
      return measurement.getDateTime().equals(this.getDateTime()) && measurement.getValue().equals(this.getValue());
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return HashCodes.hashCode(getDateTime().hashCode(), getValue().hashCode());
  }

  /**
   * Static factory method to create a new instance of this class.
   * 
   * @param dateTime
   *          the {@link LocalDateTime} of the measurement
   * @param value
   *          the measured value
   * @return a new instance of {@link Measurement}
   */
  public static Measurement of(final LocalDateTime dateTime, final Number value) {
    return new Measurement(dateTime, value);
  }

}
