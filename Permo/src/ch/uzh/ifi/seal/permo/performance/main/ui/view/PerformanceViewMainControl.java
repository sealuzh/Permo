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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import ch.uzh.ifi.seal.permo.common.ui.util.LayoutConstants;
import ch.uzh.ifi.seal.permo.common.ui.view.controls.FallbackLabelComposite;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridDataBuilder;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridLayoutBuilder;
import ch.uzh.ifi.seal.permo.performance.main.ui.view.messages.Messages;
import ch.uzh.ifi.seal.permo.performance.main.ui.viewmodel.PerformanceViewViewModel;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.view.TimeSeriesComposite;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.TimeSeriesCompositeViewModel;

/**
 * Defines the content of the Performance View.
 */
public class PerformanceViewMainControl extends FallbackLabelComposite {

  private final PerformanceViewViewModel viewModel;

  private TimeSeriesComposite timeSeriesComposite;

  public PerformanceViewMainControl(final Composite parent, final PerformanceViewViewModel viewModel) {
    super(parent, SWT.NONE, Messages.SELECT_METHOD_MESSAGE);
    this.viewModel = viewModel;
    init();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected GridLayout createLayout() {
    return GridLayoutBuilder.oneColumn().margin(LayoutConstants.VIEW_MARGIN).noSpacing().create();
  }

  private void init() {
    createContent();
    updateContent();
    registerListener();
  }

  private void createContent() {
    timeSeriesComposite = new TimeSeriesComposite(this, viewModel.getTimeSeriesComposite());
    setRealContent(timeSeriesComposite, GridDataBuilder.singleCell().alignment(SWT.FILL).grabSpace().create());
  }

  /**
   * Updates the content according to the linked viewmodel.
   */
  public void updateContent() {
    if (!viewModel.getTimeSeriesComposite().isEmpty()) {
      showRealContent();
    }
    else {
      showFallbackContent();
    }
  }

  private void registerListener() {
    viewModel.getTimeSeriesComposite().addPropertyChangeListener(TimeSeriesCompositeViewModel.PROPERTY__MONITORING_DATA, new PropertyChangeListener() {
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        updateContent();
      }
    });
  }

}
