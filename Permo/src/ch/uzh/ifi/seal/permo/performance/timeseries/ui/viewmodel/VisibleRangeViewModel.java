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
package ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Date;

import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateConverter;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.view.messages.Messages;

/**
 * Holds the state of the currently visible area.
 */
public class VisibleRangeViewModel extends AbstractTimeSeriesCompositeChildViewModel {

  public static final String PROPERTY__END_DATE = "endDate";
  public static final String PROPERTY__RANGE = "range";

  private TimeRange range;
  private Date endDate;

  public VisibleRangeViewModel(final TimeSeriesCompositeViewModel parent) {
    super(parent);
  }

  /**
   * Returns the current range of the visible area.
   * 
   * @return the current range of the visible area
   */
  public TimeRange getRange() {
    return range;
  }

  /**
   * Sets the range of the visible area.
   * 
   * @param range
   *          the new range of the visible area
   */
  public void setRange(final TimeRange range) {
    final TimeRange oldValue = this.range;
    this.range = range;
    if (oldValue == null || this.range == null || !oldValue.equals(this.range)) {
      updateVisibleRange();
      firePropertyChange(PROPERTY__RANGE, oldValue, this.range);
    }
  }

  /**
   * Returns the end date of the visible area.
   * 
   * @return the end date of the visible area
   */
  public Date getEndDate() {
    return endDate;
  }

  /**
   * Sets the end date of the visible area.
   * 
   * @param endDate
   *          the new end date of the visible area
   */
  public void setEndDate(final Date endDate) {
    final Date oldValue = this.endDate;
    this.endDate = endDate;
    if (oldValue == null || this.endDate == null || !oldValue.equals(this.endDate)) {
      updateVisibleRange();
      firePropertyChange(PROPERTY__END_DATE, oldValue, this.endDate);
    }
  }

  private void updateVisibleRange() {
    getParent().updateTimeSeries();
  }

  /**
   * Calculates the start date of the visible area based on the given range and end date and returns it.
   * 
   * @return the start date of the visible area
   */
  public Date getStartDate() {
    final LocalDateTime endDateTime = DateConverter.convert(getEndDate());
    return DateConverter.convert(endDateTime.minus(getRange().get()));
  }

  /**
   * An enum representing the possible values that can be selected for the r of the visible area including the according
   * textual representations.
   */
  public static enum TimeRange {

    MIN_5(Duration.ofMinutes(5), Messages.RANGE_MINUTES_5),
    MIN_15(Duration.ofMinutes(15), Messages.RANGE_MINUTES_10),
    MIN_30(Duration.ofMinutes(30), Messages.RANGE_MINUTES_30),
    HOUR_1(Duration.ofHours(1), Messages.RANGE_HOURS_1),
    HOUR_3(Duration.ofHours(3), Messages.RANGE_HOURS_3),
    HOUR_6(Duration.ofHours(6), Messages.RANGE_HOURS_6),
    HOUR_12(Duration.ofHours(12), Messages.RANGE_HOURS_12),
    DAY_1(Duration.ofDays(1), Messages.RANGE_DAYS_1),
    DAY_3(Duration.ofDays(3), Messages.RANGE_DAYS_3),
    WEEK_1(Period.ofWeeks(1), Messages.RANGE_WEEKS_1),
    WEEK_2(Period.ofWeeks(2), Messages.RANGE_WEEKS_2),
    MONTH_1(Period.ofMonths(1), Messages.RANGE_MONTHS_1),
    MONTH_3(Period.ofMonths(3), Messages.RANGE_MONTHS_3);

    private TemporalAmount temporalAmount;
    private String text;

    private TimeRange(final TemporalAmount temporalAmount, final String text) {
      this.temporalAmount = temporalAmount;
      this.text = text;
    }

    /**
     * Returns the {@link TemporalAmount} of the time range.
     */
    public TemporalAmount get() {
      return temporalAmount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
      return text;
    }

    /**
     * Creates the right enum value from its textual representation.
     * 
     * @param text
     *          the textual representation
     * @return the right enum value from the given textual representation.
     */
    public static TimeRange fromString(final String text) {
      for (final TimeRange range : values()) {
        if (range.toString().equals(text)) {
          return range;
        }
      }
      return null;
    }
  }
}
