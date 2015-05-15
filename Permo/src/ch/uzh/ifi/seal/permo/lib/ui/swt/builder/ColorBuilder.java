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
package ch.uzh.ifi.seal.permo.lib.ui.swt.builder;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * A builder for {@link Color}'s.
 */
public class ColorBuilder {

  private Display display;
  private RGB rgb;

  private ColorBuilder(final RGB rgb) {
    this.rgb = rgb;
    this.display = null;
  }

  /**
   * Sets the device on which to allocate the color
   * 
   * @param device
   *          the device on which to allocate the color
   * @return the current {@link ColorBuilder}
   */
  public ColorBuilder setDisplay(final Display device) {
    this.display = device;
    return this;
  }

  /**
   * Creates the {@link Color}.
   * 
   * @return the created {@link Color}
   */
  public Color create() {
    return new Color(display(), rgb);
  }

  private Display display() {
    return display != null ? display : Display.getCurrent();
  }

  /**
   * Creates a {@link ColorBuilder}.
   * 
   * @param r
   *          the R value of the color
   * @param g
   *          the G value of the color
   * @param b
   *          the B value of the color
   * @return the created {@link ColorBuilder}
   */
  public static ColorBuilder of(final int r, final int g, final int b) {
    return of(new RGB(r, g, b));

  }

  /**
   * Creates a {@link ColorBuilder}.
   * 
   * @param rgb
   *          the RGB value of the color
   * @return the created {@link ColorBuilder}
   */
  public static ColorBuilder of(final RGB rgb) {
    return new ColorBuilder(rgb);
  }
}
