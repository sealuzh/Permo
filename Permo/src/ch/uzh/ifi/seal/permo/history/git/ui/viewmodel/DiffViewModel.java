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
package ch.uzh.ifi.seal.permo.history.git.ui.viewmodel;

import org.eclipse.swt.graphics.Image;

import ch.uzh.ifi.seal.permo.app.activator.core.Activator;
import ch.uzh.ifi.seal.permo.history.git.core.model.Diff;

/**
 * A view model providing access to an underlying {@link Diff}.
 */
public class DiffViewModel {

  private static final String ICON_ADD = "icons/icon.add.png";
  private static final String ICON_DELETE = "icons/icon.delete.png";
  private static final String ICON_MODIFY = "icons/icon.modify.png";

  private Diff diff;

  public DiffViewModel(final Diff diff) {
    this.diff = diff;
  }

  /**
   * Returns the underlying file diff.
   * 
   * @return the underlying file diff
   */
  public Diff getDiff() {
    return diff;
  }

  /**
   * Returns the image corresponding to the type of diff
   * 
   * @return
   */
  public Image getImage() {
    switch (diff.getType()) {
      case ADD:
        return Activator.getImageDescriptor(ICON_ADD).createImage();
      case DELETE:
        return Activator.getImageDescriptor(ICON_DELETE).createImage();
      case MODIFY:
        return Activator.getImageDescriptor(ICON_MODIFY).createImage();
    }
    return null;
  }

}
