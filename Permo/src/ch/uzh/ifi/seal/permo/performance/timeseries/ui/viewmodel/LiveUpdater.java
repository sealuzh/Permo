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

import java.time.LocalDateTime;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import ch.uzh.ifi.seal.permo.app.preferences.core.Preferences;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateConverter;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateTimes;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.RecurringJob;

/**
 * The live is responsible to update the timeseries in a given interval if the live mode is activated.
 */
public class LiveUpdater implements IPropertyChangeListener {

  private RecurringJob recurringJob;

  private boolean isRunning;

  private TimeSeriesCompositeViewModel viewModel;

  public LiveUpdater(final TimeSeriesCompositeViewModel viewModel) {
    this.isRunning = false;
    this.viewModel = viewModel;
    registerPreferencesListener();
  }

  private void registerPreferencesListener() {
    Preferences.getStore().addPropertyChangeListener(this);
  }

  /**
   * Starts the live updater.
   */
  public void start() {
    if (!isRunning) {
      stop();
      isRunning = true;
      final int interval = Preferences.getStore().getInt(Preferences.PERMO__UPDATE_INTERVAL);
      recurringJob = new RecurringJob(interval, () -> {
        if (viewModel.getTimeSeries() != null) {
          final LocalDateTime now = DateTimes.now();
          viewModel.getVisibleRange().setEndDate(DateConverter.convert(now));
        }
      });
      recurringJob.start();
    }
  }

  /**
   * Stops the live updater.
   */
  public void stop() {
    if (isRunning && recurringJob != null) {
      recurringJob.stopRequest();
      isRunning = false;
    }
  }

  /**
   * 
   * {@inheritDoc}
   */
  @Override
  public void propertyChange(final PropertyChangeEvent event) {
    if (event.getProperty().equals(Preferences.PERMO__UPDATE_INTERVAL) && recurringJob != null) {
      recurringJob.setInterval(Preferences.getStore().getInt(Preferences.PERMO__UPDATE_INTERVAL));
    }
  }

}
