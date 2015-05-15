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

import org.eclipse.jface.preference.IPreferenceStore;

import ch.uzh.ifi.seal.permo.app.activator.core.Activator;

/**
 * Provides access to the preference store and holds constants representing the keys of all the particular preferences.
 * The key of a preference is qualified by the key of its corresponding preference group.
 * 
 * The idea of the group concept is to qualify the different preferences and bundle related preferences. The groups
 * shall be correspond to the different preference pages in the UI. Currently there is actually only one preference
 * group (and hence preference page) called 'Permo'.
 */
public class Preferences {

  private Preferences() {}

  /**
   * Returns the {@link IPreferenceStore} of the Permo plug-in.
   * 
   * @return the {@link IPreferenceStore} of the Permo plug-in.
   */
  public static IPreferenceStore getStore() {
    return Activator.getDefault().getPreferenceStore();
  }

  private static final String DOT = ".";

  /**
   * The 'Permo' preference group.
   */
  public static final String PERMO = "Permo";

  /**
   * The update interval preference.
   */
  public static final String PERMO__UPDATE_INTERVAL = qualifiedId(PERMO, "updateInterval");
  public static final String PERMO__FETCH_AUTOMATICALLY = qualifiedId(PERMO, "fetchAutomatically");

  /*
   * Creates a qualified ID for the preference defined by the given groupId and preferenceId
   */
  private final static String qualifiedId(final String groupId, final String preferenceId) {
    return Activator.PLUGIN_ID + DOT + groupId + DOT + preferenceId;
  }

}
