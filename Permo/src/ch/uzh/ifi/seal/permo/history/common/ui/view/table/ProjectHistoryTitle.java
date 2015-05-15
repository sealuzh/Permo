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
package ch.uzh.ifi.seal.permo.history.common.ui.view.table;

import static ch.uzh.ifi.seal.permo.lib.core.stdlib.DateTimes.format;

import java.time.LocalDateTime;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.Project;
import ch.uzh.ifi.seal.permo.common.ui.util.LayoutConstants;
import ch.uzh.ifi.seal.permo.lib.ui.eclipse.Fonts;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridDataBuilder;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridLayoutBuilder;

/**
 * The title of the project history view. It includes information about the considered project and the selected time
 * range.
 */
public class ProjectHistoryTitle extends Composite {

  private static final String HYPHEN = "-";
  private static final String DATE_PATTERN = "dd.MM.yy";
  private static final String TIME_PATTERN = "HH:mm:ss";

  private static final int NR_OF_CLUMNS = 4;
  private static final int VERTICAL_SPACING = 0;
  private static final int ONE_COLUMN = 1;
  private static final int TWO_ROWS = 2;

  private Label title;
  private Label startDate;
  private Label endDate;
  private Label startTime;
  private Label endTime;

  public ProjectHistoryTitle(final Composite parent, final int style) {
    super(parent, SWT.NONE);
    init();
  }

  private void init() {
    createLayout();
    createContent();
    update();
  }

  private void createLayout() {
    setLayout(GridLayoutBuilder.columns(NR_OF_CLUMNS).noMargin().spacing(LayoutConstants.SPACING, VERTICAL_SPACING).create());
  }

  private void createContent() {
    title = new Label(this, SWT.WRAP);
    title.setLayoutData(GridDataBuilder.multipleCell(ONE_COLUMN, TWO_ROWS).alignment(SWT.LEFT, SWT.CENTER).grabHorizontalSpace().create());
    Fonts.style(title, true, LayoutConstants.FONT_SIZE_BIG);

    startDate = new Label(this, SWT.NONE);
    startDate.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.RIGHT, SWT.FILL).create());
    Fonts.bold(startDate);

    final Label hyphen = new Label(this, SWT.NONE);
    hyphen.setLayoutData(GridDataBuilder.multipleCell(ONE_COLUMN, TWO_ROWS).alignment(SWT.RIGHT, SWT.CENTER).create());
    hyphen.setText(HYPHEN);
    Fonts.style(hyphen, true, LayoutConstants.FONT_SIZE_BIG);

    endDate = new Label(this, SWT.NONE);
    endDate.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.LEFT, SWT.FILL).create());
    Fonts.bold(endDate);

    startTime = new Label(this, SWT.NONE);
    startTime.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.RIGHT, SWT.FILL).create());
    Fonts.size(startTime, LayoutConstants.FONT_SIZE_SMALL);

    endTime = new Label(this, SWT.NONE);
    endTime.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.LEFT, SWT.FILL).create());
    Fonts.size(endTime, LayoutConstants.FONT_SIZE_SMALL);
  }

  public void update(final Project project, final LocalDateTime startDateTime, final LocalDateTime endDateTime) {
    title.setText(project.getName());
    startDate.setText(format(startDateTime, DATE_PATTERN));
    endDate.setText(format(endDateTime, DATE_PATTERN));
    startTime.setText(format(startDateTime, TIME_PATTERN));
    endTime.setText(format(endDateTime, TIME_PATTERN));
    layout();
  }
}
