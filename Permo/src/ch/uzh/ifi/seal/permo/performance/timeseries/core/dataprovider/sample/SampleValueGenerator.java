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
package ch.uzh.ifi.seal.permo.performance.timeseries.core.dataprovider.sample;

import ch.uzh.ifi.seal.permo.lib.core.stdlib.Randoms;

/**
 * Provides methods to create sample values
 */
public class SampleValueGenerator {

  private static final double MIN_VALUE = 10;
  private static final double MAX_VALUE = 2000;

  private static final double NEXT_VALUE_RANGE = 2;

  private static final double CHANGE_POINT_PROBABILITY = 0.0;
  private static final double CHANGE_POINT_MIN_DIFF_RATIO = 0.25;

  /**
   * Creates a new value based on the previous value of the time series. The new value is created randomly in a
   * predefined range ({@link #NEXT_VALUE_RANGE}) from the previous value. With a given probability (
   * {@link #CHANGE_POINT_PROBABILITY}), a change point is created. In this case, the new value differs significantly
   * from the last value ({@link #CHANGE_POINT_MIN_DIFF_RATIO} * ( {@link #MAX_VALUE} - {@link #MIN_VALUE} ) ). If the
   * previous value is <code>null</code>, the new value is generated randomly in the predefined range (i.e. between
   * {@link #MIN_VALUE} and {@link #MAX_VALUE}).
   * 
   * @param previousValue
   *          the previous value
   * @return the newly created value
   */
  public Number generateValue(final Double previousValue) {
    double lowerBound;
    double upperBound;
    if (previousValue == null) {
      lowerBound = MIN_VALUE;
      upperBound = MAX_VALUE;
    }
    else if (isChangePoint()) {
      lowerBound = previousValue < middleOfRange() ? MIN_VALUE : previousValue + changePointMinDiff();
      upperBound = previousValue < middleOfRange() ? previousValue - changePointMinDiff() : MAX_VALUE;
    }
    else {
      lowerBound = Math.max(previousValue - NEXT_VALUE_RANGE, MIN_VALUE);
      upperBound = Math.min(previousValue + NEXT_VALUE_RANGE, MAX_VALUE);
    }
    return Randoms.create(lowerBound, upperBound);
  }

  private boolean isChangePoint() {
    return Randoms.create() <= CHANGE_POINT_PROBABILITY;
  }

  private double changePointMinDiff() {
    return range() * CHANGE_POINT_MIN_DIFF_RATIO;
  }

  private double range() {
    return MAX_VALUE - MIN_VALUE;
  }

  private double middleOfRange() {
    return MAX_VALUE - range() / 2;
  }

}
