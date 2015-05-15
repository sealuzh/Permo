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

import org.eclipse.compare.CompareUI;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IReusableEditor;

import ch.uzh.ifi.seal.permo.lib.ui.eclipse.WorkbenchUtil;

/**
 * Provides functionality related to the Eclipse compare editor.
 */
public class CompareUtil {

  /**
   * Displays the given input in an existing compare editor, if there is already one. Otherwise, a new compare editor is
   * opened.
   * 
   * @param input
   *          the {@link GitFileDiffEditorInput} to be shown in the compare editor
   */
  public static void openExistingCompareEditor(final GitFileDiffEditorInput input) {
    final IEditorPart existingEditor = findExistingCompareEditor();
    if (existingEditor != null) {
      CompareUI.reuseCompareEditor(input, (IReusableEditor) existingEditor);
      WorkbenchUtil.activePage().activate(existingEditor);
    }
    else {
      openNewCompareEditor(input);
    }
  }

  /**
   * Opens a new compare editor with the given input.
   * 
   * @param input
   *          the {@link GitFileDiffEditorInput} to be shown in the newly created compare editor
   */
  public static void openNewCompareEditor(final GitFileDiffEditorInput input) {
    CompareUI.openCompareEditor(input);
  }

  /*
   * iterates over all the opened editors in the workbench and checks whether there is a compare editor
   */
  private static IEditorPart findExistingCompareEditor() {
    final IEditorReference[] editorRefs = WorkbenchUtil.activePage().getEditorReferences();
    for (final IEditorReference editorRef : editorRefs) {
      final IEditorPart part = editorRef.getEditor(false);
      if (part != null && part.getEditorInput() instanceof GitFileDiffEditorInput && part instanceof IReusableEditor) {
        return part;
      }
    }
    return null;
  }

}
