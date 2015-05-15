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
package ch.uzh.ifi.seal.permo.performance.main.ui.trigger;

import java.util.Iterator;
import java.util.Optional;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

import ch.uzh.ifi.seal.permo.lib.ui.eclipse.WorkbenchUtil;

/**
 * Provides utility methods to check whether the current selection matches a {@link IMethod}.
 */
class SelectedMethods {

  /**
   * Checks whether the given {@link ISelection} points to a single Java method. If yes, the appropriate {@link IMethod}
   * is returned.
   * 
   * @param selection
   *          the {@link ISelection} to be checked
   * @return the appropriate {@link IMethod} if the {@link ISelection} points to a Java method, {@link Optional#empty()}
   *         otherwise
   */
  public static Optional<IMethod> getSelectedMethod(final ISelection selection) {
    if (selection != null) {
      if (selection instanceof IStructuredSelection) {
        return getSelectedMethod((IStructuredSelection) selection);
      }
      else if (selection instanceof ITextSelection) {
        // There could also be an ITextSelection in a view. This case is so far not supported.
        return getSelectedMethodInActiveEditor();
      }
    }
    return Optional.empty();
  }

  /**
   * Checks whether the given {@link IStructuredSelection} points to a single Java method. If yes, the appropriate
   * {@link IMethod} is returned.
   * 
   * @param structuredSelection
   *          the {@link IStructuredSelection} to be checked
   * @return the appropriate {@link IMethod} if the {@link IStructuredSelection} points to a Java method,
   *         {@link Optional#empty()} otherwise
   */
  public static Optional<IMethod> getSelectedMethod(final IStructuredSelection structuredSelection) {
    final Iterator<?> iterator = structuredSelection.iterator();
    if (iterator.hasNext()) {
      final Object selectedElement = iterator.next();
      // Currently, only a single selected method is supported. Therefore the check below is required.
      if (!iterator.hasNext()) {
        if (selectedElement instanceof IMethod) {
          return Optional.of((IMethod) selectedElement);
        }
      }
    }
    return Optional.empty();
  }

  /**
   * Checks whether there is an active Java editor and whether its cursor points to a Java method. If yes, the
   * appropriate {@link IMethod} is returned.
   * 
   * @return the appropriate {@link IMethod} if the cursor of the active Java editor points to a method,
   *         {@link Optional#empty()} otherwise
   */
  public static Optional<IMethod> getSelectedMethodInActiveEditor() {
    final IEditorPart editor = WorkbenchUtil.activePage().getActiveEditor();
    if (editor != null && editor instanceof ITextEditor) {
      final ISelection selection = ((ITextEditor) editor).getSelectionProvider().getSelection();
      if (selection != null && selection instanceof ITextSelection) {
        return getSelectedMethod((ITextSelection) selection);
      }
    }
    return Optional.empty();
  }

  /**
   * Checks whether the given {@link ITextSelection} points to a Java method (i.e. the cursor is positioned to a Java
   * method). If yes, the appropriate {@link IMethod} is returned.
   * 
   * @param textSelection
   *          the {@link ITextSelection} to be checked
   * @return the appropriate {@link IMethod} if the given {@link ITextSelection} points to a method,
   *         {@link Optional#empty()} otherwise
   */
  public static Optional<IMethod> getSelectedMethod(final ITextSelection textSelection) {
    final Optional<IJavaElement> selectedJavaElement = getSelectedJavaElement(textSelection);
    if (selectedJavaElement.isPresent() && selectedJavaElement.get() instanceof IMethod) {
      return Optional.of((IMethod) selectedJavaElement.get());
    }
    return Optional.empty();
  }

  /**
   * Determines the Java element the given {@link ITextSelection} points to (i.e. the Java element the cursor is
   * positioned to).
   * 
   * @param textSelection
   *          the {@link ITextSelection} to be checked
   * @return the {@link IJavaElement} the cursor is positioned to or {@link Optional#empty()}, if there is no active
   *         editor, or the active editor is not a Java editor.
   */
  public static Optional<IJavaElement> getSelectedJavaElement(final ITextSelection textSelection) {
    final Optional<ITypeRoot> javaRootElement = getJavaRootElementOfActiveEditor();
    if (javaRootElement.isPresent()) {
      try {
        final IJavaElement selectedJavaElement = ((ICompilationUnit) javaRootElement.get()).getElementAt(textSelection.getOffset());
        if (selectedJavaElement != null) {
          return Optional.of(selectedJavaElement);
        }
      }
      catch (final JavaModelException e) {
        e.printStackTrace();
      }
    }
    return Optional.empty();
  }

  /**
   * Determines the root Java element of the active editor.
   * 
   * @return the {@link ITypeRoot} of the active editor or {@link Optional#empty()}, if there is no active editor or the
   *         active editor is not a Java editor.
   */
  private static Optional<ITypeRoot> getJavaRootElementOfActiveEditor() {
    final IEditorPart editor = WorkbenchUtil.activePage().getActiveEditor();
    if (editor != null) {
      final IJavaElement javaElement = JavaUI.getEditorInputJavaElement(editor.getEditorInput());
      if (javaElement != null && javaElement instanceof ITypeRoot) {
        return Optional.of((ITypeRoot) javaElement);
      }

    }
    return Optional.empty();
  }

}
