/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.api.examples;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.modeldescription.ModelDescriptionValidator;
import com.opensimulationplatform.modeldescription.xml.factory.ModelDescriptionFactory;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

class ValidateOspModelDescriptionFile {
  public static void main(String[] args) throws URISyntaxException {
    File ospModelDescriptionFile = new File("/path/to/OspModelDescription.xml");
    URI fmu = new URI("file:///path/to/model.fmu");

    ModelDescriptionFactory factory = new ModelDescriptionFactory();
    ModelDescription modelDescription = factory.create(ospModelDescriptionFile, fmu);
    ModelDescriptionValidator validator = new ModelDescriptionValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(modelDescription);

    for (ValidationDiagnostic<Object> diagnostic : diagnostics) {
      System.out.println("Message: " + diagnostic.getErrorMessage());
      System.out.println("Object: " + diagnostic.getValidatedObject());
    }
  }
}
