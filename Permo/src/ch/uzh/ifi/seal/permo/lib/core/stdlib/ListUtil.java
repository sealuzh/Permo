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
package ch.uzh.ifi.seal.permo.lib.core.stdlib;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Provides utility methods to deal with {@link List}'s.
 */
public class ListUtil {

  private ListUtil() {}

  /**
   * Returns the last element of the given list.
   * 
   * @param list
   *          the {@link List}
   * @return the last element of the given list
   */
  public static <E> Optional<E> last(final List<E> list) {
    return list.isEmpty() ? Optional.empty() : Optional.of(list.get(list.size() - 1));
  }

  /**
   * Returns the last element of the given list or <code>null</code>, if the list is empty.
   * 
   * @param list
   *          the {@link List}
   * @return the last element of the given list or <code>null</code>, if the list is empty
   */
  public static <E> E lastOrNull(final List<E> list) {
    return last(list).orElse(null);
  }

  /**
   * Maps the given input list to another list
   * 
   * @param list
   *          the list to be mapped
   * @param mapper
   *          the mapper that maps each element of the input list to an element of the output list
   * @return the mapped list
   */
  public static <I, O> List<O> map(final List<I> list, final Function<I, O> mapper) {
    return list.stream().map(mapper).collect(Collectors.toList());
  }

}
