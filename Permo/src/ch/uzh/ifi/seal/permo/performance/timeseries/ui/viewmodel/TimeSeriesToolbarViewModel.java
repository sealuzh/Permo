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

import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateConverter;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateTimes;

/**
 * The viewmodel corresponding to the toolbar of the time series composite.
 */
public class TimeSeriesToolbarViewModel extends AbstractTimeSeriesCompositeChildViewModel {

  public TimeSeriesToolbarViewModel(final TimeSeriesCompositeViewModel parent) {
    super(parent);
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
   * Returns the view model corresponding to the selected range.
   * 
   * @return the view model corresponding to the selected range
   */
  public SelectedRangeViewModel getSelectedRange() {
    return getParent().getSelectedRange();
  }

  /**
   * Sets the end date of the visible range to now.
   */
  public void showUpToDateRange() {
    getParent().getVisibleRange().setEndDate(DateConverter.convert(DateTimes.now()));
  }

}
