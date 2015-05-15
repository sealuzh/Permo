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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * A builder for {@link IAction}'s.
 */
public class ActionBuilder {

  private IAction action;

  private ActionBuilder(final ActionHandler handler) {
    this.action = new Action() {
      @Override
      public void run() {
        handler.execute();
      }
    };
  }

  private ActionBuilder(final String text, final int style, final ActionHandler handler) {
    this.action = new Action(text, style) {
      @Override
      public void run() {
        handler.execute();
      }
    };
  }

  /**
   * Sets the text of the action that is shown in the UI.
   * 
   * @param text
   *          the text of the action
   * @return the current {@link ActionBuilder}
   */
  public ActionBuilder setText(final String text) {
    action.setText(text);
    return this;
  }

  /**
   * Sets the icon of the action that is shown in the UI.
   * 
   * @param imageDescriptor
   *          the icon of the action
   * @return the current {@link ActionBuilder}
   */
  public ActionBuilder setIcon(final ImageDescriptor imageDescriptor) {
    action.setImageDescriptor(imageDescriptor);
    return this;
  }

  /**
   * Sets the {@link IAction#isChecked()}.
   * 
   * @param checked
   *          a boolean specifying the checked-property of the {@link IAction}.
   * @return
   */
  public ActionBuilder setChecked(final boolean checked) {
    action.setChecked(checked);
    return this;
  }

  /**
   * Creates the {@link IAction}.
   * 
   * @return the created {@link IAction}
   */
  public IAction create() {
    return action;
  }

  /**
   * Creates a new {@link ActionBuilder}.
   * 
   * @param text
   *          the text of the action
   * @param style
   *          the style of the button
   * @param handler
   *          the code to be executed by the action
   * @return the created {@link ActionBuilder}
   */
  public static ActionBuilder of(final String text, final int style, final ActionHandler handler) {
    final ActionBuilder actionBuilder = new ActionBuilder(text, style, handler);
    return actionBuilder;
  }

  /**
   * Creates a new {@link ActionBuilder}.
   * 
   * @param text
   *          the text of the action
   * @param handler
   *          the code to be executed by the action
   * @return the created {@link ActionBuilder}
   */
  public static ActionBuilder of(final String text, final ActionHandler handler) {
    final ActionBuilder actionBuilder = of(handler);
    actionBuilder.setText(text);
    return actionBuilder;
  }

  /**
   * Creates a new {@link ActionBuilder}.
   * 
   * @param handler
   *          the code to be executed by the action
   * @return the created {@link ActionBuilder}
   */
  public static ActionBuilder of(final ActionHandler handler) {
    return new ActionBuilder(handler);
  }

  /**
   * An {@link ActionHandler} defines the code that is executed by an action.
   */
  @FunctionalInterface
  public static interface ActionHandler {
    public void execute();
  }
}
