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
package ch.uzh.ifi.seal.permo.performance.main.ui.view.messages;

import org.eclipse.osgi.util.NLS;

/**
 * Provides the literal strings used in the 'performance.main' sub-component.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "ch.uzh.ifi.seal.permo.performance.main.ui.view.messages.messages";

  public static String ACTION__LIVE_MODE;
  public static String ACTION__SAVE;
  public static String ACTION_OPEN_PREFERENCES;
  public static String SELECT_METHOD_MESSAGE;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {}
}
