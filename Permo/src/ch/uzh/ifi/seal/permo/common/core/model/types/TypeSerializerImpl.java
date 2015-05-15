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

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Implementation of {@link TypeSerializer}.
 */
public class TypeSerializerImpl implements TypeSerializer {

  private static final String POINT = ".";
  private static final List<String> PRIMITIVE_TYPES = Lists.newArrayList("byte", "short", "int", "long", "float", "double", "boolean", "char");

  /**
   * {@inheritDoc}
   */
  @Override
  public String serialize(final Type type) {
    return type.getQualifiedName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Type deserialize(final String serializedType) {
    final boolean isArray = serializedType.endsWith(ArrayType.ARRAY_SUFFIX);
    if (isArray) {
      final String serializedElementType = serializedType.substring(0, serializedType.length() - ArrayType.ARRAY_SUFFIX.length());
      return ArrayType.of(deserialize(serializedElementType));
    }
    final int lastPoint = serializedType.lastIndexOf(POINT);
    if (lastPoint == -1) {
      if (PRIMITIVE_TYPES.contains(serializedType)) {
        return PrimitiveType.of(serializedType);
      }
      return GenericType.of(serializedType);
    }
    final String packageName = serializedType.substring(0, lastPoint);
    final String name = serializedType.substring(lastPoint + 1);
    return ReferenceType.of(packageName, name);
  }
}
