package com.opensimulationplatform.core.validation;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;

import java.util.ArrayList;
import java.util.List;

public abstract class Validator<T> {
  protected ValidationContext context;

  protected abstract List<Validator<?>> getValidators();

  protected abstract List<ValidationError<?>> getValidationErrors();

  public void setContext(ValidationContext context) {
    this.context = context;

    List<ValidationError<?>> validationErrors = getValidationErrors();
    if (validationErrors != null) {
      validationErrors.forEach(validator -> validator.setContext(context));
    }

    List<Validator<?>> validators = getValidators();
    if (validators != null) {
      validators.forEach(validator -> validator.setContext(context));
    }
  }

  public List<ValidationDiagnostic<T>> validate() {
    List<ValidationDiagnostic<T>> diagnostics = new ArrayList<>();

    List<ValidationError<?>> validationErrors = getValidationErrors();
    if (validationErrors != null) {
      for (ValidationError<?> validationError : validationErrors) {
        for (ValidationDiagnostic<?> d : validationError.validate()) {
          diagnostics.add(new ValidationDiagnostic(d.getErrorMessage(), d.getValidatedObject()));
        }
      }
    }

    List<Validator<?>> validators = getValidators();
    if (validators != null) {
      for (Validator<?> nestedValidator : validators) {
        for (ValidationDiagnostic<?> d : nestedValidator.validate()) {
          diagnostics.add(new ValidationDiagnostic(d.getErrorMessage(), d.getValidatedObject()));
        }
      }
    }

    return diagnostics;
  }

  public List<ValidationDiagnostic<T>> validate(SystemStructure systemStructure) {
    if (context == null) {
      this.setContext(new ValidationContext(systemStructure));
    }

    return validate();
  }

  public List<ValidationDiagnostic<T>> validate(ModelDescription modelDescription) {
    if (context == null) {
      this.setContext(new ValidationContext(modelDescription));
    }

    return validate();
  }
}
