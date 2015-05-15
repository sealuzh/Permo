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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridDataBuilder;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridLayoutBuilder;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.TimeSeriesCompositeViewModel;

/**
 * Provides a composite displaying a time series chart and the corresponding toolbar on top.
 */
public class TimeSeriesComposite extends Composite {

  private static final String TITLE_PATTERN = "%s - %s";

  private TimeSeriesCompositeViewModel viewModel;
  private TimeSeriesChart timeSeriesChart;
  private Composite toolbar;

  public TimeSeriesComposite(final Composite parent, final TimeSeriesCompositeViewModel viewModel) {
    super(parent, SWT.NONE);
    this.viewModel = viewModel;
    setLayout();
    createContent();
    registerListener();
  }

  private final void setLayout() {
    setLayout(GridLayoutBuilder.oneColumn().noMargin().noSpacing().create());
  }

  private final void createContent() {
    createToolbar();
    createChart();
  }

  private final void createToolbar() {
    toolbar = new TimeSeriesToolbar(this, viewModel.getToolbar());
    toolbar.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.FILL).grabHorizontalSpace().create());
  }

  private final void createChart() {
    timeSeriesChart = new TimeSeriesChart(this, viewModel.getChart());
    timeSeriesChart.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.FILL).grabSpace().create());
    setTitle();
  }

  private void registerListener() {
    viewModel.addPropertyChangeListener(TimeSeriesCompositeViewModel.PROPERTY__MONITORING_DATA, new PropertyChangeListener() {

      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        setTitle();
      }
    });
  }

  private void setTitle() {
    if (!viewModel.isEmpty()) {
      final String project = viewModel.getMonitoringData().getProject().toString();
      final String procedure = viewModel.getMonitoringData().getProcedure().toShortString();
      timeSeriesChart.setTitle(String.format(TITLE_PATTERN, project, procedure));
    }
  }

}
