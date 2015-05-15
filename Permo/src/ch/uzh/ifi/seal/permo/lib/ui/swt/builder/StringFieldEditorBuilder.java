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
package ch.uzh.ifi.seal.permo.lib.ui.swt.builder;

import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;

/**
 * A builder for {@link StringFieldEditor}'s.
 */
public class StringFieldEditorBuilder {

  private String name;
  private String labelText;
  private Composite parent;

  private boolean emptyStringAllowed;

  private Optional<Validation> optionalValidation;

  private StringFieldEditorBuilder(final String name, final String labelText, final Composite parent) {
    this.name = name;
    this.labelText = labelText;
    this.parent = parent;
    this.emptyStringAllowed = true;
    this.optionalValidation = Optional.empty();

  }

  /**
   * Sets {@link StringFieldEditor#isEmptyStringAllowed()} to <code>false</code>.
   * 
   * @return the current {@link StringFieldEditorBuilder}
   */
  public StringFieldEditorBuilder emptyNotAllowed() {
    this.emptyStringAllowed = false;
    return this;
  }

  /**
   * Adds a custom validation to the {@link StringFieldEditor}.
   * 
   * @param condition
   *          the condition the value has to meet
   * @param errorMessage
   *          the error message that appears if the condition does not apply
   * @return the current {@link StringFieldEditorBuilder}
   */
  public StringFieldEditorBuilder withValidation(final Predicate<String> condition, final String errorMessage) {
    this.optionalValidation = Optional.of(new Validation(condition, errorMessage));
    return this;
  }

  /**
   * Adds a validation to the {@link StringFieldEditor} that checks whether the string matches the given regular
   * expression.
   * 
   * @param regularExpression
   *          the regular expression to be matched
   * @param errorMessage
   *          the error message that appears if the regular expression is not matched.
   * @return the current {@link StringFieldEditorBuilder}
   */
  public StringFieldEditorBuilder withValidation(final String regularExpression, final String errorMessage) {
    return withValidation((text) -> text.matches(regularExpression), errorMessage);
  }

  public StringFieldEditor create() {
    final StringFieldEditor stringFieldEditor = optionalValidation.isPresent() ? createWithValidation() : createWihtoutValidation();
    stringFieldEditor.setEmptyStringAllowed(emptyStringAllowed);
    return stringFieldEditor;
  }

  private StringFieldEditor createWihtoutValidation() {
    return new StringFieldEditor(name, labelText, parent);
  }

  private StringFieldEditor createWithValidation() {
    final Validation validation = this.optionalValidation.get();
    final StringFieldEditor stringFieldEditor = new StringFieldEditor(name, labelText, parent) {

      @Override
      protected boolean checkState() {
        if (!validation.getCondition().test(getStringValue())) {
          setErrorMessage(validation.getMessage());
          showErrorMessage();
          return false;
        }
        return super.checkState();
      }
    };
    return stringFieldEditor;
  }

  /**
   * Creates a new {@link StringFieldEditorBuilder}.
   * 
   * @param name
   *          the name of the {@link StringFieldEditor}
   * @param labelText
   *          the label text of the {@link StringFieldEditor}
   * @param parent
   *          the parent {@link Composite} of the {@link StringFieldEditor}
   * @return the created {@link StringFieldEditorBuilder}
   */
  public static StringFieldEditorBuilder of(final String name, final String labelText, final Composite parent) {
    return new StringFieldEditorBuilder(name, labelText, parent);
  }

  private static class Validation {
    private final Predicate<String> condition;
    private final String message;

    public Validation(final Predicate<String> condition, final String message) {
      this.condition = condition;
      this.message = message;
    }

    public Predicate<String> getCondition() {
      return condition;
    }

    public String getMessage() {
      return message;
    }

  }

}
