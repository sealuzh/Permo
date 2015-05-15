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
package ch.uzh.ifi.seal.permo.history.common.ui.view.detail;

import java.util.Map;

import org.eclipse.swt.widgets.Composite;

import ch.uzh.ifi.seal.permo.history.common.ui.view.detail.base.AbstractEventDetailContent;
import ch.uzh.ifi.seal.permo.history.common.ui.view.detail.base.EventDetailContentFactory;
import ch.uzh.ifi.seal.permo.history.common.ui.viewmodel.ProjectHistoryEventViewModel;
import ch.uzh.ifi.seal.permo.history.git.ui.view.detail.CommitDetailContentFactory;
import ch.uzh.ifi.seal.permo.history.git.ui.viewmodel.CommitViewModel;

import com.google.common.collect.Maps;

/**
 * Provides the appropriate event detail control for a given type of project event.
 * 
 * The event detail control is the control that appears below the event list when the user selected an event.
 */
public class EventDetailContentProvider {

  public static EventDetailContentProvider INSTANCE = new EventDetailContentProvider();

  @SuppressWarnings("rawtypes")
  private Map<Class, EventDetailContentFactory> map;

  private EventDetailContentProvider() {
    this.map = Maps.newHashMap();
    fillMap();
  }

  private void fillMap() {
    map.put(CommitViewModel.class, new CommitDetailContentFactory());
  }

  /**
   * Creates a new event detail control based on the type of the given view model.
   * 
   * @param parent
   *          the parent composite of the detail control to be created
   * @param viewModel
   *          the underlying view model
   * @return the created event detail control
   */
  @SuppressWarnings("unchecked")
  public <VM extends ProjectHistoryEventViewModel> AbstractEventDetailContent<VM> create(final Composite parent, final VM viewModel) {
    final EventDetailContentFactory<VM> factory = map.get(viewModel.getClass());
    final AbstractEventDetailContent<VM> content = factory.create(parent, viewModel);
    return content;
  }

}
