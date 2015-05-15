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
package ch.uzh.ifi.seal.permo.common.ui.view.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import ch.uzh.ifi.seal.permo.common.ui.view.colors.Colors;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridDataBuilder;

/**
 * A special extension of the {@link FallbackComposite} that provides a simple {@link Label} as fallback content. The
 * text of the label can be specified by concrete subclasses.
 */
public abstract class FallbackLabelComposite extends FallbackComposite {

  public FallbackLabelComposite(final Composite parent, final int style, final String fallbackMessage) {
    super(parent, style);
    setFallbackContent(fallbackMessage);
  }

  private void setFallbackContent(final String fallbackMessage) {
    final Label fallbackLabel = new Label(this, SWT.WRAP);
    fallbackLabel.setText(fallbackMessage);
    fallbackLabel.setBackground(Colors.WHITE);
    super.setFallbackContent(fallbackLabel, GridDataBuilder.singleCell().alignment(SWT.FILL).grabHorizontalSpace().create());
  }

  /**
   * The {@link #setFallbackContent(String)} method is not supported for {@link FallbackLabelComposite}'s, since the
   * fallback content is created by the {@link FallbackLabelComposite} itself.
   * 
   * {@inheritDoc}
   */
  @Override
  public void setFallbackContent(final Control fallbackContent, final GridData layoutData) {
    throw new UnsupportedOperationException();
  }

}
