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
package ch.uzh.ifi.seal.permo.app.preferences.ui;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import ch.uzh.ifi.seal.permo.app.preferences.core.Preferences;
import ch.uzh.ifi.seal.permo.app.preferences.ui.messages.Messages;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridDataBuilder;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.GridLayoutBuilder;
import ch.uzh.ifi.seal.permo.lib.ui.swt.builder.StringFieldEditorBuilder;

/**
 * The main Permo preference page.
 */
public class PermoPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

  private static final String REGEX_NUMBER = "^[1-9]\\d*$";
  private static final String SECONDS = " [s]";

  public PermoPreferencePage() {
    super(GRID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void init(final IWorkbench workbench) {
    setPreferenceStore(Preferences.getStore());
    setDescription(Messages.PREFERENCE_PAGE__DESCRIPTION);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createFieldEditors() {
    final FieldEditorGroup performanceGroup = createGroup(Messages.PERFORMANCE_VIEW);
    addField(StringFieldEditorBuilder.of(Preferences.PERMO__UPDATE_INTERVAL, Messages.UPDATE_INTERVAL + SECONDS, performanceGroup.getContentArea())
        .withValidation(REGEX_NUMBER, NLS.bind(Messages.INVALID_UPDATE_INTERVAL_MSG, Messages.UPDATE_INTERVAL)).create());

    final FieldEditorGroup historyGroup = createGroup(Messages.HISTORY_VIEW);
    addField(new BooleanFieldEditor(Preferences.PERMO__FETCH_AUTOMATICALLY, Messages.FETCH_AUTOMATICALLY, historyGroup.getContentArea()));
  }

  private FieldEditorGroup createGroup(final String title) {
    return new FieldEditorGroup(getFieldEditorParent(), title);
  }

  private static class FieldEditorGroup {

    private static final int MARGIN = 5;
    private static final int SPACING = 10;
    private static final int COLUMNS = 2;
    private static final int ROWS = 1;

    private final Composite contentArea;

    public FieldEditorGroup(final Composite parent, final String title) {
      final Group group = initGroup(parent, title);
      this.contentArea = initContentArea(group);
    }

    private Group initGroup(final Composite parent, final String title) {
      final Group group = new Group(parent, SWT.SHADOW_ETCHED_IN);
      group.setText(title);
      group.setLayout(GridLayoutBuilder.oneColumn().margin(MARGIN).spacing(SPACING).create());
      group.setLayoutData(GridDataBuilder.multipleCell(COLUMNS, ROWS).alignment(SWT.FILL).grabHorizontalSpace().create());
      return group;
    }

    private Composite initContentArea(final Group group) {
      final Composite contentArea = new Composite(group, SWT.NONE);
      contentArea.setLayout(GridLayoutBuilder.columns(COLUMNS).create());
      contentArea.setLayoutData(GridDataBuilder.singleCell().alignment(SWT.FILL).grabSpace().create());
      return contentArea;
    }

    public Composite getContentArea() {
      return contentArea;
    }

  }

}
