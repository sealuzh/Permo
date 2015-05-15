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
package ch.uzh.ifi.seal.permo.performance.main.ui.trigger;

import org.eclipse.core.expressions.PropertyTester;

/**
 * In the plugin.xml file, a menu entry is contributed to the context menu of the Java editor to trigger the 'monitor
 * method' command. This class contains the logic to determine whether the menu entry shall be visible or not. It
 * proves, whether the cursor is positioned onto a method.
 */
public class IsMethodSelectedPropertyTester extends PropertyTester {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
    return SelectedMethods.getSelectedMethodInActiveEditor().isPresent();
  }

}
