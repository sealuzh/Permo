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
package ch.uzh.ifi.seal.permo.performance.timeseries.ui.view;

import org.eclipse.core.databinding.observable.value.DateAndTimeObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;

import ch.uzh.ifi.seal.permo.lib.core.eclipse.DataBindings;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.Dates;

/**
 * A wrapper that combines a data and a time widget.
 */
public class DateTimeWidget {

  private final DateTime dateWidget;
  private final DateTime timeWidget;

  public DateTimeWidget(final Composite parent) {
    dateWidget = createDateWidget(parent);
    timeWidget = createTimeWidget(parent);
  }

  private DateTime createDateWidget(final Composite parent) {
    final DateTime dateWidget = new DateTime(parent, SWT.DATE | SWT.DROP_DOWN);
    dateWidget.setDay(Dates.Defaults.DAY);
    dateWidget.setMonth(Dates.Defaults.MONTH);
    dateWidget.setYear(Dates.Defaults.YEAR);
    return dateWidget;
  }

  private DateTime createTimeWidget(final Composite parent) {
    final DateTime timeWidget = new DateTime(parent, SWT.TIME | SWT.DROP_DOWN);
    timeWidget.setHours(Dates.Defaults.HOURS);
    timeWidget.setMinutes(Dates.Defaults.MINUTES);
    timeWidget.setSeconds(Dates.Defaults.SECONDS);
    return timeWidget;
  }

  /**
   * Binds the values of the date and time widgets to the given property of the given object.
   * 
   * @param boundedObject
   * @param boundedPropertyName
   */
  public void setBinding(final Object boundedObject, final String boundedPropertyName) {
    final IObservableValue dateObservable = WidgetProperties.selection().observe(dateWidget);
    final IObservableValue timeObservable = WidgetProperties.selection().observe(timeWidget);
    DataBindings.createBinding(new DateAndTimeObservableValue(dateObservable, timeObservable), boundedObject, boundedPropertyName);
  }

  /**
   * Sets the enabled state of the date and time widgets.
   * 
   * @param enabled
   *          the enabled state
   */
  public void setEnabled(final boolean enabled) {
    dateWidget.setEnabled(enabled);
    timeWidget.setEnabled(enabled);
  }
}
