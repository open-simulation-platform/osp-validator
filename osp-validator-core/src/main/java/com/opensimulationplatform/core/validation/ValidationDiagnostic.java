/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

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
