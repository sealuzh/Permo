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
package ch.uzh.ifi.seal.permo.common.ui.trigger;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import ch.uzh.ifi.seal.permo.lib.ui.eclipse.WorkbenchUtil;

/**
 * This class provides an abstract base implementation for view triggers. A view trigger is responsible to check whether
 * the view shall be updated or cleaned and whether it is already active and visible or not. For each particular
 * situation, the respective command is triggered (see {@link #execute()}.
 */
public abstract class AbstractViewTrigger<V extends ViewPart> {

  /**
   * Executes the the appropriate actions according to the current state of the view (inactive, active, visible) and the
   * availability of content to be shown in the view:
   * <ul>
   * <li>View is inactive and content is available -> open view, update content</li>
   * <li>View is inactive and content is not available -> no action</li>
   * <li>View is active, but not visible and content is available -> make visible, update content</li>
   * <li>View is active and visible and content is available -> update content</li>
   * <li>View is active and content is not available -> clear content</li>
   * </ul>
   */
  @SuppressWarnings("unchecked")
  public final void execute() {
    IViewPart view = getCurrentView();
    if (isContentAvailable()) {
      if (view == null) {
        try {
          if (allowMultipleViews()) {
            view = WorkbenchUtil.activePage().showView(viewId(), secondaryViewId(), IWorkbenchPage.VIEW_VISIBLE);
          }
          else {
            view = WorkbenchUtil.activePage().showView(viewId());
          }
        }
        catch (final PartInitException e) {
          e.printStackTrace();
        }
      }
      else {
        WorkbenchUtil.activePage().bringToTop(view);
      }
      updateContent((V) view);
    }
    else if (view != null) {
      clearContent((V) view);
    }
  }

  private IViewPart getCurrentView() {
    if (allowMultipleViews()) {
      final IViewReference ref = WorkbenchUtil.activePage().findViewReference(viewId(), secondaryViewId());
      return ref != null ? ref.getView(false) : null;
    }
    return WorkbenchUtil.activePage().findView(viewId());
  }

  /**
   * Subclasses have to return the id of the view that shall be triggered.
   * 
   * @return the id of the view that shall be triggered
   */
  protected abstract String viewId();

  /**
   * Defines whether it is possible to open multiple instances of the view at the same time.
   * 
   * @return <code>true</code> if multiple instances of this view can be displayed at the same time, <code>false</code>
   *         otherwise
   */
  protected abstract boolean allowMultipleViews();

  /**
   * Returns the secondary ID of the view that shall be triggered. This is only required if
   * {@link #allowMultipleViews()} returns <code>true</code>.
   * 
   * @return the secondary ID of the view that shall be triggered
   */
  protected abstract String secondaryViewId();

  /**
   * Checks whether there is content available to fill the view.
   * 
   * @return <code>true</code> if content is available, <code>false</code> otherwise
   */
  protected abstract boolean isContentAvailable();

  /**
   * Action to take in order to update the content of the view.
   */
  protected abstract void updateContent(V view);

  /**
   * Action to take in order to clear the view if no content is available.
   */
  protected abstract void clearContent(V view);

}
