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

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;

import ch.uzh.ifi.seal.permo.common.core.model.types.Type;
import ch.uzh.ifi.seal.permo.common.core.model.types.ReferenceType;
import ch.uzh.ifi.seal.permo.common.core.model.types.TypeFactory;

import com.google.common.collect.Lists;

/**
 * A factory for {@link Procedure}'s.
 */
public class ProcedureFactory {

  private TypeFactory typeFactory;

  public ProcedureFactory() {
    this.typeFactory = new TypeFactory();
  }

  /**
   * Creates a {@link Procedure} from a given {@link IMethodBinding}.
   * 
   * @param methodBinding
   *          the {@link IMethodBinding} that serves as input for the {@link Procedure} to be created
   * @return the created {@link Procedure}
   */
  public Procedure create(final IMethodBinding methodBinding) {
    final ReferenceType type = typeFactory.createReferenceType(methodBinding.getDeclaringClass()).get();
    final String name = methodBinding.getName();
    final List<Type> argumentTypes = createArgumentTypes(methodBinding);
    return Procedure.of(type, name, argumentTypes);
  }

  /*
   * Creates the {@link List} of {@link Type}'s of the arguments (parameters) of the given {@link IMethodBinding}.
   */
  private List<Type> createArgumentTypes(final IMethodBinding methodBinding) {
    final List<Type> argumentTypes = Lists.newArrayList();
    for (final ITypeBinding typeBinding : methodBinding.getParameterTypes()) {
      argumentTypes.add(typeFactory.create(typeBinding));
    }
    return argumentTypes;
  }

}
