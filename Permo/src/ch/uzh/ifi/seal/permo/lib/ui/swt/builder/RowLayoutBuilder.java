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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;

/**
 * A builder for {@link RowLayout}'s.
 */
public class RowLayoutBuilder {

  private RowLayout rowLayout;

  private RowLayoutBuilder(final int direction) {
    this.rowLayout = new RowLayout(direction);
  }

  /**
   * Sets {@link RowLayout#center} to <code>true</code>.
   * 
   * @return the current {@link RowLayoutBuilder}
   */
  public RowLayoutBuilder center() {
    this.rowLayout.center = true;
    return this;
  }

  /**
   * Sets {@link RowLayout#fill} to <code>true</code>.
   * 
   * @return the current {@link RowLayoutBuilder}
   */
  public RowLayoutBuilder fill() {
    this.rowLayout.fill = true;
    return this;
  }

  /**
   * Sets {@link RowLayout#justify} to <code>true</code>.
   * 
   * @return the current {@link RowLayoutBuilder}
   */
  public RowLayoutBuilder justify() {
    this.rowLayout.justify = true;
    return this;
  }

  /**
   * Sets {@link RowLayout#wrap} to <code>false</code>.
   * 
   * @return the current {@link RowLayoutBuilder}
   */
  public RowLayoutBuilder noWrap() {
    this.rowLayout.wrap = false;
    return this;
  }

  /**
   * Sets {@link RowLayout#pack} to <code>false</code>.
   * 
   * @return the current {@link RowLayoutBuilder}
   */
  public RowLayoutBuilder noPack() {
    this.rowLayout.pack = false;
    return this;
  }

  /**
   * Sets {@link RowLayout#spacing} to zero.
   * 
   * @return the current {@link RowLayoutBuilder}
   */
  public RowLayoutBuilder noSpacing() {
    return spacing(0);
  }

  /**
   * Sets {@link RowLayout#spacing} to the given value.
   * 
   * @param spacing
   *          the value of the spacing
   * @return the current {@link RowLayoutBuilder}
   */
  public RowLayoutBuilder spacing(final int spacing) {
    this.rowLayout.spacing = spacing;
    return this;
  }

  /**
   * Sets the margin of the {@link RowLayout} to 0.
   * 
   * @return the current {@link RowLayoutBuilder}
   */
  public RowLayoutBuilder noMargin() {
    return margin(0);
  }

  /**
   * Sets the margin of the {@link RowLayout}.
   * 
   * @param margin
   *          the value of the margin (in pixels)
   * @return the current {@link RowLayoutBuilder}
   */
  public RowLayoutBuilder margin(final int margin) {
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
  public RowLayoutBuilder margin(final int marginHeight, final int marginWidth) {
    return margin(0, 0, 0, 0, marginHeight, marginWidth);
  }

  /**
   * Sets the margin of the {@link RowLayout}.
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
  public RowLayoutBuilder margin(final int marginTop, final int marginRight, final int marginBottom, final int marginLeft) {
    return margin(marginTop, marginRight, marginBottom, marginLeft, 0, 0);
  }

  /**
   * Sets the margin of the {@link RowLayout}.
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
   * @return
   */
  public RowLayoutBuilder margin(final int marginTop, final int marginRight, final int marginBottom, final int marginLeft, final int marginHeight, final int marginWidth) {
    this.rowLayout.marginTop = marginTop;
    this.rowLayout.marginRight = marginRight;
    this.rowLayout.marginBottom = marginBottom;
    this.rowLayout.marginLeft = marginLeft;
    this.rowLayout.marginHeight = marginHeight;
    this.rowLayout.marginWidth = marginWidth;
    return this;
  }

  /**
   * Creates the {@link RowLayout}.
   * 
   * @return the created {@link RowLayout}
   */
  public RowLayout create() {
    return this.rowLayout;
  }

  /**
   * Creates a builder for a {@link RowLayout} arranging its children in a row (i.e. horizontally).
   * 
   * @return a builder to create a {@link RowLayout}
   */
  public static RowLayoutBuilder row() {
    return new RowLayoutBuilder(SWT.HORIZONTAL);
  }

  /**
   * Creates a builder for a {@link RowLayout} arranging its children in a column (i.e. vertically).
   * 
   * @return a builder to create a {@link RowLayout}
   */
  public static RowLayoutBuilder column() {
    return new RowLayoutBuilder(SWT.VERTICAL);
  }
}
