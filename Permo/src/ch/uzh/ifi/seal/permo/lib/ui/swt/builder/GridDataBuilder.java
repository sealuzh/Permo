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

import org.eclipse.swt.layout.GridData;

/**
 * A builder for {@link GridData}'s.
 */
public class GridDataBuilder {

  private GridData gridData;

  private GridDataBuilder() {
    this.gridData = new GridData();
  }

  private GridDataBuilder(final int horizontalSpan, final int verticalSpan) {
    this();
    this.gridData.horizontalSpan = horizontalSpan;
    this.gridData.verticalSpan = verticalSpan;
  }

  /**
   * Sets the alignment of the {@link GridData}.
   * 
   * @param aligment
   *          the value of the horizontal- and vertical alignment
   * @return the current {@link GridDataBuilder}
   */
  public GridDataBuilder alignment(final int aligment) {
    return alignment(aligment, aligment);
  }

  /**
   * Sets the alignment of the {@link GridData}.
   * 
   * @param horizontalAligment
   *          the value of the horizontal alignment
   * @param verticalAlignment
   *          the value of the vertical alignment
   * @return the current {@link GridDataBuilder}
   */
  public GridDataBuilder alignment(final int horizontalAligment, final int verticalAlignment) {
    this.gridData.horizontalAlignment = horizontalAligment;
    this.gridData.verticalAlignment = verticalAlignment;
    return this;
  }

  /**
   * Sets {@link GridData#grabExcessHorizontalSpace} and {@link GridData#grabExcessVerticalSpace} to <code>true</code>.
   * 
   * @return the current {@link GridDataBuilder}
   */
  public GridDataBuilder grabSpace() {
    grabHorizontalSpace();
    grabVerticalSpace();
    return this;
  }

  /**
   * Sets {@link GridData#widthHint}.
   * 
   * @param width
   *          the width
   * @return the current {@link GridDataBuilder}
   */
  public GridDataBuilder width(final int width) {
    this.gridData.widthHint = width;
    return this;
  }

  /**
   * Sets {@link GridData#heightHint}.
   * 
   * @param height
   *          the height
   * @return the current {@link GridDataBuilder}
   */
  public GridDataBuilder height(final int height) {
    this.gridData.heightHint = height;
    return this;
  }

  /**
   * Sets {@link GridData#widthHint} and {@link GridData#heightHint}.
   * 
   * @param width
   *          the width
   * @param height
   *          the height
   * @return the current {@link GridDataBuilder}
   */
  public GridDataBuilder size(final int width, final int height) {
    width(width);
    height(height);
    return this;
  }

  /**
   * Sets {@link GridData#grabExcessHorizontalSpace} to <code>true</code>.
   * 
   * @return the current {@link GridDataBuilder}
   */
  public GridDataBuilder grabHorizontalSpace() {
    this.gridData.grabExcessHorizontalSpace = true;
    return this;
  }

  /**
   * Sets {@link GridData#grabExcessVerticalSpace} to <code>true</code>.
   * 
   * @return the current {@link GridDataBuilder}
   */
  public GridDataBuilder grabVerticalSpace() {
    this.gridData.grabExcessVerticalSpace = true;
    return this;
  }

  /**
   * Creates the {@link GridData}.
   * 
   * @return the created {@link GridData}
   */
  public GridData create() {
    return gridData;
  }

  /**
   * Creates the {@link GridData} that spans a single cell.
   * 
   * @return the created {@link GridDataBuilder}
   */
  public static GridDataBuilder singleCell() {
    return new GridDataBuilder();
  }

  /**
   * Creates a {@link GridData} that spans a single row and the given number of columns.
   * 
   * @param numberOfColumns
   *          the number of columns (i.e. {@link GridData#horizontalSpan})
   * @return the created {@link GridDataBuilder}
   */
  public static GridDataBuilder singleRow(final int numberOfColumns) {
    return new GridDataBuilder(numberOfColumns, 1);
  }

  /**
   * Creates a {@link GridData} that spans a single column and the given number of rows.
   * 
   * @param numberOfRows
   *          the number of rows (i.e. {@link GridData#verticalSpan})
   * @return the created {@link GridDataBuilder}
   */
  public static GridDataBuilder singleColumn(final int numberOfRows) {
    return new GridDataBuilder(1, numberOfRows);
  }

  /**
   * Creates a {@link GridData} that spans multiple rows and columns.
   * 
   * @param numberOfColumns
   *          the number of columns (i.e. {@link GridData#horizontalSpan})
   * @param numberOfRows
   *          the number of rows (i.e. {@link GridData#verticalSpan})
   * @return the created {@link GridDataBuilder}
   */
  public static GridDataBuilder multipleCell(final int numberOfColumns, final int numberOfRows) {
    return new GridDataBuilder(numberOfColumns, numberOfRows);
  }

}
