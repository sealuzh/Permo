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
package ch.uzh.ifi.seal.permo.performance.main.ui.viewmodel;

import ch.uzh.ifi.seal.permo.common.ui.viewmodel.AbstractStatefulViewModel;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.MonitoringData;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.TimeSeriesCompositeViewModel;

/**
 * The basic viewmodel for the Performance View. Provides access to the data from the model and holds the state of the
 * view.
 */
public class PerformanceViewViewModel extends AbstractStatefulViewModel {

  public static final String PROPERTY__LIVE_MODE_ENABLED = "liveModeEnabled";
  public static final String PROPERTY__SAFE_MODE = "safeMode";

  private TimeSeriesCompositeViewModel timeSeriesComposite;

  private boolean liveModeEnabled;
  private boolean saveMode;

  public PerformanceViewViewModel() {
    this.liveModeEnabled = false;
    this.saveMode = false;
    this.timeSeriesComposite = new TimeSeriesCompositeViewModel();
  }

  /**
   * Sets the monitoring data to be visualized in the view.
   * 
   * @param monitoringData
   *          the monitoring data to be visualized in the view
   */
  public void setMonitoringData(final MonitoringData monitoringData) {
    this.timeSeriesComposite.setMonitoringData(monitoringData);
  }

  /**
   * Returns the viewmodel of the timeseries composite
   * 
   * @return the viewmodel of the timeseries composite
   */
  public TimeSeriesCompositeViewModel getTimeSeriesComposite() {
    return timeSeriesComposite;
  }

  /**
   * Sets the view model of the timeseries composite.
   * 
   * @param timeSeriesComposite
   *          the new viewmodel of the timeseries composite
   */
  public void setTimeSeriesComposite(final TimeSeriesCompositeViewModel timeSeriesComposite) {
    this.timeSeriesComposite = timeSeriesComposite;
  }

  /**
   * Checks whether the live-mode is enabled. If the live-mode is enabled, the performance chart visualized in the
   * Performance View is updated in a regular interval. The interval can be defined in the plug-in preferences.
   * 
   * @return <code>true</code> if the live-mode is enabled, <code>false</code> otherwise
   */
  public boolean isLiveModeEnabled() {
    return this.liveModeEnabled;
  }

  /**
   * Toggles the live-mode-enabled attribute (see {@link #isLiveModeEnabled()}).
   */
  public void toggleLiveModeEnabled() {
    setLiveModeEnabled(!isLiveModeEnabled());
  }

  /**
   * Sets the live-mode-enabled attribute (see {@link #isLiveModeEnabled()}).
   * 
   * @param liveModeEnabled
   *          the new state of the live-mode-enabled attribute
   */
  public void setLiveModeEnabled(final boolean liveModeEnabled) {
    firePropertyChange(PROPERTY__LIVE_MODE_ENABLED, this.liveModeEnabled, this.liveModeEnabled = liveModeEnabled);
    toggleLiveUpdater();
  }

  /**
   * Checks whether the save mode is enabled. If the save mode is enable, view state is store if the Eclipse workbench
   * is closed and can be reloaded when the workbench is started again.
   * 
   * @return <code>true</code> if the save mode is enabled, <code>false</code> otherwise
   */
  public boolean isSafeModeEnabled() {
    return this.saveMode;
  }

  /**
   * Toggle the save mode (see {@link #isSafeModeEnabled()}).
   */
  public void toggleSafeMode() {
    setSafeMode(!isSafeModeEnabled());
  }

  /**
   * Set the save mode (see {@link #isSafeModeEnabled()}).
   * 
   * @param saveMode
   *          the new state of the safe mode
   */
  public void setSafeMode(final boolean saveMode) {
    firePropertyChange(PROPERTY__SAFE_MODE, this.saveMode, this.saveMode = saveMode);
  }

  /**
   * Defines the action token as the view gets visible.
   * 
   * Starts the live updater on the timeseries if the live mode is enabled.
   */
  public void onVisible() {
    if (isLiveModeEnabled()) {
      timeSeriesComposite.startLiveMode();
    }
  }

  /**
   * Is executed as the view gets hidden.
   * 
   * Stops the live updater on the timeseries.
   */
  public void onHidden() {
    timeSeriesComposite.stopLiveMode();
  }

  private void toggleLiveUpdater() {
    if (isLiveModeEnabled()) {
      timeSeriesComposite.startLiveMode();
    }
    else {
      timeSeriesComposite.stopLiveMode();
    }
  }
}
