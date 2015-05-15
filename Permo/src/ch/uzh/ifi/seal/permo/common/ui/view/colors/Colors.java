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
package ch.uzh.ifi.seal.permo.common.ui.view.colors;

import org.eclipse.swt.graphics.Color;

import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.ColorBuilder;

/**
 * Provides constants of commonly used colors.
 */
public class Colors {

  private Colors() {}

  /**
   * The dark blue of the Permo color scheme.
   */
  public static Color BLUE_DARK = ColorBuilder.of(23, 73, 133).create();

  /**
   * The light blue of the Permo color scheme.
   */
  public static Color BLUE_LIGHT = ColorBuilder.of(117, 167, 226).create();

  /**
   * Black.
   */
  public static Color BLACK = ColorBuilder.of(0, 0, 0).create();

  /**
   * White.
   */
  public static Color WHITE = ColorBuilder.of(255, 255, 255).create();

  /**
   * Gray.
   */
  public static Color GRAY = ColorBuilder.of(200, 200, 200).create();

  /**
   * The color used to show error messages (red).
   */
  public static Color ERROR = ColorBuilder.of(138, 41, 41).create();

}
