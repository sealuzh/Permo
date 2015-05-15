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
package ch.uzh.ifi.seal.permo.app.preferences.core;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import ch.uzh.ifi.seal.permo.app.activator.core.Activator;

/**
 * This class allows to set default values for preferences that have not yet been explicitly specified by the user of
 * the plug-in. To do so, it is registered in the plugin.xml file as preference initializer.
 */
public class DefaultPreferenceInitializer extends AbstractPreferenceInitializer {

  private static final int PERMO__UPDATE_INTERVAL__DEFAULT_VALUE = 2;
  private static final boolean PERMO__FETCH_AUTOMATICALLY__DEFAULT_VALUE = false;

  /**
   * {@inheritDoc}
   */
  @Override
  public void initializeDefaultPreferences() {
    final IPreferenceStore store = Activator.getDefault().getPreferenceStore();
    store.setDefault(Preferences.PERMO__UPDATE_INTERVAL, PERMO__UPDATE_INTERVAL__DEFAULT_VALUE);
    store.setDefault(Preferences.PERMO__FETCH_AUTOMATICALLY, PERMO__FETCH_AUTOMATICALLY__DEFAULT_VALUE);
  }

}
