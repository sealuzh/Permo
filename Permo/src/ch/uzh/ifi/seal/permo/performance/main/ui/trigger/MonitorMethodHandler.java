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

import java.util.Optional;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.handlers.HandlerUtil;

import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.Procedure;
import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.ProcedureFactory;
import ch.uzh.ifi.seal.permo.common.core.model.codeartifacts.Project;
import ch.uzh.ifi.seal.permo.lib.core.eclipse.AstUtil;
import ch.uzh.ifi.seal.permo.performance.timeseriers.core.model.MonitoringData;

/**
 * A handler executing the monitoring method command. This comprises opening the Performance View and fill it with the
 * appropriate data.
 */
public class MonitorMethodHandler extends AbstractHandler {

  private static final String ERROR__NO_SELECTED_ELEMENT = "No selected element.";

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(final ExecutionEvent event) throws ExecutionException {
    final ISelection selection = HandlerUtil.getCurrentSelection(event);
    if (selection == null) {
      throw new ExecutionException(ERROR__NO_SELECTED_ELEMENT);
    }
    final Optional<IMethod> method = SelectedMethods.getSelectedMethod(selection);
    if (method.isPresent()) {
      final IMethodBinding methodBinding = AstUtil.createBinding(method.get());
      final Project project = Project.of(method.get().getJavaProject().getProject().getName());
      final Procedure procedure = new ProcedureFactory().create(methodBinding);
      new PerformanceViewTrigger(MonitoringData.of(project, procedure)).execute();
    }
    return null;
  }
}
