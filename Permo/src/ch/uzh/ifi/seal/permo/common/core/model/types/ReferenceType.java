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
 * Representation of a reference type.
 */
public class ReferenceType extends NonArrayType {

  private static final String POINT = ".";

  private final String packageName;

  private ReferenceType(final String packageName, final String name) {
    super(name);
    this.packageName = packageName;
  }

  /**
   * Returns the name of the package the type belongs to.
   * 
   * @return the name of the package the type belongs to
   */
  public String getPackageName() {
    return packageName;
  }

  /**
   * Returns the qualified name of the type.
   * 
   * @return the qualified name of the type
   */
  @Override
  public String getQualifiedName() {
    return getPackageName() + POINT + getName();
  }

  /**
   * A static factory method to create a new instance of this class.
   * 
   * @param packageName
   *          the name of the package the type belongs to
   * @param name
   *          the name of the type
   * @return a new instance of {@link ReferenceType}
   */
  public static ReferenceType of(final String packageName, final String name) {
    return new ReferenceType(packageName, name);
  }

}
