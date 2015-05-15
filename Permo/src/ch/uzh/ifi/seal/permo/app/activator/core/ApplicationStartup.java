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
package ch.uzh.ifi.seal.permo.app.activator.core;

import ch.uzh.ifi.seal.permo.app.events.core.Events;

/**
 * This class is executed on plug-in startup in order to execute the required bootstrapping code.
 */
public class ApplicationStartup {

  private static final String VIEW_SYNCHRONIZER = "ch.uzh.ifi.seal.permo.history.main.ui.trigger.ProjectHistoryViewSynchronizer";

  private ApplicationStartup() {}

  public void startApplication() throws Exception {
    registerProjectHistoryViewSynchronizer();
  }

  /**
   * This method registers the ProjectHistoryViewSynchronizer which is responsible to open/fill/update/clear the Project
   * History View as soon as a time range is selected in the Performance View.
   * 
   * The class is manually loaded by its qualified name. This eliminates a dependency from the current package (the main
   * package containing the {@link Activator}) to any other package of the plug-in, which would result in cyclic
   * dependencies.
   */
  private void registerProjectHistoryViewSynchronizer() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    @SuppressWarnings("unchecked")
    final Class<Object> viewSynchronizerClass = (Class<Object>) Class.forName(VIEW_SYNCHRONIZER);
    Events.INSTANCE.subscribe(viewSynchronizerClass.newInstance());
  }

  /**
   * A static factory method to create a new instance of this class.
   * 
   * @return a new instance of {@link ApplicationStartup}
   */
  public static ApplicationStartup of() {
    return new ApplicationStartup();
  }

}
