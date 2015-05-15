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
package ch.uzh.ifi.seal.permo.common.core.model.codeartifacts;

import ch.uzh.ifi.seal.permo.lib.core.stdlib.Equals;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.HashCodes;

/**
 * Contains the static information that is required to uniquely identify a project inside the eclipse workbench.
 */
public class Project {

  private String name;

  private Project(final String name) {
    this.name = name;
  }

  /**
   * Returns the name of the project.
   * 
   * @return the name of the project
   */
  public String getName() {
    return name;
  }

  /**
   * 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getName();
  }

  /**
   * 
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    return Equals.equals(this, obj, () -> this.getName().equals(((Project) obj).getName()));
  }

  /**
   * 
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return HashCodes.hashCode(getName().hashCode());
  }

  /**
   * A static factory method to create a new instance of this class.
   * 
   * @param name
   *          the name of the {@link Project}
   * @return a new instance of {@link Project}
   */
  public static Project of(final String name) {
    return new Project(name);
  }
}
