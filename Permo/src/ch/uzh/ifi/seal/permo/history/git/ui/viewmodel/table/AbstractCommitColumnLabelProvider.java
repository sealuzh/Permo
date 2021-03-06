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
package ch.uzh.ifi.seal.permo.history.git.ui.viewmodel.table;

import org.eclipse.swt.graphics.Color;

import ch.uzh.ifi.seal.permo.common.ui.view.colors.Colors;
import ch.uzh.ifi.seal.permo.history.common.ui.viewmodel.ProjectHistoryEventViewModel;
import ch.uzh.ifi.seal.permo.history.git.ui.viewmodel.CommitViewModel;
import ch.uzh.ifi.seal.permo.lib.ui.jface.DefaultColumnLabelProvider;

/**
 * An abstract implementation of column label providers for commits.
 */
public class AbstractCommitColumnLabelProvider extends DefaultColumnLabelProvider<ProjectHistoryEventViewModel> {

  /**
   * {@inheritDoc}
   */
  @Override
  public Color foreground(final ProjectHistoryEventViewModel element) {
    /*
     * Change the foreground color of merge commits to gray.
     */
    if (element instanceof CommitViewModel && ((CommitViewModel) element).isMerge()) {
      return Colors.GRAY;
    }
    return super.foreground(element);
  }
}
