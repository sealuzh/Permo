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

import ch.uzh.ifi.seal.permo.common.ui.viewmodel.AbstractStatefulViewModel;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateConverter;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.MonitoringData;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.TimeSeries;
import ch.uzh.ifi.seal.permo.performance.timeseries.core.dataprovider.PerformanceDataProvider;
import ch.uzh.ifi.seal.permo.performance.timeseries.core.dataprovider.sample.SamplePerformanceDataProvider;

/**
 * This class holds the state of a complete time series chart view. It is comprised of different other view models and
 * delegates most of the work to those.
 */
public class TimeSeriesCompositeViewModel extends AbstractStatefulViewModel {

  public static final String PROPERTY__LIVE_MODE = "liveMode";
  public static final String PROPERTY__MONITORING_DATA = "monitoringData";

  private TimeSeriesToolbarViewModel toolbar;
  private TimeSeriesChartViewModel chart;
  private VisibleRangeViewModel visibleRange;
  private SelectedRangeViewModel selectedRange;

  private PerformanceDataProvider dataProvider;
  private LiveUpdater liveUpdater;

  private boolean liveMode;

  private MonitoringData monitoringData;
  private TimeSeriesViewModel timeSeriesViewModel;

  public TimeSeriesCompositeViewModel() {
    initValues();
    initDependencies();
    initChildViewModels();
  }

  private void initValues() {
    this.liveMode = false;
  }

  private void initDependencies() {
    // TODO replace SamplePerformanceDataProvider with proper implementation
    this.dataProvider = new SamplePerformanceDataProvider();
    this.liveUpdater = new LiveUpdater(this);
  }

  private void initChildViewModels() {
    this.visibleRange = new VisibleRangeViewModel(this);
    this.timeSeriesViewModel = new TimeSeriesViewModel(this);
    this.selectedRange = new SelectedRangeViewModel(this);
    this.toolbar = new TimeSeriesToolbarViewModel(this);
    this.chart = new TimeSeriesChartViewModel(this);
  }

  public MonitoringData getMonitoringData() {
    return monitoringData;
  }

  /**
   * Sets the data that shall be visualized in the time series chart.
   * 
   * @param monitoringData
   *          the data that shall be visualized in the time series chart
   */
  public void setMonitoringData(final MonitoringData monitoringData) {
    if (projectChanged(monitoringData) && getSelectedRange().hasValidSelection()) {
      getSelectedRange().clearSelection();
    }
    firePropertyChange(PROPERTY__MONITORING_DATA, this.monitoringData, this.monitoringData = monitoringData);
    updateTimeSeries();
  }

  private boolean projectChanged(final MonitoringData newMonitoringData) {
    return this.monitoringData == null || newMonitoringData == null || !this.monitoringData.getProject().equals(newMonitoringData.getProject());
  }

  /**
   * Checks whether the live mode is active.
   * 
   * @return whether the live mode is active
   */
  public boolean getLiveMode() {
    return this.liveMode;
  }

  /**
   * Start the live mode.
   */
  public void startLiveMode() {
    if (!getLiveMode()) {
      setLiveMode(true);
      liveUpdater.start();
    }
  }

  /**
   * Stops the live mode.
   */
  public void stopLiveMode() {
    if (getLiveMode()) {
      setLiveMode(false);
      liveUpdater.stop();
    }
  }

  private void setLiveMode(final boolean liveMode) {
    firePropertyChange(PROPERTY__LIVE_MODE, this.liveMode, this.liveMode = liveMode);
  }

  public TimeSeriesToolbarViewModel getToolbar() {
    return toolbar;
  }

  /**
   * Returns the view model corresponding to the time series chart.
   * 
   * @return the view model corresponding to the time series chart
   */
  public TimeSeriesChartViewModel getChart() {
    return chart;
  }

  /**
   * Returns the visible range.
   * 
   * @return the visible range
   */
  public VisibleRangeViewModel getVisibleRange() {
    return visibleRange;
  }

  /**
   * Returns the selected range.
   * 
   * @return the selected range
   */
  public SelectedRangeViewModel getSelectedRange() {
    return selectedRange;
  }

  /**
   * Returns the view model corresponding to the visualized time series.
   * 
   * @return the view model corresponding to the visualized time series
   */
  public TimeSeriesViewModel getTimeSeries() {
    return timeSeriesViewModel;
  }

  /**
   * Updates the time series view model according to the underlying model element.
   */
  public void updateTimeSeries() {
    if (!isEmpty()) {
      timeSeriesViewModel.setTimeSeries(getCurrentTimeSeries());
    }
  }

  /**
   * Checks whether the data for the timeseries chart is present or not.
   * 
   * @return <code>true</code> of the data is present, <code>false</code> otherwise
   */
  public boolean isEmpty() {
    return monitoringData == null;
  }

  private TimeSeries getCurrentTimeSeries() {
    return dataProvider.getData(monitoringData, DateConverter.convert(getVisibleRange().getStartDate()), DateConverter.convert(getVisibleRange().getEndDate()));
  }

}
