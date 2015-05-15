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
import java.util.Map;

import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.MonitoringData;

import com.google.common.collect.Maps;

/**
 * A storage for the randomly generated sample performance data. Is only used together with random data.
 * 
 * Saving the sample data avoids getting different values for the same point in time when requesting data repeatedly
 * (e.g. when the live-updater is activated).
 */
public class SampleDataStorage {

  private static SampleDataStorage instance = new SampleDataStorage();

  private Map<MonitoringData, Map<LocalDateTime, Number>> storage;

  private SampleDataStorage() {
    this.storage = Maps.newHashMap();
  }

  public static SampleDataStorage instance() {
    return instance;
  }

  /**
   * Gets the stored value for the given {@link MonitoringData} at the given point in time.
   * 
   * @param monitoringData
   *          the {@link MonitoringData}
   * @param time
   *          the point in time
   * @return stored value for the given {@link MonitoringData} at the given point in time
   */
  public Number get(final MonitoringData monitoringData, final LocalDateTime time) {
    return getMonitoringDataStorage(monitoringData).get(time);
  }

  /**
   * Saves the given data to the sample data storage.
   * 
   * @param monitoringData
   *          the {@link MonitoringData}
   * @param time
   *          the point in time
   * @param value
   *          the measured value
   * @return
   */
  public boolean put(final MonitoringData monitoringData, final LocalDateTime time, final Number value) {
    if (getMonitoringDataStorage(monitoringData).get(time) == null) {
      getMonitoringDataStorage(monitoringData).put(time, value);
      return true;
    }
    return false;
  }

  private Map<LocalDateTime, Number> getMonitoringDataStorage(final MonitoringData monitoringData) {
    if (storage.get(monitoringData) == null) {
      storage.put(monitoringData, Maps.newHashMap());
    }
    return storage.get(monitoringData);
  }

}
