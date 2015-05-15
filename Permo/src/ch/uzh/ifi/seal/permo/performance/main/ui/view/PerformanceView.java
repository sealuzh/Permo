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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;

import ch.uzh.ifi.seal.permo.app.activator.core.Activator;
import ch.uzh.ifi.seal.permo.app.preferences.ui.PreferencePages;
import ch.uzh.ifi.seal.permo.common.ui.view.AbstractView;
import ch.uzh.ifi.seal.permo.common.ui.view.colors.Colors;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.ActionBuilder;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.PreferenceDialogBuilder;
import ch.uzh.ifi.seal.permo.performance.main.ui.view.messages.Messages;
import ch.uzh.ifi.seal.permo.performance.main.ui.viewmodel.PerformanceViewViewModel;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.MonitoringData;

/**
 * This is the main class of the Performance View. It handles all the functionality related to Eclipse view parts and
 * delegates the rendering of the actual content of the view to {@link PerformanceViewMainControl}.
 **/
public class PerformanceView extends AbstractView {

  private static final String ICON__LIVE_MODE = "icons/button.livemode.png";
  private static final String ICON__SAVE = "icons/button.save.png";

  private final PerformanceViewViewModel viewModel;

  private PerformanceViewMainControl main;

  public PerformanceView() {
    super();
    this.viewModel = new PerformanceViewViewModel();
  }

  /**
   * This is a callback that allows to create the viewer and initialize it.
   */
  @Override
  public void createPartControl(final Composite parent) {
    createLayout(parent);
    createContent(parent);
    fillToolbar();
    fillPullDownMenu();
  }

  private void createLayout(final Composite parent) {
    parent.setBackground(Colors.WHITE);
  }

  private void createContent(final Composite parent) {
    this.main = new PerformanceViewMainControl(parent, viewModel);
  }

  /**
   * Update the content of the view.
   * 
   * @param monitoringData
   *          the monitoring data to be visualized in the view
   */
  public void updateView(final MonitoringData monitoringData) {
    this.viewModel.setMonitoringData(monitoringData);
  }

  /**
   * Clear the content of the view.
   */
  public void clearView() {
    this.main.showFallbackContent();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void loadState(final IDialogSettings settings) {
    new PerformanceViewSettings(settings).loadState(viewModel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void saveState(final IDialogSettings settings) {
    new PerformanceViewSettings(getSettings()).saveState(viewModel);
  }

  private void fillPullDownMenu() {
    fillPullDownMenu(ActionBuilder.of(Messages.ACTION_OPEN_PREFERENCES, () -> {
      PreferenceDialogBuilder.of().showOnly(PreferencePages.PERMO).create().open();
    }).create());
  }

  private void fillToolbar() {
    final ImageDescriptor liveIcon = Activator.getImageDescriptor(ICON__LIVE_MODE);
    final IAction liveAction = ActionBuilder.of(Messages.ACTION__LIVE_MODE, IAction.AS_CHECK_BOX, () -> viewModel.toggleLiveModeEnabled()).setChecked(viewModel.isLiveModeEnabled()).setIcon(liveIcon)
        .create();
    viewModel.addPropertyChangeListener(PerformanceViewViewModel.PROPERTY__LIVE_MODE_ENABLED, new PropertyChangeListener() {
      @Override
      public void propertyChange(final PropertyChangeEvent event) {
        liveAction.setChecked(viewModel.isLiveModeEnabled());
      }
    });
    fillToolbar(liveAction);

    final ImageDescriptor saveIcon = Activator.getImageDescriptor(ICON__SAVE);
    final IAction saveAction = ActionBuilder.of(Messages.ACTION__SAVE, IAction.AS_CHECK_BOX, () -> {
      viewModel.toggleSafeMode();
    }).setChecked(viewModel.isSafeModeEnabled()).setIcon(saveIcon).create();
    viewModel.addPropertyChangeListener(PerformanceViewViewModel.PROPERTY__SAFE_MODE, new PropertyChangeListener() {
      @Override
      public void propertyChange(final PropertyChangeEvent event) {
        saveAction.setChecked(viewModel.isSafeModeEnabled());
      }
    });
    fillToolbar(saveAction);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void onVisible() {
    viewModel.onVisible();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void onHidden() {
    viewModel.onHidden();
  }

  /**
   * Passing the focus request to the viewer's main control.
   */
  @Override
  public void setFocus() {
    main.setFocus();
  }
}
