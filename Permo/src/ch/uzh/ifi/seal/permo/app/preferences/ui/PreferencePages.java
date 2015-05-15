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
package ch.uzh.ifi.seal.permo.app.preferences.ui;

import ch.uzh.ifi.seal.permo.app.activator.core.Activator;
import ch.uzh.ifi.seal.permo.app.preferences.core.Preferences;

/**
 * Holds constants representing the ID's of the preference pages of the Permo plugin.
 * 
 * Currently there is only one main preference page called 'Permo'.
 */
public class PreferencePages {

  private static final String DOT = ".";
  private static final String BASE_QUALIFIER = Activator.PLUGIN_ID + DOT + "preferencePages" + DOT;

  /**
   * The ID of the Permo preference page.
   */
  public static final String PERMO = BASE_QUALIFIER + Preferences.PERMO;

}
