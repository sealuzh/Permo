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
package ch.uzh.ifi.seal.permo.lib.core.stdlib;

import java.util.Random;

/**
 * Utility functionality for random values.
 */
public class Randoms {

  private Randoms() {}

  /**
   * Returns a random double between 0.0 and 1.0.
   * 
   * @return a random double between 0.0 and 1.0
   */
  public static double create() {
    return new Random().nextDouble();
  }

  /**
   * Returns a random double that between the given lower- and upper bounds.
   * 
   * @param lowerBound
   *          the lower bound
   * @param upperBound
   *          the upper bound
   * @return a random double that between the given lower- and upper bounds.
   */
  public static double create(final double lowerBound, final double upperBound) {
    return (upperBound - lowerBound) * create() + lowerBound;
  }

  /**
   * Returns a random integer that between the given lower- and upper bounds.
   * 
   * @param lowerBound
   *          the lower bound
   * @param upperBound
   *          the upper bound
   * @return a random integer that between the given lower- and upper bounds
   */
  public static int create(final int lowerBound, final int upperBound) {
    return new Random().nextInt(upperBound - lowerBound) + lowerBound;
  }

}
