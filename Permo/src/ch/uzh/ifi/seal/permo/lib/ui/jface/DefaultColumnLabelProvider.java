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
package ch.uzh.ifi.seal.permo.lib.ui.jface;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * A default implementation of a {@link ColumnLabelProvider} that makes use of generic to avoid explicit type castings
 * in subclasses.
 * 
 * @param <E>
 *          the type of expected table elements
 */
public class DefaultColumnLabelProvider<E> extends ColumnLabelProvider {

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final Font getFont(final Object element) {
    return font((E) element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final Color getBackground(final Object element) {
    return background((E) element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final Color getForeground(final Object element) {
    return foreground((E) element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final Image getImage(final Object element) {
    return image((E) element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final String getText(final Object element) {
    return text((E) element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final String getToolTipText(final Object element) {
    return toolTipText((E) element);
  }

  public Font font(final E element) {
    return null;
  }

  public Color background(final E element) {
    return null;
  }

  public Color foreground(final E element) {
    return null;
  }

  public Image image(final E element) {
    return null;
  }

  public String text(final E element) {
    return null;
  }

  public String toolTipText(final E element) {
    return text(element);
  }

}
