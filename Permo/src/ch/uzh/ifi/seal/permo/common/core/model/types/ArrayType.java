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

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Representation of an array type.
 */
public class ArrayType extends Type {

  public static final String ARRAY_SUFFIX = "[]";

  private final Type elementType;

  private ArrayType(final Type elementType) {
    this.elementType = elementType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return elementType.getName() + ARRAY_SUFFIX;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getQualifiedName() {
    return elementType.getQualifiedName() + ARRAY_SUFFIX;
  }

  /**
   * Returns the {@link Type} of the elements of the current {@link ArrayType}
   * 
   * @return the {@link Type} of the elements of the current {@link ArrayType}
   */
  public Type getElementType() {
    return elementType;
  }

  /**
   * A static factory method to create an new instance of this class.
   * 
   * @param elementType
   *          the {@link Type} of the elements of the {@link ArrayType}
   * @return a new instance of {@link ArrayType}
   */
  public static ArrayType of(final Type elementType) {
    checkNotNull(elementType);
    return new ArrayType(elementType);
  }
}
