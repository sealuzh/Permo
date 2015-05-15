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
package ch.uzh.ifi.seal.permo.history.git.core.model.impl;

import ch.uzh.ifi.seal.permo.history.git.core.model.Person;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.Equals;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.HashCodes;

/**
 * Implementation of {@link Person}.
 */
public class PersonImpl implements Person {

  private static final String TO_STRING_PATTERN = "%s <%s>";

  private final String name;
  private final String emailAddress;

  private PersonImpl(final String name, final String emailAddress) {
    this.name = name;
    this.emailAddress = emailAddress;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format(TO_STRING_PATTERN, getName(), getEmailAddress());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    return Equals.equals(this, obj, () -> {
      final Person person = (Person) obj;
      return this.getName().equals(person.getName()) && this.getEmailAddress().equals(person.getEmailAddress());
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return HashCodes.hashCode(getName().hashCode(), getEmailAddress().hashCode());
  }

  /**
   * Static factory method to create an instance of this class.
   * 
   * @param name
   *          the name of the person
   * @param emailAddress
   *          the email address of the person
   * @return a new instance of {@link PersonImpl}
   */
  public static Person of(final String name, final String emailAddress) {
    return new PersonImpl(name, emailAddress);
  }

}
