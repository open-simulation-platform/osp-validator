package com.opensimulationplatform.core.validation;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;

import java.util.ArrayList;
import java.util.List;

public abstract class Validator<T> {
  protected ValidatorContext context;

  protected abstract List<Validator<?>> getValidators();

  protected abstract List<ValidationError<?>> getValidationErrors();

  public void setContext(ValidatorContext context) {
    this.context = context;

    List<ValidationError<?>> validationErrors = getValidationErrors();
    if (validationErrors != null) {
      ValidationErrorContext validationErrorContext = new ValidationErrorContext();
      validationErrorContext.owl = context.owl;
      validationErrorContext.names = context.names;
      validationErrorContext.units = context.units;
      validationErrorContext.variables = context.variables;
      validationErrorContext.variableGroups = context.variableGroups;
      validationErrorContext.simulators = context.simulators;
      validationErrorContext.variableConnections = context.variableConnections;
      validationErrorContext.variableGroupConnections = context.variableGroupConnections;

      validationErrors.forEach(validationError -> validationError.setContext(validationErrorContext));
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
      ValidatorContextFactory factory = new ValidatorContextFactory();
      ValidatorContext context = factory.create(systemStructure);
      this.setContext(context);
    }

    return validate();
  }

  public List<ValidationDiagnostic<T>> validate(ModelDescription modelDescription) {
    if (context == null) {
      ValidatorContextFactory factory = new ValidatorContextFactory();
      ValidatorContext context = factory.create(modelDescription);
      this.setContext(context);
    }

    return validate();
  }
}
