package com.opensimulationplatform.core.validation.name;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Name_3_Test {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Generic vg1 = new Generic();
    vg1.setName("not-a-unique-name");

    Generic vg2 = new Generic();
    vg2.setName("not-a-unique-name");

    modelDescription.getGenerics().add(vg1);
    modelDescription.getGenerics().add(vg2);

    VE_Name_3 v = new VE_Name_3();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Name>> diagnostics = v.validate();
    assertEquals(1, diagnostics.size());
    Name invalidName = diagnostics.get(0).getValidatedObject();
    assertEquals(invalidName.get(), vg1.getName().get());
    assertEquals(invalidName.get(), vg2.getName().get());
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Generic vg1 = new Generic();
    vg1.setName("vg1-unique-name");

    Generic vg2 = new Generic();
    vg2.setName("vg2-unique-name");

    modelDescription.getGenerics().add(vg1);
    modelDescription.getGenerics().add(vg2);

    VE_Name_3 v = new VE_Name_3();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Name>> diagnostics = v.validate();
    assertTrue(diagnostics.isEmpty());
  }
}