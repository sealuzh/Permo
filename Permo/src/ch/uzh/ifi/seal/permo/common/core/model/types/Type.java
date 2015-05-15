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

import ch.uzh.ifi.seal.permo.lib.core.stdlib.Equals;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.HashCodes;

/**
 * An abstract base implementation to represent a type.
 */
public abstract class Type {

  /**
   * Returns the name of the type.
   * 
   * @return the name of the type
   */
  public abstract String getName();

  /**
   * Returns the qualified name of the type.
   * 
   * @return the qualified name of the type
   */
  public abstract String getQualifiedName();

  /**
   * 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getQualifiedName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    return Equals.equals(this, obj, () -> this.getQualifiedName().equals(((Type) obj).getQualifiedName()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return HashCodes.hashCode(getQualifiedName().hashCode());
  }
}
