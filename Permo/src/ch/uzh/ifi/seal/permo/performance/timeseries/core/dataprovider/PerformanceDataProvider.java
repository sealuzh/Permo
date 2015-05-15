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
package ch.uzh.ifi.seal.permo.performance.timeseries.core.dataprovider;

import java.time.LocalDateTime;

import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.Measurement;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.MonitoringData;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.TimeSeries;

/**
 * An interface for data provider that fetch performance data for the given artifacts at the given times/time intervals.
 */
public interface PerformanceDataProvider {

  /**
   * Fetches the measured performance value for the given {@link MonitoringData} at the given point in time.
   * 
   * @param monitoringData
   *          the {@link MonitoringData}
   * @param time
   *          the point in time
   * @return the measured performance value for the given {@link MonitoringData} at the given point in time
   */
  public Measurement getData(MonitoringData monitoringData, LocalDateTime time);

  /**
   * Fetches the measured performance values for the given {@link MonitoringData} during the given interval.
   * 
   * @param monitoringData
   *          the {@link MonitoringData}
   * @param startTime
   *          the start time of the interval
   * @param endTime
   *          the end time of the interval
   * @return the measured performance values for the given {@link MonitoringData} during the specified interval
   */
  public TimeSeries getData(MonitoringData monitoringData, LocalDateTime startTime, LocalDateTime endTime);

}
