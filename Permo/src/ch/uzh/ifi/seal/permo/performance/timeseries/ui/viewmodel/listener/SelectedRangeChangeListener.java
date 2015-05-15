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
package ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.SelectedRangeViewModel;

/**
 * A listener that is notified whenever the selected range has changed.
 */
public abstract class SelectedRangeChangeListener implements PropertyChangeListener {

  /**
   * {@inheritDoc}
   */
  @Override
  public final void propertyChange(final PropertyChangeEvent event) {
    if (hasSelectionRangeChanged(event)) {
      selectedRangeChanged();
    }
  }

  /**
   * Is called if the selected range has been changed.
   */
  protected abstract void selectedRangeChanged();

  /**
   * Checks whether the given {@link PropertyChangeEvent} influences the selected range.
   * 
   * @param propertyName
   *          the {@link PropertyChangeEvent}
   * @return <code>true</code> if the selected range has changed due to the {@link PropertyChangeEvent},
   *         <code>false</code> otherwise
   */
  private boolean hasSelectionRangeChanged(final PropertyChangeEvent propertyChangedEvent) {
    final String propertyName = propertyChangedEvent.getPropertyName();
    return propertyName.equals(SelectedRangeViewModel.PROPERTY__START_DATE) || propertyName.equals(SelectedRangeViewModel.PROPERTY__END_DATE);
  }

}
