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
package ch.uzh.ifi.seal.permo.lib.ui.jface;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

/**
 * An abstract implementation of a {@link TableViewer} providing typical settings and additional methods to create
 * columns.
 */
public abstract class AbstractDefaultTableViewer extends TableViewer {

  public AbstractDefaultTableViewer(final Composite parent) {
    super(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
    init();
  }

  private final void init() {
    createContentProvider();
    createLayout();
    createColumns();
  }

  private final void createContentProvider() {
    setContentProvider(new ArrayContentProvider());
  }

  private final void createLayout() {
    getTable().setLinesVisible(true);
  }

  /**
   * Has to be overridden by subclasses in order to create the desired columns.
   */
  protected abstract void createColumns();

  /**
   * Creates a new {@link TableViewerColumn} attached of the current {@link TableViewer}. Can be used by subclasses to
   * create columns in {@link #createColumns()}.
   * 
   * @param labelProvider
   *          the {@link ColumnLabelProvider}
   * @param style
   *          the style
   * @return the created {@link TableViewerColumn}
   */
  protected final TableViewerColumn createColumn(final ColumnLabelProvider labelProvider, final int style) {
    final TableViewerColumn column = new TableViewerColumn(this, style);
    column.setLabelProvider(labelProvider);
    return column;
  }

  /**
   * Updates the input of the tables and resizes the columns accordingly.
   * 
   * @param input
   *          the new input
   */
  public void update(final Object input) {
    setInput(input);
    packColumns();
  }

  private void packColumns() {
    for (int i = 0; i < getTable().getColumnCount(); i++) {
      getTable().getColumns()[i].pack();
    }
  }

}
