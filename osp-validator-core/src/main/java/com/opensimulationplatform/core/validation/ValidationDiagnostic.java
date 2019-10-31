package com.opensimulationplatform.core.validation;

public class ValidationDiagnostic<T> {
  private String errorMessage;
  private T validatedObject;

  public ValidationDiagnostic() {
  }

  public ValidationDiagnostic(String errorMessage, T validatedObject) {
    this.errorMessage = errorMessage;
    this.validatedObject = validatedObject;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public T getValidatedObject() {
    return validatedObject;
  }

  public void setValidatedObject(T validatedObject) {
    this.validatedObject = validatedObject;
  }
}
