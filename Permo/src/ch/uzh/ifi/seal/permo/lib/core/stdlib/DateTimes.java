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

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Utility methods for dates and times.
 */
public class DateTimes {

  private static final String DATE_TIME__DEFAULT_FORMAT = "dd.MM.yyyy HH:mm:ss";

  private DateTimes() {}

  /**
   * Creates a {@link LocalDateTime} for the current time with the granularity of seconds.
   * 
   * @return the created {@link LocalDateTime}
   */
  public static LocalDateTime now() {
    final LocalDateTime now = LocalDateTime.now();
    return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond());
  }

  /**
   * Creates a new {@link LocalDateTime} for the given number of epoch seconds.
   * 
   * @param seconds
   *          epoch seconds
   * @return the created {@link LocalDateTime}
   */
  public static LocalDateTime ofEpochSeconds(final int seconds) {
    return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneOffset.systemDefault());
  }

  /**
   * Returns a textual representation of the given {@link LocalDateTime}
   * 
   * @param dateTime
   *          the {@link LocalDateTime}.
   * @return the formatted textual representation of the given {@link LocalDateTime}
   */
  public static String format(final LocalDateTime dateTime) {
    return format(dateTime, DATE_TIME__DEFAULT_FORMAT);
  }

  /**
   * Returns a textual representation of the given {@link LocalDateTime} formatted according to the given pattern.
   * 
   * @param dateTime
   *          the {@link LocalDateTime}
   * @param pattern
   *          the formatting pattern
   * @return the formatted textual representation of the given {@link LocalDateTime}
   */
  public static String format(final LocalDateTime dateTime, final String pattern) {
    return dateTime.format(DateTimeFormatter.ofPattern(pattern));
  }

}
