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
package ch.uzh.ifi.seal.permo.common.ui.viewmodel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Base class for view models storing state. Provides methods to add {@link PropertyChangeListener}'s in order to
 * implement data binding between views and view models
 * (http://www.vogella.com/tutorials/EclipseDataBinding/article.html).
 */
public class AbstractStatefulViewModel {

  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

  /**
   * Register a {@link PropertyChangeListener} that is notified when any property of the concrete view model changes.
   * 
   * @param listener
   *          the {@link PropertyChangeListener} to be registered.
   */
  public final void addPropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }

  /**
   * Remove a {@link PropertyChangeListener} that has been registered before.
   * 
   * @param listener
   *          the {@link PropertyChangeListener} to be removed.
   */
  public final void removePropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  /**
   * Register a {@link PropertyChangeListener} that is notified when the property with the given name changes.
   * 
   * @param propertyName
   *          the name of the property to be observed
   * @param listener
   *          the {@link PropertyChangeListener} to be registered
   */
  public final void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }

  /**
   * Remove a {@link PropertyChangeListener} registered before to observe the property with the given name.
   * 
   * @param propertyName
   *          the name of the property observed by the {@link PropertyChangeListener} that shall be removed.
   * @param listener
   *          the {@link PropertyChangeListener} to be removed.
   */
  public final void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }

  /**
   * Fires a property changed event for the given property. The new value is compared to the old value and an event is
   * only fired if the two are distinct.
   * 
   * This method should be called in every method of concrete view models that change a property.
   * 
   * @param propertyName
   *          the name of the property
   * @param oldValue
   *          the old value of the property
   * @param newValue
   *          the new value of the property
   */
  protected final void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
  }

}
