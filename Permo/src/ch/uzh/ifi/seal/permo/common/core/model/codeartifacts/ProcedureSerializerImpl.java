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
import java.util.stream.Collectors;

import ch.uzh.ifi.seal.permo.common.core.model.types.ReferenceType;
import ch.uzh.ifi.seal.permo.common.core.model.types.Type;
import ch.uzh.ifi.seal.permo.common.core.model.types.TypeSerializer;
import ch.uzh.ifi.seal.permo.common.core.model.types.TypeSerializerImpl;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * Implementation of {@link ProcedureSerializer}.
 */
public class ProcedureSerializerImpl implements ProcedureSerializer {

  private static final String COMMA = ",";
  private static final String SEMICOLON = ";";
  private static final String DESERIALIZATION_ERROR_MSG_PATTERN = "Error trying to deserialize procedure. Input was: %s.";
  private static final int EXPECTED_NUMBER_OF_SERIALIZATION_PARTS = 3;

  private TypeSerializer typeSerializer;

  public ProcedureSerializerImpl() {
    typeSerializer = new TypeSerializerImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String serialize(final Procedure procedure) {
    final String serializedArguements = Joiner.on(COMMA).join(procedure.getArgumentTypes().stream().map((type) -> typeSerializer.serialize(type)).collect(Collectors.toList()));
    final String serializedType = typeSerializer.serialize(procedure.getType());
    return Joiner.on(SEMICOLON).join(serializedType, procedure.getName(), serializedArguements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Procedure deserialize(final String serializedProcedure) {
    final List<String> serializedParts = Splitter.on(SEMICOLON).splitToList(serializedProcedure);
    if (serializedParts.size() != EXPECTED_NUMBER_OF_SERIALIZATION_PARTS) {
      throw new RuntimeException(String.format(DESERIALIZATION_ERROR_MSG_PATTERN, serializedProcedure));
    }
    final Type type = typeSerializer.deserialize(serializedParts.get(0));
    final String name = serializedParts.get(1);
    final List<Type> arguments = Splitter.on(COMMA).splitToList(serializedParts.get(2)).stream().map((deserializedType) -> typeSerializer.deserialize(deserializedType)).collect(Collectors.toList());
    return Procedure.of((ReferenceType) type, name, arguments);
  }
}
