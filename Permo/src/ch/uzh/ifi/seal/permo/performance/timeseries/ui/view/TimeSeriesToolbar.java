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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ch.uzh.ifi.seal.permo.app.activator.core.Activator;
import ch.uzh.ifi.seal.permo.common.ui.util.LayoutConstants;
import ch.uzh.ifi.seal.permo.common.ui.view.colors.Colors;
import ch.uzh.ifi.seal.permo.lib.core.eclipse.DataBindings;
import ch.uzh.ifi.seal.permo.lib.ui.eclipse.Fonts;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.RowDataBuilder;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.RowLayoutBuilder;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.view.messages.Messages;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.SelectedRangeViewModel;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.TimeSeriesCompositeViewModel;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.TimeSeriesToolbarViewModel;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.VisibleRangeViewModel;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.VisibleRangeViewModel.TimeRange;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.listener.SelectedRangeChangeListener;

/**
 * Provides a toolbar for a time series chart.
 */
public class TimeSeriesToolbar extends Composite {

  private static final String EMPTY = "";
  private static final String COLON = ":";
  private static final String HYPHEN = "-";
  private static final String ICON_TODAY = "icons/button.today.png";
  private static final int GAP_WIDTH__SMALL = 10;
  private static final int GAP_WIDTH__LARGE = 50;

  private TimeSeriesToolbarViewModel viewModel;

  private DateTimeWidget dateTimeWidget;

  public TimeSeriesToolbar(final Composite parent, final TimeSeriesToolbarViewModel viewModel) {
    super(parent, SWT.NONE);
    this.viewModel = viewModel;
    setLayout();
    createContent();
    registerListeners();
  }

  private void setLayout() {
    setLayout(RowLayoutBuilder.row().noMargin().spacing(LayoutConstants.SPACING).center().create());
  }

  private void createContent() {
    createBoldLabel(Messages.VISIBLE_AREA + COLON);
    createIntervalCombo();
    createLabel(Messages.ENDING);
    dateTimeWidget = createDateTimeWidget(viewModel.getVisibleRange(), VisibleRangeViewModel.PROPERTY__END_DATE);
    createGap(GAP_WIDTH__SMALL);
    createTodayButton();
    createGap(GAP_WIDTH__LARGE);
    createBoldLabel(Messages.SELECTED_AREA + COLON);
    createDateTimeWidget(viewModel.getSelectedRange(), SelectedRangeViewModel.PROPERTY__START_DATE);
    createLabel(HYPHEN);
    createDateTimeWidget(viewModel.getSelectedRange(), SelectedRangeViewModel.PROPERTY__END_DATE);
    createErrorLabel();
  }

  private void createIntervalCombo() {
    final ComboViewer intervalComboViewer = new ComboViewer(this, SWT.NONE);
    intervalComboViewer.setContentProvider(ArrayContentProvider.getInstance());
    intervalComboViewer.setInput(TimeRange.values());
    final IViewerObservableValue intervalComboViewerObservable = ViewersObservables.observeSingleSelection(intervalComboViewer);
    DataBindings.createBinding(intervalComboViewerObservable, viewModel.getVisibleRange(), VisibleRangeViewModel.PROPERTY__RANGE);
  }

  private void createTodayButton() {
    final Button todayButton = new Button(this, SWT.NONE);
    todayButton.setImage(Activator.getImageDescriptor(ICON_TODAY).createImage());
    todayButton.setToolTipText(Messages.TODAY_BUTTON__TOOLTIP);
    todayButton.addSelectionListener(new SelectionAdapter() {
      @Override
      public void widgetSelected(final SelectionEvent event) {
        viewModel.showUpToDateRange();
      }
    });
  }

  private DateTimeWidget createDateTimeWidget(final Object boundedObject, final String boundedPropertyName) {
    final DateTimeWidget dateTimeWidget = new DateTimeWidget(this);
    dateTimeWidget.setBinding(boundedObject, boundedPropertyName);
    return dateTimeWidget;
  }

  private Label createErrorLabel() {
    final Label validationLabel = createLabel(EMPTY);
    validationLabel.setForeground(Colors.ERROR);

    viewModel.getSelectedRange().addPropertyChangeListener(new SelectedRangeChangeListener() {
      @Override
      public void selectedRangeChanged() {
        if (viewModel.getSelectedRange().hasSelection() && !viewModel.getSelectedRange().hasValidSelection()) {
          validationLabel.setText(Messages.END_NOT_AFTER_STAR_ERROR_MSG);
          validationLabel.getParent().layout();
        }
        else {
          validationLabel.setText(EMPTY);
        }
      }
    });

    return validationLabel;
  }

  private Label createLabel(final String text) {
    final Label label = new Label(this, SWT.NONE);
    label.setText(text);
    return label;
  }

  private Label createBoldLabel(final String text) {
    return Fonts.bold(createLabel(text));
  }

  private void createGap(final int width) {
    new Composite(this, SWT.NONE).setLayoutData(RowDataBuilder.withSize(width, 1).create());
  }

  private void registerListeners() {
    viewModel.getParent().addPropertyChangeListener(TimeSeriesCompositeViewModel.PROPERTY__LIVE_MODE, new PropertyChangeListener() {
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        dateTimeWidget.setEnabled(!viewModel.getParent().getLiveMode());
      }
    });
  }

}
