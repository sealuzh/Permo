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
package ch.uzh.ifi.seal.permo.lib.core.eclipse;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.IBinding;
import org.eclipse.jdt.core.dom.IMethodBinding;

/**
 * Provides static utility methods related to AST's and AST parsing.
 */
public class AstUtil {

  private AstUtil() {}

  /**
   * Uses an {@link ASTParser} to create the {@link IMethodBinding} of the given {@link IMethod}.
   * 
   * Further info: http://wiki.eclipse.org/JDT/FAQ#From_an_IJavaElement_to_an_IBinding
   * 
   * @param method
   *          the {@link IMethod}
   * @return the create {@link IMethodBinding}.
   */
  public static IMethodBinding createBinding(final IMethod method) {
    final ASTParser parser = ASTParser.newParser(AST.JLS8);
    parser.setProject(method.getJavaProject());
    final IBinding[] bindings = parser.createBindings(new IJavaElement[] { method }, new NullProgressMonitor());
    if (bindings.length > 0 && bindings[0] instanceof IMethodBinding) {
      return (IMethodBinding) bindings[0];
    }
    return null;
  }

}
