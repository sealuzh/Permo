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
package ch.uzh.ifi.seal.permo.history.common.ui.view.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import ch.uzh.ifi.seal.permo.history.git.ui.viewmodel.table.DateTimeColumnLabelProvider;
import ch.uzh.ifi.seal.permo.history.git.ui.viewmodel.table.TitleColumnLabelProvider;
import ch.uzh.ifi.seal.permo.lib.ui.jface.AbstractDefaultTableViewer;

/**
 * The project history table displaying all events.
 */
public final class ProjectHistoryTable extends AbstractDefaultTableViewer {

  public ProjectHistoryTable(final Composite parent) {
    super(parent);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createColumns() {
    createColumn(new DateTimeColumnLabelProvider(), SWT.LEFT);
    createColumn(new TitleColumnLabelProvider(), SWT.LEFT);
  }

}
