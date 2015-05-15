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
package ch.uzh.ifi.seal.permo.performance.timeseriers.core.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * Represents a time series containing arbitrary numerical values.
 */
public class TimeSeries {

  private static final String TITLE_WITH_UNIT_PATTERN = "%s [%s]";

  private final String title;
  private final Unit unit;

  private List<Measurement> measurements;

  private List<TimeSeriesObserver> observers;

  public TimeSeries(final String title, final Unit unit, final List<Measurement> measurements) {
    this.title = title;
    this.unit = unit;
    this.measurements = Lists.newArrayList(measurements);
    this.observers = Lists.newArrayList();
  }

  /**
   * Returns the title of the time series.
   * 
   * @return the title of the time series
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the {@link Unit} of the time series.
   * 
   * @return the {@link Unit} of the time series
   */
  public Unit getUnit() {
    return unit;
  }

  /**
   * Returns the title and the abbreviation of the unit in the following form: [title] ([abbreviation-of-unit])
   * 
   * @return a {@link String} containing the title and the unit of the time series
   */
  public String getTitleWithUnit() {
    return String.format(TITLE_WITH_UNIT_PATTERN, getTitle(), getUnit().getAbbreviation());
  }

  /**
   * Returns the {@link List} of measurements of the time series. A measurement contains a measured value at a given
   * point in time.
   * 
   * @return the {@link List} of measurements of the time series.
   */
  public List<Measurement> getMeasurements() {
    return ImmutableList.copyOf(this.measurements);
  }

  /**
   * Adds a new measurement to the current time series.
   * 
   * @param dateTime
   *          the point in time the value has been measured
   * @param value
   *          the value
   */
  public void addMeasurement(final LocalDateTime dateTime, final Number value) {
    addMeasurement(Measurement.of(dateTime, value));
  }

  /**
   * Adds a new measurement to the current time series.
   * 
   * @param measurement
   *          the measurement to be added
   */
  public void addMeasurement(final Measurement measurement) {
    this.measurements.add(measurement);
    notifyObservers(measurement);
  }

  /**
   * Register an observer that is notified as soon as a new value is added to the timeseries.
   * 
   * @param observer
   *          the observer to be registered
   */
  public void registerObserver(final TimeSeriesObserver observer) {
    this.observers.add(observer);
  }

  /**
   * Removes a registered observer (see {@link #registerObserver(TimeSeriesObserver)}).
   * 
   * @param observer
   *          the observer to be removed
   */
  public void removeObserver(final TimeSeriesObserver observer) {
    this.observers.remove(observer);
  }

  /*
   * Notifies all registered observers as soon as a new value has been added to the timeseries.
   */
  private void notifyObservers(final Measurement measurement) {
    this.observers.stream().forEach((observer) -> observer.accept(measurement));
  }

  /**
   * The interface for observer of the timeseries composed of a single method that expects the newly added
   * {@link Measurement}.
   */
  public static interface TimeSeriesObserver extends Consumer<Measurement> {}

}
