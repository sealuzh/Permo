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
package ch.uzh.ifi.seal.permo.performance.timeseries.ui.view.messages;

import org.eclipse.osgi.util.NLS;

/**
 * Provides the literal strings used in the 'performance.timeseries' sub-component.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "ch.uzh.ifi.seal.permo.performance.timeseries.ui.view.messages.messages";
  public static String END_NOT_AFTER_STAR_ERROR_MSG;
  public static String ENDING;
  public static String RANGE_DAYS_1;
  public static String RANGE_DAYS_3;
  public static String RANGE_HOURS_1;
  public static String RANGE_HOURS_12;
  public static String RANGE_HOURS_3;
  public static String RANGE_HOURS_6;
  public static String RANGE_MINUTES_10;
  public static String RANGE_MINUTES_30;
  public static String RANGE_MINUTES_5;
  public static String RANGE_MONTHS_1;
  public static String RANGE_MONTHS_3;
  public static String RANGE_WEEKS_1;
  public static String RANGE_WEEKS_2;
  public static String SELECTED_AREA;
  public static String VISIBLE_AREA;
  public static String TODAY_BUTTON__TOOLTIP;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {}
}
