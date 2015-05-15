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

/**
 * The view model corresponding to the actual time series chart area. It provides the methods to select an area and
 * stores the state of ongoing selections (i.e. while the user is dragging with the mouse).
 */
public class TimeSeriesChartViewModel extends AbstractTimeSeriesCompositeChildViewModel {

  private Date startDragPositionDate;

  public TimeSeriesChartViewModel(final TimeSeriesCompositeViewModel parent) {
    super(parent);
    this.startDragPositionDate = null;
  }

  /**
   * Returns the view model corresponding to the selected range.
   * 
   * @return the view model corresponding to the selected range
   */
  public SelectedRangeViewModel getSelectedRange() {
    return getParent().getSelectedRange();
  }

  /**
   * Returns the view model corresponding to the visible range.
   * 
   * @return the view model corresponding to the visible range
   */
  public VisibleRangeViewModel getVisibleRange() {
    return getParent().getVisibleRange();
  }

  /**
   * Returns the view model corresponding to the visualized time series.
   * 
   * @return the view model corresponding to the visualized time series
   */
  public TimeSeriesViewModel getTimeSeries() {
    return getParent().getTimeSeries();
  }

  /**
   * Action executed on mouse-down. The start the is set to the current date and the end date is flushed. Changes to the
   * selected area temporarily are prohibited from being published
   * 
   * @param dateOfMousePosition
   *          the date corresponding to the current mouse position
   */
  public void mouseDown(final Date dateOfMousePosition) {
    getSelectedRange().setPublishChange(false);
    getSelectedRange().setStartDate(dateOfMousePosition);
    getSelectedRange().setEndDate(null);
    startDragPositionDate = dateOfMousePosition;
  }

  /**
   * Action executed on mouse-move. The start and end dates are set.
   * 
   * @param dateOfMousePosition
   *          the date corresponding to the current mouse position
   */
  public void mouseMove(final Date dateOfMousePosition) {
    if (startDragPositionDate != null) {
      final Date startDate = startDragPositionDate.before(dateOfMousePosition) ? startDragPositionDate : dateOfMousePosition;
      final Date endDate = startDragPositionDate.before(dateOfMousePosition) ? dateOfMousePosition : startDragPositionDate;
      getSelectedRange().setStartDate(startDate);
      getSelectedRange().setEndDate(endDate);
    }
  }

  /**
   * Action executed on mouse-up. The publish-change-lock is cancelled and the temporary drag position is flushed.
   */
  public void mouseUp() {
    getSelectedRange().setPublishChange(true);
    startDragPositionDate = null;
    getSelectedRange().publishChange();
  }

  /**
   * Action executed on mouse-double-click. The selected area is flushed.
   */
  public void mouseDoubleClick() {
    getSelectedRange().setPublishChange(true);
    getSelectedRange().clearSelection();
  }

}
