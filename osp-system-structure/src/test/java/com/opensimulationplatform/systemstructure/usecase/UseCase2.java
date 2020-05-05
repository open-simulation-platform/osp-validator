package com.opensimulationplatform.systemstructure.usecase;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.systemstructure.SystemStructureValidator;
import com.opensimulationplatform.systemstructure.TestResources;
import com.opensimulationplatform.systemstructure.util.MockFmuLocator;
import com.opensimulationplatform.systemstructure.xml.factory.SystemStructureFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class UseCase2 {
  @Test
  public void works() {
    SystemStructureFactory factory = new SystemStructureFactory();
    factory.setFmuLocator(new MockFmuLocator());
    SystemStructure systemStructure = factory.create(TestResources.OSP_SYSTEM_STRUCTURE_USE_CASE_2);

    SystemStructureValidator validator = new SystemStructureValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(systemStructure);

    for (ValidationDiagnostic<Object> diagnostic : diagnostics) {
      System.out.println(diagnostic.getValidatedObject());
      System.out.println(diagnostic.getErrorMessage());
    }

    assertTrue(diagnostics.isEmpty());
  }
}
