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

import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.ui.dialogs.PreferencesUtil;

import ch.uzh.ifi.seal.permo.lib.ui.eclipse.WorkbenchUtil;

/**
 * A builder for {@link PreferenceDialog}'s.
 */
public class PreferenceDialogBuilder {

  private String shownPageId;
  private String[] displayedPageIds;
  private Object data;

  private PreferenceDialogBuilder() {
    this.shownPageId = null;
    this.displayedPageIds = null;
    this.data = null;
  }

  /**
   * Creates the {@link PreferenceDialog}.
   * 
   * @return the created {@link PreferenceDialog}
   */
  public PreferenceDialog create() {
    return PreferencesUtil.createPreferenceDialogOn(WorkbenchUtil.activeShell(), shownPageId, displayedPageIds, data);
  }

  /**
   * Specify the filter of the {@link PreferenceDialog}.
   * 
   * @param pageIds
   *          the ID's of the preference pages that shall be included in the {@link PreferenceDialog}
   * @return the current {@link PreferenceDialogBuilder}
   */
  public PreferenceDialogBuilder filter(final String... pageIds) {
    this.displayedPageIds = pageIds;
    return this;
  }

  /**
   * Specifies the page that shall be visible when opening the {@link PreferenceDialog}.
   * 
   * @param pageId
   *          the ID of the page that shall be visible
   * @return the current {@link PreferenceDialogBuilder}
   */
  public PreferenceDialogBuilder show(final String pageId) {
    this.shownPageId = pageId;
    return this;
  }

  /**
   * Specifies a single page that shall be included and visible in the {@link PreferenceDialog}.
   * 
   * @param pageId
   *          the ID of the page to be included in the {@link PreferenceDialog}
   * @return the current {@link PreferenceDialogBuilder}
   */
  public PreferenceDialogBuilder showOnly(final String pageId) {
    show(pageId);
    filter(new String[] { pageId });
    return this;
  }

  /**
   * Creates a new builder for {@link PreferenceDialog}'s.
   * 
   * @return a new builder for {@link PreferenceDialog}'s.
   */
  public static PreferenceDialogBuilder of() {
    return new PreferenceDialogBuilder();
  }

}
