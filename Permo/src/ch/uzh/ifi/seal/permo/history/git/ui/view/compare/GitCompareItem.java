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
package ch.uzh.ifi.seal.permo.history.git.ui.view.compare;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.eclipse.compare.BufferedContent;
import org.eclipse.compare.ITypedElement;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.PlatformUI;

/**
 * Git specific implementation of {@link ITypedElement}.
 * 
 * Represents the content of one side of the compare editor.
 */
class GitCompareItem extends BufferedContent implements ITypedElement {

  private static final String POINT = ".";

  private final String fileName;
  private final String fileContent;

  public GitCompareItem(final String name, final String content) {
    this.fileName = name;
    this.fileContent = content;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return fileName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Image getImage() {
    if (fileName != null) {
      return PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor(fileName).createImage();
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getType() {
    final String fileEnding = getFileEnding();
    return fileEnding != null ? fileEnding : ITypedElement.TEXT_TYPE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected InputStream createStream() throws CoreException {
    return new ByteArrayInputStream(fileContent.getBytes(StandardCharsets.UTF_8));
  }

  /*
   * Determines the file ending of the filename.
   */
  private String getFileEnding() {
    if (fileName != null) {
      final int indexOfSeparator = fileName.lastIndexOf(POINT);
      if (indexOfSeparator != -1 && fileName.length() > indexOfSeparator + 1) {
        final String fileEnding = fileName.substring(indexOfSeparator + 1);
        return fileEnding;
      }
    }
    return null;
  }

}
