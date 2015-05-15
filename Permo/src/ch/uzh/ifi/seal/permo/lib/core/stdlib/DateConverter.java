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

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Provides methods to convert {@link Date}'s into {@link LocalDateTime}'s and vice versa.
 */
public class DateConverter {

  /**
   * Converts a {@link LocalDateTime} into a {@link Date}. The system default is used to set the time zone.
   * 
   * @param dateTime
   *          the {@link LocalDateTime} to be converted
   * @return the created {@link Date}
   */
  public static Date convert(final LocalDateTime dateTime) {
    return convert(dateTime.atZone(ZoneId.systemDefault()));
  }

  /**
   * Converts a {@link ZonedDateTime} into a {@link Date}.
   * 
   * @param dateTime
   *          the {@link ZonedDateTime} to be converted
   * @return the created {@link Date}
   */
  public static Date convert(final ZonedDateTime dateTime) {
    return Date.from(dateTime.toInstant());
  }

  /**
   * Converts a {@link Date} into a {@link LocalDateTime}.
   * 
   * @param date
   *          the {@link Date} to be converted
   * @return the created {@link LocalDateTime}
   */
  public static LocalDateTime convert(final Date date) {
    if (date != null) {
      return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    return null;
  }

}
