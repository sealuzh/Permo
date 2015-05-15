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
 * Representation of a primitive type.
 * 
 * Primitive types are 'byte', 'short', 'int', 'long', 'float', 'double', 'boolean', and 'char'.
 */
public class PrimitiveType extends NonArrayType {

  protected PrimitiveType(final String name) {
    super(name);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getQualifiedName() {
    return getName();
  }

  /**
   * A static factory method to create a new instance of this class.
   * 
   * @param name
   *          the name of the type
   * @return a new instance of {@link PrimitiveType}
   */
  public static PrimitiveType of(final String name) {
    return new PrimitiveType(name);
  }

}
