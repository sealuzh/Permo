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
package ch.uzh.ifi.seal.permo.app.events.core;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Implementation of {@link Events} making use of the {@link EventBus} of Google Guava:
 * https://code.google.com/p/guava-libraries/wiki/EventBusExplained
 * 
 * Subscribers can be arbitrary objects (no need to implement any interface), but have to provide a monadic method (i.e.
 * a method with a single parameter) annotated with the {@link Subscribe} annotation from Guava. The type of the
 * parameter of the method defines the type of event that is handled.
 */
public class EventsImpl implements Events {

  private EventBus bus;

  protected EventsImpl() {
    this.bus = new EventBus();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void subscribe(final Object listener) {
    this.bus.register(listener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void unsubscribe(final Object listener) {
    this.bus.unregister(listener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void post(final Object event) {
    this.bus.post(event);
  }

}
