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
package ch.uzh.ifi.seal.permo.lib.ui.eclipse;

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * Utility methods to deal with fonts.
 */
public class Fonts {

  /**
   * Sets the font of the given {@link Control} to bold.
   * 
   * @param control
   *          the {@link Control}
   * @return the modified {@link Control}
   */
  public static <C extends Control> C bold(final C control) {
    final FontDescriptor descriptor = getFontDescriptor(control).setStyle(SWT.BOLD);
    return setFont(control, descriptor);
  }

  /**
   * Sets the font size of the given {@link Control}.
   * 
   * @param control
   *          the {@link Control}
   * @param height
   *          the desired height (in pixels) of the font
   * @return the modified {@link Control}
   */
  public static <C extends Control> C size(final C control, final int height) {
    final FontDescriptor descriptor = getFontDescriptor(control).setHeight(height);
    return setFont(control, descriptor);
  }

  /**
   * Sets the font style of the given {@link Control}.
   * 
   * @param control
   *          the {@link Control}
   * @param bold
   *          a boolean specifying whether the font shall be bold (<code>true</code>) or not (<code>false</code>)
   * @param height
   *          the desired height (in pixels) of the font
   * @return the modified {@link Control}
   */
  public static <C extends Control> C style(final C control, final boolean bold, final int height) {
    if (bold) {
      bold(control);
    }
    return size(control, height);
  }

  private static FontDescriptor getFontDescriptor(final Control control) {
    return FontDescriptor.createFrom(control.getFont());
  }

  private static <C extends Control> C setFont(final C control, final FontDescriptor descriptor) {
    control.setFont(descriptor.createFont(Display.getCurrent()));
    return control;
  }
}
