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
package ch.uzh.ifi.seal.permo.lib.core.eclipse;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;

/**
 * Provides utility functionality to deal with data bindings.
 */
public class DataBindings {

  /**
   * Creates a new data binding between the given {@link IObservableValue} and the given property of the given object.
   * 
   * @param widgetObservable
   *          the {@link IObservableValue} of the UI element to be bound.
   * @param boundedObject
   *          the object holding the property to be bound
   * @param boundedPropertyName
   *          the name of the property to be bound
   */
  public static void createBinding(final IObservableValue widgetObservable, final Object boundedObject, final String boundedPropertyName) {
    final DataBindingContext dataBindingContext = new DataBindingContext();
    final IObservableValue viewModelObservable = BeanProperties.value(boundedPropertyName).observe(boundedObject);
    dataBindingContext.bindValue(widgetObservable, viewModelObservable);
  }

}
