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
package ch.uzh.ifi.seal.permo.common.ui.view;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import ch.uzh.ifi.seal.permo.app.activator.core.Activator;
import ch.uzh.ifi.seal.permo.lib.ui.eclipse.DefaultPartListener;
import ch.uzh.ifi.seal.permo.lib.ui.eclipse.WorkbenchUtil;

/**
 * A base implementation for eclipse views.
 * 
 * Mainly handles the lifecycle of the view and provides appropriate hook methods that can be overridden by subclasses
 * in order to execute code on state changes.
 */
public abstract class AbstractView extends ViewPart {

  private final String ID_SEPARATOR = ":";

  protected AbstractView() {
    super();
    registerPartListener();
  }

  /**
   * This method registers a listener that observes the lifecycle of the view and calls the appropriate methods if the
   * view gets visible or gets hidden.
   */
  private void registerPartListener() {
    WorkbenchUtil.activePage().addPartListener(new DefaultPartListener() {
      @Override
      public void partVisible(final IWorkbenchPartReference partRef) {
        if (isSelf(partRef)) {
          onVisible();
        }
      }

      @Override
      public void partHidden(final IWorkbenchPartReference partRef) {
        if (isSelf(partRef)) {
          onHidden();
        }
      }

      private boolean isSelf(final IWorkbenchPartReference partRef) {
        return AbstractView.this.equals(partRef.getPart(false));
      }
    });
  }

  /**
   * Is called when the view gets visible.
   */
  protected void onVisible() {}

  /**
   * Is called when the view gets hidden.
   */
  protected void onHidden() {}

  /**
   * {@inheritDoc}
   */
  @Override
  public void init(final IViewSite site) throws PartInitException {
    super.init(site);
    loadState(getSettings());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    saveState(getSettings());
    super.dispose();
  }

  /**
   * Loads and returns the settings of the current view. If there is no section for the current view in the settigns
   * store, a new one is created.
   * 
   * @return the settings of the current view
   */
  protected IDialogSettings getSettings() {
    final String viewId = getCompleteId();
    final IDialogSettings settings = Activator.getDefault().getDialogSettings();
    IDialogSettings section = settings.getSection(viewId);
    if (section == null) {
      section = settings.addNewSection(viewId);
    }
    return section;
  }

  /**
   * Can be overridden by subclasses to load the view's persisted state. Is called in
   * {@link AbstractView#init(IViewSite)}.
   */
  protected void loadState(final IDialogSettings settings) {

  }

  /**
   * Can be overridden by subclasses to persist the view's state. Is called in {@link AbstractView#dispose()}.
   */
  protected void saveState(final IDialogSettings settings) {

  }

  /**
   * Adds the given {@link IAction}'s to the view's pull down menu.
   * 
   * @param actions
   *          the actions to be added to the pull down menu
   */
  protected final void fillPullDownMenu(final IAction... actions) {
    fill(getPullDownManager(), actions);
  }

  /**
   * Adds the given {@link IAction}'s to the view's toolbar.
   * 
   * @param actions
   *          the actions to be added to the toolbar
   */
  protected final void fillToolbar(final IAction... actions) {
    fill(getToolbarManager(), actions);
  }

  private void fill(final IContributionManager contributionManager, final IAction... actions) {
    for (final IAction action : actions) {
      contributionManager.add(action);
    }
  }

  private IMenuManager getPullDownManager() {
    return getActionBars().getMenuManager();
  }

  private IToolBarManager getToolbarManager() {
    return getActionBars().getToolBarManager();
  }

  private IActionBars getActionBars() {
    return getViewSite().getActionBars();
  }

  private String getCompleteId() {
    return this.getViewSite().getId() + ID_SEPARATOR + this.getViewSite().getSecondaryId();
  }

}
