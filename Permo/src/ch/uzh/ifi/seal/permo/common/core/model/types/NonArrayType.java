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
package ch.uzh.ifi.seal.permo.common.core.model.types;

/**
 * An abstract base implementation for all concrete non-array types.
 */
public abstract class NonArrayType extends Type {

  private final String name;

  protected NonArrayType(final String name) {
    this.name = name;
  }

  /**
   * Returns the name of the type.
   * 
   * @return the name of the type
   */
  @Override
  public String getName() {
    return name;
  }

}
