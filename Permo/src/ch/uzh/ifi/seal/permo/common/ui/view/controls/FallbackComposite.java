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

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * This class provides an abstract base implementation for {@link Composite}'s that have a 'real' content an a
 * 'fallback' if the real content is not available.
 * 
 * Both the real and the fallback {@link Control}'s shall be created at startup and based on the available data to fill
 * the real content, it can be decided by concrete subclasses when to show the real content and when to show the
 * fallback content.
 * 
 * This avoids disposing all the {@link Control}'s of the real content each time no data is available. Disposing
 * {@link Control}'s can sometimes be very complicated, due to registered listeners that try to access to the disposed
 * {@link Control}'s.
 */
public abstract class FallbackComposite extends Composite {

  private Control realContent;
  private Control fallbackContent;
  private GridData realContentLayoutData;
  private GridData fallbackContentLayoutData;

  public FallbackComposite(final Composite parent, final int style) {
    super(parent, style);
    init();
  }

  private void init() {
    setLayout(createLayout());
  }

  /**
   * Creates the layout of the {@link Composite}.
   * 
   * @return the {@link GridLayout} of the {@link Composite}
   */
  protected abstract GridLayout createLayout();

  /**
   * Sets the real content of the {@link Composite}.
   * 
   * @param realContent
   *          the real content
   * @param layoutData
   *          the layout data of the real content
   */
  public void setRealContent(final Control realContent, final GridData layoutData) {
    this.realContent = realContent;
    this.realContentLayoutData = layoutData;
    this.realContent.setParent(this);
    this.realContent.setLayoutData(this.realContentLayoutData);
  }

  /**
   * Sets the fallback Content of the {@link Composite}.
   * 
   * @param fallbackContent
   *          the fallback content
   * @param layoutData
   *          the layout data of the fallback content
   */
  public void setFallbackContent(final Control fallbackContent, final GridData layoutData) {
    this.fallbackContent = fallbackContent;
    this.fallbackContentLayoutData = layoutData;
    this.fallbackContent.setParent(this);
    this.fallbackContent.setLayoutData(this.fallbackContentLayoutData);
  }

  /**
   * Returns the real content.
   * 
   * @return the {@link Control} containing the real content
   */
  public Control getRealContent() {
    return realContent;
  }

  /**
   * Returns the fallback content.
   * 
   * @return the {@link Control} containing the fallback content
   */
  public Control getFallbackContent() {
    return fallbackContent;
  }

  /**
   * Brings the real content to front. The fallback content is hided.
   */
  public void showRealContent() {
    this.fallbackContent.setVisible(false);
    this.fallbackContentLayoutData.exclude = true;
    this.realContent.setVisible(true);
    this.realContentLayoutData.exclude = false;
    layout();
  }

  /**
   * Brings the fallback content to front. The real content is hided.
   */
  public void showFallbackContent() {
    this.realContent.setVisible(false);
    this.realContentLayoutData.exclude = true;
    this.fallbackContent.setVisible(true);
    this.fallbackContentLayoutData.exclude = false;
    layout();
  }

}
