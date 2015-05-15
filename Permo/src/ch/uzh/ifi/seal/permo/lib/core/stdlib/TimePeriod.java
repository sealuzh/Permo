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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.time.LocalDateTime;

/**
 * Defines a time period based on a starting {@link LocalDateTime} and an ending {@link LocalDateTime}.
 */
public class TimePeriod {

  private static final String INVALID_TIME_PERIOD_PATTERN = "Invalid parameters for time period: end time (%s) has to be after start time (%s).";
  private static final String TO_STRING_PATTERN = "(%s - %s)";

  private final LocalDateTime startDateTime;
  private final LocalDateTime endDateTime;

  private TimePeriod(final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
  }

  /**
   * Returns the starting {@link LocalDateTime}.
   * 
   * @return the starting {@link LocalDateTime}
   */
  public LocalDateTime getStartDateTime() {
    return startDateTime;
  }

  /**
   * Returns the ending {@link LocalDateTime}.
   * 
   * @return the ending {@link LocalDateTime}
   */
  public LocalDateTime getEndDateTime() {
    return endDateTime;
  }

  private static void checkPreconditions(final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
    checkNotNull(startDateTime);
    checkNotNull(endDateTime);
    checkState(endDateTime.isAfter(startDateTime), INVALID_TIME_PERIOD_PATTERN, DateTimes.format(startDateTime), DateTimes.format(endDateTime));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return String.format(TO_STRING_PATTERN, DateTimes.format(getStartDateTime()), DateTimes.format(getEndDateTime()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    return Equals.equals(this, obj, () -> {
      final TimePeriod period = (TimePeriod) obj;
      return this.getStartDateTime().equals(period.getStartDateTime()) && this.getEndDateTime().equals(period.getEndDateTime());
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return HashCodes.hashCode(getStartDateTime().hashCode(), getEndDateTime().hashCode());
  }

  /**
   * A static factory method to create a new instance of this class.
   * 
   * @param startDateTime
   *          the starting {@link LocalDateTime}
   * @param endDateTime
   *          the ending {@link LocalDateTime}
   * @return
   */
  public static TimePeriod of(final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
    checkPreconditions(startDateTime, endDateTime);
    return new TimePeriod(startDateTime, endDateTime);
  }
}
