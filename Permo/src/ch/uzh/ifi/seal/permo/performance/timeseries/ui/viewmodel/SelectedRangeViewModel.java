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

import java.util.Date;

import ch.uzh.ifi.seal.permo.app.events.core.Events;
import ch.uzh.ifi.seal.permo.common.core.events.SelectedRangeChangedEvent;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateConverter;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.Dates;

/**
 * Stores the state of the selected range of a time series chart.
 */
public class SelectedRangeViewModel extends AbstractTimeSeriesCompositeChildViewModel {

  public static final String PROPERTY__START_DATE = "startDate";
  public static final String PROPERTY__END_DATE = "endDate";

  private Date startDate;
  private Date endDate;

  private boolean publishChange;

  public SelectedRangeViewModel(final TimeSeriesCompositeViewModel parent) {
    super(parent);
    this.publishChange = true;
  }

  /**
   * Returns the start date of the selection.
   * 
   * @return the start date of the selection
   */
  public Date getStartDate() {
    return startDate;
  }

  /**
   * Set the start date of the selection.
   * 
   * @param startDate
   *          the start date of the selection
   */
  public void setStartDate(final Date startDate) {
    firePropertyChange(PROPERTY__START_DATE, this.startDate, this.startDate = startDate);
    publishChange();
  }

  /**
   * Returns the end date of the selection.
   * 
   * @return the end date of the selection
   */
  public Date getEndDate() {
    return endDate;
  }

  /**
   * Set the end date of the selection.
   * 
   * @param endDate
   *          the end date of the selection
   */
  public void setEndDate(final Date endDate) {
    firePropertyChange(PROPERTY__END_DATE, this.endDate, this.endDate = endDate);
    publishChange();
  }

  /**
   * Clears the selection.
   */
  public void clearSelection() {
    setStartDate(Dates.Defaults.DATE);
    setEndDate(Dates.Defaults.DATE);
  }

  /**
   * Specifies whether observer shall be notified if the selected range changed.
   * 
   * This allows to NOT send notifications while the user is dragging with the mouse.
   * 
   * @param publish
   *          a boolean indicating whether changes shall be published or not
   */
  public void setPublishChange(final boolean publish) {
    this.publishChange = publish;
  }

  /**
   * Notifies the registered observer that the selected range has changed.
   */
  public void publishChange() {
    if (publishChange && (!hasSelection() || hasValidSelection())) {
      Events.INSTANCE.post(new SelectedRangeChangedEvent(getParent().getMonitoringData().getProject(), DateConverter.convert(getStartDate()), DateConverter.convert(getEndDate())));
    }
  }

  /**
   * Checks whether there is a selection.
   * 
   * @return <code>true</code> if a selection is set, false otherwise
   */
  public boolean hasSelection() {
    return Dates.isSet(getStartDate()) && Dates.isSet(getEndDate());
  }

  /**
   * Checks whether the selection is valid. A selection is valid if the start date is before the end date.
   * 
   * @return <code>true</code> if there is a valid selection, <code>false</code> otherwise
   */
  public boolean hasValidSelection() {
    return hasSelection() && getStartDate().before(getEndDate());
  }

}
