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
import java.text.DecimalFormat;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.swtchart.Chart;
import org.swtchart.IAxis;
import org.swtchart.ILineSeries;
import org.swtchart.ILineSeries.PlotSymbolType;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.Range;

import ch.uzh.ifi.seal.permo.common.ui.view.colors.Colors;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.TimeSeriesChartViewModel;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.TimeSeriesViewModel;
import ch.uzh.ifi.seal.permo.performance.timeseries.ui.viewmodel.listener.SelectedRangeChangeListener;

/**
 * This is an extension of the {@link Chart} class provided by the 'SWTCHhrt' library customized for the usage in Permo.
 * Amongst others, a selection mechanism is provided to select a range of the whole time series.
 */
public class TimeSeriesChart extends Chart {

  private static final String HASHTAG = "#";
  private static final String X_AXIS__TITLE = "point in time";
  private static final String Y_AXIS__TITLE = "execution time";

  private TimeSeriesChartViewModel viewModel;

  private ILineSeries series;

  public TimeSeriesChart(final Composite parent, final TimeSeriesChartViewModel viewModel) {
    super(parent, SWT.NONE);
    this.viewModel = viewModel;
    create();
  }

  /**
   * Sets the title of the chart.
   * 
   * @param title
   *          the title of the chart
   */
  public void setTitle(final String title) {
    getTitle().setText(title);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setForeground(final Color color) {
    getTitle().setForeground(Colors.BLACK);
    for (final IAxis axis : getAxisSet().getAxes()) {
      axis.getTitle().setForeground(color);
      axis.getTick().setForeground(color);
    }
    super.setForeground(color);
  }

  /**
   * Computes the {@link Date} corresponding to the given coordinate inside the time series chart.
   * 
   * @param pixelCoordinate
   *          the coordinate in pixels
   * @return the {@link Date} corresponding to the given coordinate inside the time series chart
   */
  private Date getDateTimeOfCoordinate(final int pixelCoordinate) {
    final double xVal = getAxisSet().getXAxis(0).getDataCoordinate(pixelCoordinate);
    final long timestamp = Math.round(xVal);
    return new Date(timestamp);
  }

  /**
   * Computes the coordinate (in pixels) corresponding to the given {@link Date}
   * 
   * @param date
   *          the {@link Date}
   * @return the coordinate (in pixels) corresponding to the given {@link Date}
   */
  private int getCoordinateOfDateTime(final Date date) {
    final long timestamp = date.getTime();
    return getAxisSet().getXAxis(0).getPixelCoordinate(timestamp);
  }

  private void create() {
    createLayout();
    updateSeries();
    addListener();
  }

  private void createLayout() {
    getLegend().setPosition(SWT.BOTTOM);
    setForeground(Colors.BLACK);
  }

  private void addListener() {
    getPlotArea().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseDown(final MouseEvent event) {
        viewModel.mouseDown(getDateTimeOfCoordinate(event.x));
      }

      @Override
      public void mouseUp(final MouseEvent event) {
        viewModel.mouseUp();
      }

      @Override
      public void mouseDoubleClick(final MouseEvent event) {
        viewModel.mouseDoubleClick();
      }
    });

    getPlotArea().addMouseMoveListener(new MouseMoveListener() {
      @Override
      public void mouseMove(final MouseEvent event) {
        viewModel.mouseMove(getDateTimeOfCoordinate(event.x));
      }
    });

    viewModel.getSelectedRange().addPropertyChangeListener(new SelectedRangeChangeListener() {

      @Override
      public void selectedRangeChanged() {
        redrawPlotArea();
      }
    });

    // is called after each invocation of the redraw-method of the plot-area
    getPlotArea().addListener(SWT.Paint, new Listener() {

      @Override
      public void handleEvent(final Event event) {
        drawSelectionRectangle(event.gc);
      }
    });

    viewModel.getTimeSeries().addPropertyChangeListener(TimeSeriesViewModel.PROPERTY__TIME_SERIES, new PropertyChangeListener() {
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        Display.getDefault().asyncExec(new Runnable() {
          @Override
          public void run() {
            updateSeries();
          }
        });
      }
    });
  }

  private void redrawPlotArea() {
    getPlotArea().redraw();
  }

  /*
   * Draws the selection rectangle when the user selects an area.
   */
  private void drawSelectionRectangle(final GC gc) {
    if (viewModel.getSelectedRange().hasValidSelection()) {
      gc.setBackground(Colors.BLUE_DARK);
      gc.setAlpha(128);
      final int y = 0;
      final int height = getPlotArea().getBounds().height;
      final int startDateCoordinate = getCoordinateOfDateTime(viewModel.getSelectedRange().getStartDate());
      final int endDateCoordinate = getCoordinateOfDateTime(viewModel.getSelectedRange().getEndDate());
      gc.fillRectangle(startDateCoordinate, y, endDateCoordinate - startDateCoordinate, height);

    }
  }

  private void updateSeries() {
    if (series != null) {
      getSeriesSet().deleteSeries(series.getId());
    }
    if (!viewModel.getTimeSeries().isEmpty()) {
      series = createSeries();
    }
    adjustAndRedraw();
  }

  /*
   * Creates a new series based on the values in the viewmodel.
   */
  private ILineSeries createSeries() {
    series = (ILineSeries) getSeriesSet().createSeries(SeriesType.LINE, viewModel.getTimeSeries().getTitleWithUnit());
    series.setLineColor(Colors.BLUE_DARK);
    final IAxis yAxis = getAxisSet().getYAxis(series.getYAxisId());
    final String axisTickFormat = HASHTAG + viewModel.getTimeSeries().getUnit().getAbbreviation();
    yAxis.getTick().setFormat(new DecimalFormat(axisTickFormat));
    yAxis.getTitle().setText(Y_AXIS__TITLE);
    series.setSymbolType(PlotSymbolType.NONE);
    series.setXDateSeries(viewModel.getTimeSeries().getTimeValues());
    series.setYSeries(viewModel.getTimeSeries().getValues());
    return series;
  }

  /*
   * Adjusts the range of the chart according the visible values and redraws the area.
   */
  public void adjustAndRedraw() {
    adjustRange();
    redraw();
  }

  /*
   * Adjusts the range of the visible area.
   */
  private void adjustRange() {
    getAxisSet().adjustRange();
    adjustXAxes();
  }

  /*
   * Adjusts the y-axes according to the specified visible area. Normally the SWTChart is adjusted according to the
   * available values (min, max), which of course is inappropriate as the user can specify the area.
   */
  private void adjustXAxes() {
    final Date endDate = viewModel.getVisibleRange().getEndDate();
    final Date startDate = viewModel.getVisibleRange().getStartDate();
    for (final IAxis axis : getAxisSet().getXAxes()) {
      axis.setRange(new Range(startDate.getTime(), endDate.getTime()));
      axis.getTitle().setText(X_AXIS__TITLE);
    }
  }
}
