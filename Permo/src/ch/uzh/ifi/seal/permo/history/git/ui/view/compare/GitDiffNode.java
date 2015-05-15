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

import org.eclipse.compare.structuremergeviewer.DiffNode;
import org.eclipse.compare.structuremergeviewer.Differencer;

import ch.uzh.ifi.seal.permo.history.git.core.model.Diff;
import ch.uzh.ifi.seal.permo.history.git.core.model.DiffType;

/**
 * Git-specific implementation of {@link DiffNode}.
 * 
 * Contains the content shown in the compare editor (see also {@link GitFileDiffEditorInput}).
 */
public class GitDiffNode extends DiffNode {

  private static final String EMPTY = "";

  public GitDiffNode(final Diff diff) {
    super(Differencer.CHANGE);
    init(diff);
  }

  private void init(final Diff diff) {
    final String leftFileName = !diff.getType().equals(DiffType.DELETE) ? diff.getNewFileName() : diff.getOldFileName();
    final String leftFileContent = !diff.getType().equals(DiffType.DELETE) ? diff.getNewContent() : EMPTY;
    final String rightFileName = !diff.getType().equals(DiffType.ADD) ? diff.getOldFileName() : diff.getNewFileName();
    final String rightFileContent = !diff.getType().equals(DiffType.ADD) ? diff.getOldContent() : EMPTY;
    setLeft(new GitCompareItem(leftFileName, leftFileContent));
    setRight(new GitCompareItem(rightFileName, rightFileContent));
  }

}
