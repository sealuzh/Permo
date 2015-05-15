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
package ch.uzh.ifi.seal.permo.common.core.model.base;

/**
 * A generic interface for serializers of model entities.
 * 
 * @param <E>
 *          the type of the element to be serialized/deserialized
 */
public interface Serializer<E> {

  /**
   * Serializes the given object to its {@link String} representation.
   * 
   * @param object
   *          the object to be serialized
   * @return the serialized representation of the given object
   */
  public String serialize(E object);

  /**
   * Deserializes an object from its {@link String} representation.
   * 
   * @param serializedObject
   *          the serialized object
   * @return the deserialized object
   */
  public E deserialize(String serializedObject);

}
