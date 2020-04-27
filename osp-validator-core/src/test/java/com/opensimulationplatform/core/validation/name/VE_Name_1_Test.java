package com.opensimulationplatform.core.validation.name;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Name_1_Test {

  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setName("not-a-unique-name");

    Variable v2 = new Variable();
    v2.setName("not-a-unique-name");

    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    VE_Name_1 v = new VE_Name_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Name>> diagnostics = v.validate();

    assertEquals(1, diagnostics.size());
    Name invalidName = diagnostics.get(0).getValidatedObject();
    assertEquals(invalidName.get(), v1.getName().get());
    assertEquals(invalidName.get(), v2.getName().get());
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setName("v1-unique-name");

    Variable v2 = new Variable();
    v2.setName("v2-unique-name");

    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    VE_Name_1 v = new VE_Name_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Name>> diagnostics = v.validate();

    assertTrue(diagnostics.isEmpty());
  }
}