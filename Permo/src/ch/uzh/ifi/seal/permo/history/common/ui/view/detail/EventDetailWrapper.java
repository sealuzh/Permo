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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import ch.uzh.ifi.seal.permo.common.ui.view.controls.FallbackLabelComposite;
import ch.uzh.ifi.seal.permo.history.common.ui.view.messages.Messages;
import ch.uzh.ifi.seal.permo.history.main.ui.viewmodel.ProjectHistoryViewViewModel;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridDataBuilder;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridLayoutBuilder;

/**
 * This control wraps the event detail control below the event list.
 * 
 * The wrapper listens for selection changes in the event list and updates the event detail control accordingly.
 */
public class EventDetailWrapper extends FallbackLabelComposite {

  private ProjectHistoryViewViewModel viewModel;

  private Composite content;

  public EventDetailWrapper(final Composite parent, final ProjectHistoryViewViewModel viewModel) {
    super(parent, SWT.NONE, Messages.SELECT_EVENT_MESSAGE);
    this.viewModel = viewModel;
    init();
  }

  @Override
  protected GridLayout createLayout() {
    return GridLayoutBuilder.oneColumn().noMargin().noSpacing().create();
  }

  private void init() {
    createContent();
    updateContent();
    registerListener();
  }

  private void registerListener() {
    viewModel.addPropertyChangeListener(new PropertyChangeListener() {
      @Override
      public void propertyChange(final PropertyChangeEvent event) {
        updateContent();
      }
    });
  }

  private void createContent() {
    content = new Composite(this, SWT.NONE);
    content.setLayout(GridLayoutBuilder.oneColumn().noMargin().noSpacing().create());
    setRealContent(content, GridDataBuilder.singleCell().alignment(SWT.FILL).grabSpace().create());
  }

  private void updateContent() {
    if (viewModel.getSelectedEvent() != null) {
      showRealContent();
      updateEventContent();
    }
    else {
      showFallbackContent();
    }
  }

  private void updateEventContent() {
    disposeContent();
    EventDetailContentProvider.INSTANCE.create(content, viewModel.getSelectedEvent()).setLayoutData(GridDataBuilder.singleCell().grabSpace().alignment(SWT.FILL).create());
    content.layout();
  }

  private void disposeContent() {
    for (final Control control : content.getChildren()) {
      control.dispose();
    }
  }
}
