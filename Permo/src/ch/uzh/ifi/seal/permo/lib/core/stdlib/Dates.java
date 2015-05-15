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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Provides utility functionality to deal with {@link Date}'s.
 */
public class Dates {

  private static final String DATE__SERIALIZATION_PATTERN = "yyyy-MM-dd HH:mm:ss";

  private Dates() {}

  /**
   * Creates a {@link String} representation of the given {@link Date}.
   * 
   * @param date
   *          the {@link Date}
   * @return the {@link String} representation of the given {@link Date}.
   */
  public static String toString(final Date date) {
    return new SimpleDateFormat(DATE__SERIALIZATION_PATTERN).format(date);
  }

  /**
   * Creates a {@link Date} from the given {@link String} representation
   * 
   * @param date
   *          the {@link String} representation of the {@link Date}
   * @return the created {@link Date}
   */
  public static Date fromString(final String date) {
    try {
      return new SimpleDateFormat(DATE__SERIALIZATION_PATTERN).parse(date);
    }
    catch (final ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Checks whether the given {@link Date} is set (i.e. it is not null and differs from the default value).
   * 
   * @param date
   *          the {@link Date} to be checked
   * @return <code>true</code> if the given {@link Date} is set, <code>false</code> otherwise
   */
  public static boolean isSet(final Date date) {
    return date != null && !date.equals(Defaults.DATE);
  }

  /**
   * Provides default values.
   */
  public static class Defaults {

    private Defaults() {}

    public static Date DATE = Date.from(Instant.ofEpochMilli(0));
    public static int DAY = 1;
    public static int MONTH = 0;
    public static int YEAR = 1970;
    public static int HOURS = 1;
    public static int MINUTES = 0;
    public static int SECONDS = 0;
  }

}
