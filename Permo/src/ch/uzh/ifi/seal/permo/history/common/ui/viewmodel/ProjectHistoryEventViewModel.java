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
package ch.uzh.ifi.seal.permo.history.common.ui.viewmodel;

import java.time.LocalDateTime;

import org.eclipse.swt.graphics.Image;

/**
 * Defines the methods of any view model that provides information about an event.
 */
public interface ProjectHistoryEventViewModel {

  /**
   * Returns the point in time of the event.
   * 
   * @return the point in time of the event
   */
  public LocalDateTime getDateTime();

  /**
   * Returns the title of the event.
   * 
   * @return the title of the event
   */
  public String getTitle();

  /**
   * Returns an icon that visualizes the type of event.
   * 
   * @return an icon that visualizes the type of event
   */
  public Image getImage();
}
