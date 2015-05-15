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
package ch.uzh.ifi.seal.permo.history.git.ui.viewmodel.table;

import org.eclipse.swt.graphics.Image;

import ch.uzh.ifi.seal.permo.history.common.ui.viewmodel.ProjectHistoryEventViewModel;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateTimes;

/**
 * A label provider for the date/time column in the project history table.
 */
public class DateTimeColumnLabelProvider extends AbstractCommitColumnLabelProvider {

  private static final String SHORT_DATE_TIME_PATTERN = "dd.MM.yy HH:mm";

  /**
   * {@inheritDoc}
   */
  @Override
  public String text(final ProjectHistoryEventViewModel element) {
    return DateTimes.format(element.getDateTime(), SHORT_DATE_TIME_PATTERN);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toolTipText(final ProjectHistoryEventViewModel element) {
    return DateTimes.format(element.getDateTime());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image image(final ProjectHistoryEventViewModel element) {
    return element.getImage();
  }

}
