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
package ch.uzh.ifi.seal.permo.history.git.ui.view.detail;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;

import ch.uzh.ifi.seal.permo.common.ui.util.LayoutConstants;
import ch.uzh.ifi.seal.permo.history.common.ui.view.detail.base.AbstractEventDetailContent;
import ch.uzh.ifi.seal.permo.history.git.ui.viewmodel.CommitViewModel;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.DateTimes;
import ch.uzh.ifi.seal.permo.lib.ui.eclipse.Fonts;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridDataBuilder;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridLayoutBuilder;

/**
 * An event detail control to display information about a commit.
 */
public class CommitDetailContent extends AbstractEventDetailContent<CommitViewModel> {

  private static final String DATE_TIME_PATTERN = "dd.MM.yy HH:mm:ss";
  private static final int NR_OF_COLUMNS = 2;
  private static final int TITLE_NR_OF_COLUMNS = 2;

  private static final int TWO_COLUMN = 2;
  private static final int ONE_ROWS = 1;

  public CommitDetailContent(final Composite parent, final CommitViewModel commitViewModel) {
    super(parent, commitViewModel);
    createContent();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Layout createLayout() {
    return GridLayoutBuilder.columns(NR_OF_COLUMNS).noMargin().spacing(LayoutConstants.SPACING).create();
  }

  private void createContent() {
    createHeader();
    createTitle();
    createTable();
  }

  private void createHeader() {
    final Label dateTimeLabel = new Label(this, SWT.NONE);
    dateTimeLabel.setText(DateTimes.format(getViewModel().getDateTime(), DATE_TIME_PATTERN));
    dateTimeLabel.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.LEFT).grabHorizontalSpace().create());
    Fonts.size(dateTimeLabel, LayoutConstants.FONT_SIZE_SMALL);

    final Label auhtorLabel = new Label(this, SWT.NONE);
    auhtorLabel.setText(getViewModel().getAuthor().getName());
    auhtorLabel.setToolTipText(getViewModel().getAuthor().toString());
    auhtorLabel.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.RIGHT).create());
    Fonts.size(auhtorLabel, LayoutConstants.FONT_SIZE_SMALL);

  }

  private void createTitle() {
    final Composite title = new Composite(this, SWT.NONE);
    title.setLayoutData(GridDataBuilder.multipleCell(TWO_COLUMN, ONE_ROWS).alignment(SWT.LEFT).grabHorizontalSpace().create());
    title.setLayout(GridLayoutBuilder.columns(TITLE_NR_OF_COLUMNS).noMargin().spacing(LayoutConstants.SPACING).create());

    final Label idLabel = new Label(title, SWT.NONE);
    idLabel.setText(getViewModel().getId());
    idLabel.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.LEFT).create());

    final Label messageLabel = new Label(title, SWT.WRAP);
    messageLabel.setText(getViewModel().getTitle());
    messageLabel.setToolTipText(getViewModel().getFullMessage());
    messageLabel.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.FILL).grabHorizontalSpace().create());
    Fonts.bold(messageLabel);
  }

  private void createTable() {
    final FileDiffTable table = new FileDiffTable(this);
    table.getTable().setLayoutData(GridDataBuilder.multipleCell(TWO_COLUMN, ONE_ROWS).alignment(SWT.FILL).grabSpace().create());
    table.update(getViewModel().getFileDiffs());
  }

}
