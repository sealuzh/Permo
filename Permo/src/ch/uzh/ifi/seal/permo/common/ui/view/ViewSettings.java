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
package ch.uzh.ifi.seal.permo.common.ui.view;

import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.jface.dialogs.IDialogSettings;

/**
 * This class provides methods to deal with view settings.
 * 
 * Views can delegate the work of loading and saving settings to this class by providing access to their
 * {@link IDialogSettings}.
 */
public abstract class ViewSettings {

  private static final boolean NULL_VALUE__BOOLEAN = false;
  private static final String NULL_VALUE__STRING = null;

  private final IDialogSettings settings;

  public ViewSettings(final IDialogSettings settings) {
    this.settings = settings;
  }

  /**
   * Loads a boolean value with the give key from the view settings.
   * 
   * @param key
   *          the key of the boolean value to be loaded
   * @return the boolean value or <code>false</code>, if no boolean with the given key could be loaded
   */
  public boolean loadBoolean(final String key) {
    return settings.getBoolean(key);
  }

  /**
   * Loads a {@link String} value with the given key from the view settings.
   * 
   * @param key
   *          the key of the {@link String} value to be loaded
   * @return the {@link String} value or {@link Optional#empty()}, if no {@link String} value with the given key could
   *         be loaded
   */
  public Optional<String> loadString(final String key) {
    final String value = settings.get(key);
    if (value != null && !value.isEmpty()) {
      return Optional.of(value);
    }
    return Optional.empty();
  }

  /**
   * Loads a {@link String} value with the given key from the view settings and performs an action on it if the value
   * could be successfully loaded. The action is passed as parameter to the method and expects the loaded value as input
   * parameter.
   * 
   * @param key
   *          the key of the {@link String} value to be loaded
   * @param action
   *          the action to be performed if the value could be successfully loaded
   */
  public void loadAndDo(final String key, final Consumer<String> action) {
    final Optional<String> value = loadString(key);
    if (value.isPresent()) {
      action.accept(value.get());
    }
  }

  /**
   * Stores the given key/value pair in the view settings.
   * 
   * @param key
   *          the key to be stored
   * @param value
   *          the boolean value to be stored
   */
  public void store(final String key, final boolean value) {
    settings.put(key, value);
  }

  /**
   * Stores the given key/value pair in the view settings.
   * 
   * @param key
   *          the key to be stored
   * @param value
   *          the {@link String} value to be stored
   */
  public void store(final String key, final String value) {
    settings.put(key, value);
  }

  /**
   * Removes the boolean value with the given key from the view settings.
   * 
   * @param key
   *          the key of the boolean value to be removed
   */
  public void removeBoolean(final String key) {
    settings.put(key, NULL_VALUE__BOOLEAN);
  }

  /**
   * Removes the {@link String} value with the given key from the view settings.
   * 
   * @param key
   *          the key of the {@link String} value to be removed
   */
  public void removeString(final String key) {
    settings.put(key, NULL_VALUE__STRING);
  }

}
