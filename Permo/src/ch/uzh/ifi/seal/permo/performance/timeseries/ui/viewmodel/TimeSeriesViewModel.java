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

import static ch.uzh.ifi.seal.permo.lib.core.stdlib.DateConverter.convert;

import java.time.LocalDateTime;
import java.util.Date;

import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.TimeSeries;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.Unit;

/**
 * The view model corresponding to the given {@link TimeSeries}.
 */
public class TimeSeriesViewModel extends AbstractTimeSeriesCompositeChildViewModel {

  public static final String PROPERTY__TIME_SERIES = "timeSeries";

  private TimeSeries timeSeries;

  public TimeSeriesViewModel(final TimeSeriesCompositeViewModel parent) {
    super(parent);
  }

  /**
   * Checks whether the time series exists or not.
   * 
   * @return <code>true</code> if the underlying time series exists, <code>false</code> otherwise
   */
  public boolean isEmpty() {
    return timeSeries == null;
  }

  /**
   * Returns the {@link TimeSeries#getTitleWithUnit()} attribute.
   * 
   * @return the {@link TimeSeries#getTitleWithUnit()} attribute
   */
  public String getTitleWithUnit() {
    return timeSeries.getTitleWithUnit();
  }

  /**
   * Returns the {@link Unit} of the time series.
   * 
   * @return the {@link Unit} of the time series
   */
  public Unit getUnit() {
    return timeSeries.getUnit();
  }

  /**
   * Returns an array of all the dates of the measurements.
   * 
   * @return an array of all the dates of the measurements.
   */
  public Date[] getTimeValues() {
    return timeSeries.getMeasurements().stream().map(m -> convert(m.getDateTime())).toArray(Date[]::new);
  }

  /**
   * Sets the time series.
   * 
   * @param timeSeries
   *          the time series to be set
   */
  public void setTimeSeries(final TimeSeries timeSeries) {
    firePropertyChange(PROPERTY__TIME_SERIES, this.timeSeries, this.timeSeries = timeSeries);
  }

  /**
   * Returns an array of all the values of the measurements.
   * 
   * @return an array of all the values of the measurements.
   */
  public double[] getValues() {
    return timeSeries.getMeasurements().stream().mapToDouble(m -> m.getValue().doubleValue()).toArray();
  }

  /**
   * Adds a new measurement to the underlying time series.
   * 
   * @param dateTime
   *          the point in time the value has been measured
   * @param value
   *          the value
   */
  public void addMeasurement(final LocalDateTime dateTime, final Number value) {
    timeSeries.addMeasurement(dateTime, value);
  }

}
