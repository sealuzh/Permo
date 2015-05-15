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

import org.eclipse.swt.layout.RowData;

/**
 * A builder for {@link RowData}.
 */
public class RowDataBuilder {

  private RowData rowData;

  private RowDataBuilder() {
    this.rowData = new RowData();
  }

  /**
   * Sets {@link RowData#exclude} to <code>true</code>.
   * 
   * @return the current {@link RowDataBuilder}
   */
  public RowDataBuilder exclude() {
    this.rowData.exclude = true;
    return this;
  }

  /**
   * Sets {@link RowData#width} and {@link RowData#height}.
   * 
   * @param width
   *          the width (in pixels)
   * @param height
   *          the height (in pixels)
   * @return the current {@link RowDataBuilder}
   */
  public RowDataBuilder size(final int width, final int height) {
    width(width);
    height(height);
    return this;
  }

  /**
   * Sets {@link RowData#width}.
   * 
   * @param width
   *          the width (in pixels)
   * @return the current {@link RowDataBuilder}
   */
  public RowDataBuilder width(final int width) {
    this.rowData.width = width;
    return this;
  }

  /**
   * Sets {@link RowData#height}.
   * 
   * @param height
   *          the height (in pixels)
   * @return the current {@link RowDataBuilder}
   */
  public RowDataBuilder height(final int height) {
    this.rowData.height = height;
    return this;
  }

  /**
   * Creates the {@link RowData}.
   * 
   * @return the created {@link RowData}
   */
  public RowData create() {
    return this.rowData;
  }

  /**
   * Creates a {@link RowDataBuilder} with the given size.
   * 
   * @param width
   *          the width (in pixels)
   * @param height
   *          the height (in pixels)
   * @return the created {@link RowDataBuilder}
   */
  public static RowDataBuilder withSize(final int width, final int height) {
    return new RowDataBuilder().size(width, height);
  }

  /**
   * Creates a {@link RowDataBuilder} with the given width.
   * 
   * @param width
   *          the width (in pixels)
   * @return the created {@link RowDataBuilder}
   */
  public static RowDataBuilder withWidth(final int width) {
    return new RowDataBuilder().width(width);
  }

  /**
   * Creates a {@link RowDataBuilder} with the given height.
   * 
   * @param height
   *          the height (in pixels)
   * @return the created {@link RowDataBuilder}
   */
  public static RowDataBuilder withHeight(final int height) {
    return new RowDataBuilder().height(height);
  }

  /**
   * Creates a {@link RowDataBuilder} with {@link RowData#exclude} set to <code>true</code>.
   * 
   * @return the created {@link RowDataBuilder}
   */
  public static RowDataBuilder withExclude() {
    return new RowDataBuilder().exclude();
  }
}
