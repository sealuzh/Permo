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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.compare.CompareConfiguration;
import org.eclipse.compare.CompareEditorInput;
import org.eclipse.compare.structuremergeviewer.ICompareInput;
import org.eclipse.core.runtime.IProgressMonitor;

import ch.uzh.ifi.seal.permo.history.git.core.model.Diff;

/**
 * Git-specific input for the Eclipse compare editor.
 */
public class GitFileDiffEditorInput extends CompareEditorInput {

  private static final String LABEL_PATTERN = "%s: %s";
  private static final String EMPTY = "";

  private final Diff diff;

  public GitFileDiffEditorInput(final Diff diff) {
    super(new CompareConfiguration());
    this.diff = diff;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object prepareInput(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
    setConfigs();
    return createInput();
  }

  private void setConfigs() {
    final String leftLabel = String.format(LABEL_PATTERN, diff.getNewCommit().getId(), diff.getNewPath());
    final String rightLabel = diff.getOldCommit() != null ? String.format(LABEL_PATTERN, diff.getOldCommit().getId(), diff.getOldPath()) : EMPTY;
    getCompareConfiguration().setLeftLabel(leftLabel);
    getCompareConfiguration().setRightLabel(rightLabel);
  }

  private ICompareInput createInput() {
    return new GitDiffNode(diff);
  }

}
