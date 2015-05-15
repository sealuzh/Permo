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

import java.util.Optional;

import org.eclipse.jdt.core.dom.ITypeBinding;

/**
 * A factory to create {@link Type}'s.
 */
public class TypeFactory {

  private TypeSerializer typeSerializer;

  public TypeFactory() {
    this.typeSerializer = new TypeSerializerImpl();
  }

  /**
   * Creates a new {@link Type} of the given {@link ITypeBinding}. Determines which concrete {@link Type} is required.
   * 
   * @param typeBinding
   *          the {@link ITypeBinding} that serves as input {@link Type} to be created
   * @return the created {@link Type}
   */
  public Type create(final ITypeBinding typeBinding) {
    if (typeBinding.isArray()) {
      return typeSerializer.deserialize(typeBinding.getQualifiedName());
    }
    else if (typeBinding.isTypeVariable()) {
      return GenericType.of(typeBinding.getName());
    }
    else if (typeBinding.isPrimitive()) {
      return PrimitiveType.of(typeBinding.getName());
    }
    return createReferenceType(typeBinding).get();
  }

  /**
   * Creates a new {@link ReferenceType} of the given {@link ITypeBinding}.
   * 
   * @param typeBinding
   *          the {@link ITypeBinding} that serves as input {@link ReferenceType} to be created
   * @return
   */
  public Optional<ReferenceType> createReferenceType(final ITypeBinding typeBinding) {
    return !typeBinding.isPrimitive() ? Optional.of(ReferenceType.of(typeBinding.getPackage().getName(), typeBinding.getName())) : Optional.empty();
  }

}
