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
package ch.uzh.ifi.seal.permo.app.preferences.ui.messages;

import org.eclipse.osgi.util.NLS;

/**
 * Provides the literal strings used in the 'app.preferences.ui' sub-component.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "ch.uzh.ifi.seal.permo.app.preferences.ui.messages.messages";
  public static String INVALID_UPDATE_INTERVAL_MSG;
  public static String PREFERENCE_PAGE__DESCRIPTION;
  public static String UPDATE_INTERVAL;
  public static String FETCH_AUTOMATICALLY;
  public static String PERFORMANCE_VIEW;
  public static String HISTORY_VIEW;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {}
}
