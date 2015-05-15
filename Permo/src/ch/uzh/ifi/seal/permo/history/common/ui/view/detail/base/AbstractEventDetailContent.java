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
package ch.uzh.ifi.seal.permo.history.common.ui.view.detail.base;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

import ch.uzh.ifi.seal.permo.history.common.ui.viewmodel.ProjectHistoryEventViewModel;

/**
 * Abstract base implementation for event detail controls.
 * 
 * @param <VM>
 *          the type of the underlying view model (subtype of {@link ProjectHistoryEventViewModel})
 */
public abstract class AbstractEventDetailContent<VM extends ProjectHistoryEventViewModel> extends Composite {

  private VM viewModel;

  public AbstractEventDetailContent(final Composite parent, final VM viewModel) {
    super(parent, SWT.NONE);
    init(viewModel);
  }

  private void init(final VM viewModel) {
    this.viewModel = viewModel;
    setLayout(createLayout());
  }

  public abstract Layout createLayout();

  public VM getViewModel() {
    return viewModel;
  }

}
