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
package ch.uzh.ifi.seal.permo.history.git.ui.view.detail;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import ch.uzh.ifi.seal.permo.history.git.ui.view.compare.CompareUtil;
import ch.uzh.ifi.seal.permo.history.git.ui.view.compare.GitFileDiffEditorInput;
import ch.uzh.ifi.seal.permo.history.git.ui.viewmodel.DiffViewModel;
import ch.uzh.ifi.seal.permo.lib.ui.jface.AbstractDefaultTableViewer;
import ch.uzh.ifi.seal.permo.lib.ui.jface.DefaultColumnLabelProvider;

/**
 * The file diff table shown in the git detail control.
 */
public class FileDiffTable extends AbstractDefaultTableViewer {

  public FileDiffTable(final Composite parent) {
    super(parent);
    registerListener();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createColumns() {
    createColumn(new DefaultColumnLabelProvider<DiffViewModel>() {
      @Override
      public Image image(final DiffViewModel element) {
        return element.getImage();
      }
    }, SWT.LEFT);
    createColumn(new DefaultColumnLabelProvider<DiffViewModel>() {
      @Override
      public String text(final DiffViewModel element) {
        return element.getDiff().getPath();
      }
    }, SWT.LEFT);

  }

  /*
   * Registers a listener that listens for double clicks on table items and opens the compare editor with the
   * appropriate content.
   */
  private void registerListener() {
    getTable().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseDoubleClick(final MouseEvent event) {
        if (getSelection() instanceof IStructuredSelection) {
          final IStructuredSelection selection = (IStructuredSelection) getSelection();
          if (selection.getFirstElement() instanceof DiffViewModel) {
            final DiffViewModel selectedDiff = (DiffViewModel) selection.getFirstElement();
            final GitFileDiffEditorInput editorInput = new GitFileDiffEditorInput(selectedDiff.getDiff());
            if ((event.stateMask & SWT.CTRL) != 0) {
              CompareUtil.openNewCompareEditor(editorInput);
            }
            else {
              CompareUtil.openExistingCompareEditor(editorInput);
            }

          }
        }
        getSelection();
      }
    });
  }

}
