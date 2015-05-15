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
package ch.uzh.ifi.seal.permo.performance.main.ui.view;

import java.util.Date;
import java.util.Optional;

import org.eclipse.jface.dialogs.IDialogSettings;

import ch.uzh.ifi.seal.permo.common.ui.view.ViewSettings;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateConverter;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateTimes;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.Dates;
import ch.uzh.ifi.seal.permo.performance.main.ui.viewmodel.PerformanceViewViewModel;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.serializer.MonitoringDataSerializerImpl;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.VisibleRangeViewModel.TimeRange;

/**
 * Concrete implementation of {@link ViewSettings} for the Performance View. Loads and saves the state of the
 * Performance View.
 */
public class PerformanceViewSettings extends ViewSettings {

  private static final String STATE__SAVE_MODE = "safeMode";
  private static final String STATE__LIVE_MODE_ENABLED = "liveModeEnabled";
  private static final String STATE__VISIBLE_RANGE_INTERVAL = "visibleRange.interval";
  private static final String STATE__VISIBLE_RANGE_END_DATE = "visibleRange.endDate";
  private static final String STATE__MONITORING_DATA = "monitoringData";

  public PerformanceViewSettings(final IDialogSettings settings) {
    super(settings);
  }

  public final void loadState(final PerformanceViewViewModel viewModel) {
    viewModel.setSafeMode(loadBoolean(STATE__SAVE_MODE));
    viewModel.setLiveModeEnabled(loadBoolean(STATE__LIVE_MODE_ENABLED));

    final TimeRange interval = loadInterval().orElse(TimeRange.MIN_5);
    viewModel.getTimeSeriesComposite().getVisibleRange().setRange(interval);
    final Date endDate = loadEndDate().orElse(DateConverter.convert(DateTimes.now()));
    viewModel.getTimeSeriesComposite().getVisibleRange().setEndDate(endDate);

    loadAndDo(STATE__MONITORING_DATA, (serializedData) -> viewModel.setMonitoringData(new MonitoringDataSerializerImpl().deserialize(serializedData)));
  }

  public Optional<TimeRange> loadInterval() {
    final Optional<String> serializedInterval = loadString(STATE__VISIBLE_RANGE_INTERVAL);
    if (serializedInterval.isPresent()) {
      final TimeRange interval = TimeRange.fromString(serializedInterval.get());
      if (interval != null) {
        return Optional.of(interval);
      }
    }
    return Optional.empty();
  }

  public Optional<Date> loadEndDate() {
    final Optional<String> dateString = loadString(STATE__VISIBLE_RANGE_END_DATE);
    if (dateString.isPresent()) {
      final Date date = Dates.fromString(dateString.get());
      if (date != null) {
        return Optional.of(date);
      }
    }
    return Optional.empty();
  }

  public final void saveState(final PerformanceViewViewModel viewModel) {
    removeValues();
    if (viewModel.isSafeModeEnabled()) {
      store(STATE__SAVE_MODE, viewModel.isSafeModeEnabled());
      store(STATE__LIVE_MODE_ENABLED, viewModel.isLiveModeEnabled());
      if (viewModel.getTimeSeriesComposite().getVisibleRange().getRange() != null) {
        store(STATE__VISIBLE_RANGE_INTERVAL, viewModel.getTimeSeriesComposite().getVisibleRange().getRange().toString());
      }
      if (viewModel.getTimeSeriesComposite().getVisibleRange().getEndDate() != null) {
        store(STATE__VISIBLE_RANGE_END_DATE, Dates.toString(viewModel.getTimeSeriesComposite().getVisibleRange().getEndDate()));
      }
      if (viewModel.getTimeSeriesComposite().getMonitoringData() != null) {
        store(STATE__MONITORING_DATA, new MonitoringDataSerializerImpl().serialize(viewModel.getTimeSeriesComposite().getMonitoringData()));
      }
    }
  }

  public void removeValues() {
    removeBoolean(STATE__SAVE_MODE);
    removeBoolean(STATE__LIVE_MODE_ENABLED);
    removeString(STATE__VISIBLE_RANGE_INTERVAL);
    removeString(STATE__VISIBLE_RANGE_INTERVAL);
    removeString(STATE__MONITORING_DATA);
  }
}
