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
package ch.uzh.ifi.seal.permo.history.main.ui.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import ch.uzh.ifi.seal.permo.common.ui.util.LayoutConstants;
import ch.uzh.ifi.seal.permo.common.ui.view.controls.FallbackLabelComposite;
import ch.uzh.ifi.seal.permo.history.common.ui.view.detail.EventDetailWrapper;
import ch.uzh.ifi.seal.permo.history.common.ui.view.messages.Messages;
import ch.uzh.ifi.seal.permo.history.common.ui.view.table.ProjectHistoryTable;
import ch.uzh.ifi.seal.permo.history.common.ui.view.table.ProjectHistoryTitle;
import ch.uzh.ifi.seal.permo.history.common.ui.viewmodel.ProjectHistoryEventViewModel;
import ch.uzh.ifi.seal.permo.history.main.ui.viewmodel.ProjectHistoryViewViewModel;
import ch.uzh.ifi.seal.permo.lib.core.stdlib.TimePeriod;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridDataBuilder;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridLayoutBuilder;

/**
 * Defines the content of the Project History View.
 */
public class ProjectHistoryViewMainControl extends FallbackLabelComposite {

  private final ProjectHistoryViewViewModel viewModel;

  private ProjectHistoryTitle title;
  private ProjectHistoryTable projectHistoryTable;

  public ProjectHistoryViewMainControl(final Composite parent, final ProjectHistoryViewViewModel viewModel) {
    super(parent, SWT.NONE, Messages.SELECT_RANGE_MESSAGE);
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
    registerListener();
    createContent();
    updateContent();
  }

  private void createContent() {
    final SashForm sashForm = new SashForm(this, SWT.VERTICAL);
    setRealContent(sashForm, GridDataBuilder.singleCell().alignment(SWT.FILL).grabSpace().create());

    final Composite overviewBox = new Composite(sashForm, SWT.NONE);
    overviewBox.setLayout(GridLayoutBuilder.oneColumn().noMargin().spacing(LayoutConstants.SPACING).create());

    title = new ProjectHistoryTitle(overviewBox, SWT.NONE);
    title.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.FILL).grabHorizontalSpace().create());

    projectHistoryTable = new ProjectHistoryTable(overviewBox);
    projectHistoryTable.getTable().setLayoutData(GridDataBuilder.singleCell().alignment(SWT.FILL).grabSpace().create());
    projectHistoryTable.addSelectionChangedListener(new ISelectionChangedListener() {
      @Override
      public void selectionChanged(final SelectionChangedEvent event) {
        if (event.getSelection() instanceof IStructuredSelection) {
          final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
          if (selection.getFirstElement() instanceof ProjectHistoryEventViewModel) {
            viewModel.setSelectedEvent((ProjectHistoryEventViewModel) selection.getFirstElement());
          }
        }
      }
    });

    new EventDetailWrapper(sashForm, viewModel);
  }

  private void updateContent() {
    if (!viewModel.isEmpty()) {
      updateHistoryContent();
      showRealContent();
    }
    else {
      showFallbackContent();
    }
  }

  private void updateHistoryContent() {
    updateTitle();
    updateTable();
  }

  private void updateTitle() {
    final TimePeriod period = viewModel.getProjectHistoryData().getTimePeriod();
    title.update(viewModel.getProjectHistoryData().getProject(), period.getStartDateTime(), period.getEndDateTime());
  }

  private void updateTable() {
    projectHistoryTable.update(viewModel.getEvents());
  }

  private void registerListener() {
    viewModel.addPropertyChangeListener(ProjectHistoryViewViewModel.PROPERTY__PROJECT_HISTORY_DATA, new PropertyChangeListener() {
      @Override
      public void propertyChange(final PropertyChangeEvent event) {
        updateContent();
      }
    });
    viewModel.addPropertyChangeListener(ProjectHistoryViewViewModel.PROPERTY__EVENTS, new PropertyChangeListener() {
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        updateTable();
      }
    });
  }
}
