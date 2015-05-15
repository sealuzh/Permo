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

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * Furthermore, it provides a singleton instance of itself to the other components in order to load plug-in specific
 * resources (e.g. images, settings).
 */
public class Activator extends AbstractUIPlugin {

  /**
   * The id of the plug-in.
   * 
   * IMPORTANT: Has to be in sync with the 'Bundle-SymbolicName' property of the MANIFEST.MF. Otherwise the
   * {@link Activator#getImageDescriptor(String)} method does not work.
   */
  public static final String PLUGIN_ID = "ch.uzh.ifi.seal.permo";

  private static Activator plugin;

  public Activator() {}

  /**
   * {@inheritDoc}
   */
  @Override
  public void start(final BundleContext context) throws Exception {
    super.start(context);
    ApplicationStartup.of().startApplication();
    plugin = this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stop(final BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance of the plug-in activator.
   *
   * @return the shared instance of the plug-in activator
   */
  public static Activator getDefault() {
    return plugin;
  }

  /**
   * Returns an image descriptor for the image file at the given plug-in relative path.
   *
   * @param path
   *          the path
   * @return the image descriptor
   */
  public static ImageDescriptor getImageDescriptor(final String path) {
    return imageDescriptorFromPlugin(PLUGIN_ID, path);
  }
}
