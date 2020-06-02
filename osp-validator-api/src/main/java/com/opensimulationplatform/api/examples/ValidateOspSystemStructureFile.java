/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.api.examples;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.systemstructure.SystemStructureValidator;
import com.opensimulationplatform.systemstructure.xml.factory.SystemStructureFactory;

import java.io.File;
import java.util.List;

public class ValidateOspSystemStructureFile {
  public static void main(String[] args) {
    File ospSystemStructureFile = new File("/path/to/OspSystemStructure.xml");

    SystemStructureFactory factory = new SystemStructureFactory();
    SystemStructure modelDescription = factory.create(ospSystemStructureFile);
    SystemStructureValidator validator = new SystemStructureValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(modelDescription);

    for (ValidationDiagnostic<Object> diagnostic : diagnostics) {
      System.out.println("Message: " + diagnostic.getErrorMessage());
      System.out.println("Object: " + diagnostic.getValidatedObject());
    }
  }
}
