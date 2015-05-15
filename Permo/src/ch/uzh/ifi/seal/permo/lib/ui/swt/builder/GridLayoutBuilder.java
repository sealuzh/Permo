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

import org.eclipse.swt.layout.GridLayout;

/**
 * A builder to create {@link GridLayout}'s.
 */
public class GridLayoutBuilder {

  private GridLayout gridLayout;

  private GridLayoutBuilder(final int numberOfColumns, final boolean equalWidth) {
    this.gridLayout = new GridLayout(numberOfColumns, equalWidth);
  }

  /**
   * Sets the margin of the {@link GridLayout} to 0.
   * 
   * @return the current {@link GridLayoutBuilder}
   */
  public GridLayoutBuilder noMargin() {
    return margin(0);
  }

  /**
   * Sets the margin of the {@link GridLayout}.
   * 
   * @param margin
   *          the value of the margin (in pixels)
   * @return the current {@link GridLayoutBuilder}
   */
  public GridLayoutBuilder margin(final int margin) {
    return margin(margin, margin);
  }

  /**
   * Sets the margin of the {@link GridLayout}.
   * 
   * @param marginHeight
   *          the value of the height margin (in pixels)
   * @param marginWidth
   *          the value of the width margin (in pixels)
   * @return the current {@link GridLayoutBuilder}
   */
  public GridLayoutBuilder margin(final int marginHeight, final int marginWidth) {
    return margin(0, 0, 0, 0, marginHeight, marginWidth);
  }

  /**
   * Sets the margin of the {@link GridLayout}. Important: The margins are added together. For example a leftMargin of 5
   * and a widthMargin results in a 10 pixel margin at the left side of the control.
   * 
   * @param marginTop
   *          the value of the top margin (in pixels)
   * @param marginRight
   *          the value of the right margin (in pixels)
   * @param marginBottom
   *          the value of the bottom margin (in pixels)
   * @param marginLeft
   *          the value of the left margin (in pixels)
   * @return the current {@link GridLayoutBuilder}
   */
  public GridLayoutBuilder margin(final int marginTop, final int marginRight, final int marginBottom, final int marginLeft) {
    return margin(marginTop, marginRight, marginBottom, marginLeft, 0, 0);
  }

  /**
   * Sets the margin of the {@link GridLayout}. Important: The margins are added together. For example a leftMargin of 5
   * and a widthMargin results in a 10 pixel margin at the left side of the control.
   * 
   * @param marginTop
   *          the value of the top margin (in pixels)
   * @param marginRight
   *          the value of the right margin (in pixels)
   * @param marginBottom
   *          the value of the bottom margin (in pixels)
   * @param marginLeft
   *          the value of the left margin (in pixels)
   * @param marginHeight
   *          the value of the height margin (in pixels)
   * @param marginWidth
   *          the value of the width margin (in pixels)
   * @return the current {@link GridLayoutBuilder}
   */
  public GridLayoutBuilder margin(final int marginTop, final int marginRight, final int marginBottom, final int marginLeft, final int marginHeight, final int marginWidth) {
    this.gridLayout.marginTop = marginTop;
    this.gridLayout.marginRight = marginRight;
    this.gridLayout.marginBottom = marginBottom;
    this.gridLayout.marginLeft = marginLeft;
    this.gridLayout.marginHeight = marginHeight;
    this.gridLayout.marginWidth = marginWidth;
    return this;
  }

  /**
   * Sets the spacing of the {@link GridLayout} to 0.
   * 
   * @return the current {@link GridLayoutBuilder}
   */
  public GridLayoutBuilder noSpacing() {
    return spacing(0);
  }

  /**
   * Sets the spacing of the {@link GridLayout}.
   * 
   * @param spacing
   *          the value of the horizontal- and vertical spacing (in pixels)
   * @return the current {@link GridLayoutBuilder}
   */
  public GridLayoutBuilder spacing(final int spacing) {
    return spacing(spacing, spacing);
  }

  /**
   * Sets the spacing of the {@link GridLayout}.
   * 
   * @param horizontalSpacing
   *          the value of the horizontal spacing (in pixels)
   * @param verticalSpacing
   *          the value of the vertical spacing (in pixels)
   * @return
   */
  public GridLayoutBuilder spacing(final int horizontalSpacing, final int verticalSpacing) {
    this.gridLayout.horizontalSpacing = horizontalSpacing;
    this.gridLayout.verticalSpacing = verticalSpacing;
    return this;
  }

  /**
   * Creates the {@link GridLayout}.
   * 
   * @return the created {@link GridLayout}
   */
  public GridLayout create() {
    return this.gridLayout;
  }

  /**
   * Creates a builder for a {@link GridLayout} with the given number of columns.
   * 
   * @param number
   *          the number of columns
   * @return a builder to create a {@link GridLayout}
   */
  public static GridLayoutBuilder columns(final int number) {
    return of(number, false);
  }

  /**
   * Creates a builder for a {@link GridLayout} with one column.
   * 
   * @return a builder to create a {@link GridLayout}
   */
  public static GridLayoutBuilder oneColumn() {
    return of(1, false);
  }

  /**
   * Creates a builder for a {@link GridLayout} with the given number of equal-width columns.
   * 
   * @param number
   *          the number of columns
   * @return a builder to create a {@link GridLayout}
   */
  public static GridLayoutBuilder equalWidthColumns(final int number) {
    return of(number, true);
  }

  /**
   * Creates a builder for a {@link GridLayout} with the given number of columns.
   * 
   * @param numberOfColumns
   *          the number of columns
   * @param equalWidth
   *          a boolean indicating whether the columns shall have equal width
   * @return a builder to create a {@link GridLayout}
   */
  public static GridLayoutBuilder of(final int numberOfColumns, final boolean equalWidth) {
    return new GridLayoutBuilder(numberOfColumns, equalWidth);
  }
}
