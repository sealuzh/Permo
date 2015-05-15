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

import org.eclipse.swt.widgets.Composite;

import ch.uzh.ifi.seal.permo.common.ui.view.AbstractView;
import ch.uzh.ifi.seal.permo.common.ui.view.colors.Colors;
import ch.uzh.ifi.seal.permo.history.common.core.model.ProjectHistoryData;
import ch.uzh.ifi.seal.permo.history.main.ui.viewmodel.ProjectHistoryViewViewModel;

/**
 * This is the eclipse view displaying the time series containing performance related data. It handles all the
 * functionality related to Eclipse view parts and delegates the rendering of the actual content of the view to
 * {@link ProjectHistoryViewMainControl}.
 */
public class ProjectHistoryView extends AbstractView {

  private ProjectHistoryViewViewModel viewModel;

  private ProjectHistoryViewMainControl main;

  public ProjectHistoryView() {
    super();
    this.viewModel = new ProjectHistoryViewViewModel();
  }

  /**
   * This is a callback that allows to create the viewer and initialize it.
   */
  @Override
  public void createPartControl(final Composite parent) {
    setLayout(parent);
    createContent(parent);
  }

  private void setLayout(final Composite parent) {
    parent.setBackground(Colors.WHITE);
  }

  private void createContent(final Composite parent) {
    this.main = new ProjectHistoryViewMainControl(parent, viewModel);
  }

  /**
   * Updates the view.
   * 
   * @param data
   *          the new data visualized in the view
   */
  public void updateView(final ProjectHistoryData data) {
    viewModel.setProjectHistoryData(data);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setFocus() {
    main.setFocus();
  }
}
