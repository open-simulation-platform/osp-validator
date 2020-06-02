/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.validation;

import java.util.ArrayList;
import java.util.List;

public abstract class ValidationError<T> {

  protected ValidationErrorContext context;

  public void setContext(ValidationErrorContext context) {
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
