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

import java.util.function.Supplier;

/**
 * This class provides a method to implement an efficient equals method. The underlying principle is adopted from the
 * book "Effective Java" from Joshua Bloch (Item 8: Obey the general contract when overriding equals).
 */
public class Equals {

  private Equals() {}

  /**
   * An efficient implementation to compare the equality of two objects.
   * 
   * @param comparingObject
   *          the object executing the equals method
   * @param comparedObject
   *          the object that is passed as parameter to the equal method
   * @param equalityMethod
   *          the function that specifies the equality based on the objects attributes.
   * @return
   */
  public static boolean equals(final Object comparingObject, final Object comparedObject, final Supplier<Boolean> equalityMethod) {
    if (comparingObject == comparedObject) {
      return true;
    }
    if (comparingObject.getClass().isInstance(comparedObject)) {
      return equalityMethod.get();
    }
    return false;
  }

}
