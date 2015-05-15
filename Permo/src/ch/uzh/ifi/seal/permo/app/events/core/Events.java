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

/**
 * This interface defines an event system that allows to subscribe and unsubscribe for events as well as post events. If
 * an event is posted, all subscriber that provide a handler for the posted event type (i.e. the class of the posted
 * event-object) are notified.
 */
public interface Events {

  /**
   * The singleton instance to access the event system.
   */
  public static Events INSTANCE = new EventsImpl();

  /**
   * Subscribes an event listener.
   * 
   * @param listener
   *          the event listener to be subscribed
   */
  public void subscribe(final Object listener);

  /**
   * Unsubscribes an event listener.
   * 
   * @param listener
   *          the event listener to be unsubscribed
   */
  public void unsubscribe(final Object listener);

  /**
   * Posts an event.
   * 
   * @param event
   *          the event to be posted. Can be an arbitrary object.
   */
  public void post(final Object event);

}
