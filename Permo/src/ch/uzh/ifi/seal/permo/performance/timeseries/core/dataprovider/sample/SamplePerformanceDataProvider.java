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
package ch.uzh.ifi.seal.permo.performance.timeseries.core.dataprovider.sample;

import java.time.LocalDateTime;

import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.Measurement;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.MonitoringData;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.TimeSeries;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.Unit;
import ch.uzh.ifi.seal.permo.performance.timeseries.core.dataprovider.PerformanceDataProvider;

import com.google.common.collect.Lists;

/**
 * An implementation of {@link PerformanceDataProvider} providing sample data.
 */
public class SamplePerformanceDataProvider implements PerformanceDataProvider {

  private static final String TITLE_PATTERN = "Execution time of %s";
  private static final Unit MILLISECONDS = new Unit("milliseconds", "ms");

  private SampleValueGenerator sampleValueGenerator;

  public SamplePerformanceDataProvider() {
    this.sampleValueGenerator = new SampleValueGenerator();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Measurement getData(final MonitoringData monitoringData, final LocalDateTime time) {
    Number value = SampleDataStorage.instance().get(monitoringData, time);
    if (value == null) {
      value = sampleValueGenerator.generateValue(getLatestValue(monitoringData, time));
      SampleDataStorage.instance().put(monitoringData, time, value);
    }
    return Measurement.of(time, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public TimeSeries getData(final MonitoringData monitoringData, final LocalDateTime startTime, final LocalDateTime endTime) {
    final String title = String.format(TITLE_PATTERN, monitoringData.getProcedure().toShortString());
    final TimeSeries timeSeries = new TimeSeries(title, MILLISECONDS, Lists.newArrayList());
    LocalDateTime time = startTime;
    while (!time.isAfter(endTime)) {
      timeSeries.addMeasurement(getData(monitoringData, time));
      time = time.plusSeconds(1);
    }
    return timeSeries;
  }

  private Double getLatestValue(final MonitoringData monitoringData, final LocalDateTime time) {
    return (Double) SampleDataStorage.instance().get(monitoringData, time.minusSeconds(1));
  }

}
