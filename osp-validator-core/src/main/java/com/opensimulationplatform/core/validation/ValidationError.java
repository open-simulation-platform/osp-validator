package com.opensimulationplatform.core.validation;

import java.util.ArrayList;
import java.util.List;

public abstract class ValidationError<T> {

  protected ValidationContext context;

  public void setContext(ValidationContext context) {
    this.context = context;
  }

  public List<ValidationDiagnostic<T>> validate() {
    List<ValidationDiagnostic<T>> validationDiagnostics = new ArrayList<>();

    List<T> invalidObjects = getInvalidObjects();
    invalidObjects.forEach(invalidObject -> {
      ValidationDiagnostic<T> validationDiagnostic = new ValidationDiagnostic<>();
      validationDiagnostic.setValidatedObject(invalidObject);
      validationDiagnostic.setErrorMessage(getErrorMessage(invalidObject));

      validationDiagnostics.add(validationDiagnostic);
    });

    return validationDiagnostics;
  }

  protected abstract List<T> getInvalidObjects();

  protected abstract String getErrorMessage(T object);
}
