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

import org.eclipse.swt.widgets.Composite;

import ch.uzh.ifi.seal.permo.history.common.ui.viewmodel.ProjectHistoryEventViewModel;

/**
 * Defines a factory for event detail controls. Concrete implementations are provided in the respective sub components
 * of 'permo.history'.
 * 
 * @param <VM>
 *          the type of the underlying view model (subtype of {@link ProjectHistoryEventViewModel})
 */
public interface EventDetailContentFactory<VM extends ProjectHistoryEventViewModel> {

  /**
   * Creates a new event detail control.
   * 
   * @param parent
   *          the parent composite of the new control
   * @param viewModel
   *          the underlying view model
   * @return the created event detail control
   */
  public AbstractEventDetailContent<VM> create(Composite parent, VM viewModel);

}
