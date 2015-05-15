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

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import ch.uzh.ifi.seal.permo.common.core.model.types.Type;
import ch.uzh.ifi.seal.permo.common.core.model.types.ReferenceType;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.Equals;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.HashCodes;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

/**
 * Contains the static information that is required to uniquely identify a procedure inside a project.
 * 
 * The term procedure is used to describe the superset of constructors and methods.
 */
public class Procedure {

  private static final String TO_STRING_PATTERN = "%s:%s(%s)";
  private static final String COMMA = ",";

  private final ReferenceType type;
  private final String name;
  private final List<Type> argumentTypes;

  private Procedure(final ReferenceType type, final String name, final List<Type> argumentTypes) {
    this.type = type;
    this.name = name;
    this.argumentTypes = ImmutableList.copyOf(argumentTypes);
  }

  /**
   * Returns the type the procedure belongs to.
   * 
   * @return the type the procedure belongs to
   */
  public ReferenceType getType() {
    return type;
  }

  /**
   * Returns the name of the procedure.
   * 
   * @return the name of the procedure
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the {@link List} of the {@link Type}'s of the arguments of the procedure.
   * 
   * @return the {@link List} of the {@link Type}'s of the arguments of the procedure
   */
  public List<Type> getArgumentTypes() {
    return argumentTypes;
  }

  /**
   * Returns a textual representation of the procedure containing the qualified names of the belonging type and the
   * argument types. This is a unique qualifier for the procedure.
   * 
   * @return a textual representation of the procedure containing the qualified names of the belonging type and the
   *         argument types
   */
  public String toLongString() {
    return toString((type) -> type.getQualifiedName());
  }

  /**
   * Returns a textual representation of the procedure containing the simple names of the belonging type and the
   * argument types.
   * 
   * @return a textual representation of the procedure containing the simple names of the belonging type and the
   *         argument types
   */
  public String toShortString() {
    return toString((type) -> type.getName());
  }

  private String toString(final Function<? super Type, ? extends String> typeToName) {
    final String argumentTypes = Joiner.on(COMMA).join(getArgumentTypes().stream().map(typeToName).collect(Collectors.toList()));
    return String.format(TO_STRING_PATTERN, typeToName.apply(getType()), getName(), argumentTypes);
  }

  /**
   * 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toLongString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    return Equals.equals(this, obj, () -> {
      final Procedure procedure = (Procedure) obj;
      return this.getType().equals(procedure.getType()) && this.getName().equals(procedure.getName()) && this.getArgumentTypes().equals(procedure.getArgumentTypes());
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return HashCodes.hashCode(getType().hashCode(), getName().hashCode(), getArgumentTypes().hashCode());
  }

  /**
   * A static factory method to create a new instance of this class.
   * 
   * @param type
   *          the {@link Type} the procedure belongs to
   * @param name
   *          the name of the procedure
   * @param argumentTypes
   *          the {@link List} of the {@link Type}'s of the arguments of the procedure
   * @return a new instance of {@link Procedure}
   */
  public static Procedure of(final ReferenceType type, final String name, final List<Type> argumentTypes) {
    return new Procedure(type, name, argumentTypes);
  }

}
