package com.opensimulationplatform.core.validation.name;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class NamesValidatorTest {
  @Test
  public void invalidModelDescription() {
    ModelDescription modelDescription = new ModelDescription();
    Variable v1 = new Variable();
    v1.setName("not-unique");
    Variable v2 = new Variable();
    v2.setName("not-unique");
    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    Generic vg1 = new Generic();
    vg1.setName("not-unique");
    Generic vg2 = new Generic();
    vg2.setName("not-unique");
    modelDescription.getGenerics().add(vg1);
    modelDescription.getGenerics().add(vg2);

    NamesValidator namesValidator = new NamesValidator();
    namesValidator.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Name>> diagnostics = namesValidator.validate();

    assertEquals(2, diagnostics.size());
  }

  @Test
  public void invaliSystemStructure() {
    SystemStructure systemStructure = new SystemStructure();
    Simulator s1 = new Simulator();
    s1.setName("not-unique");
    Simulator s2 = new Simulator();
    s2.setName("not-unique");

    ModelDescription modelDescription = new ModelDescription();
    Variable v1 = new Variable();
    v1.setName("not-unique");
    Variable v2 = new Variable();
    v2.setName("not-unique");
    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    Generic vg1 = new Generic();
    vg1.setName("not-unique");
    Generic vg2 = new Generic();
    vg2.setName("not-unique");
    modelDescription.getGenerics().add(vg1);
    modelDescription.getGenerics().add(vg2);

    s1.setModelDescription(modelDescription);

    systemStructure.getSimulators().add(s1);
    systemStructure.getSimulators().add(s2);

    NamesValidator namesValidator = new NamesValidator();
    namesValidator.setContext(new ValidationContext(systemStructure));
    List<ValidationDiagnostic<Name>> diagnostics = namesValidator.validate();

    assertEquals(3, diagnostics.size());
  }

  @Test
  public void validModelDescription() {
    ModelDescription modelDescription = new ModelDescription();
    Variable v1 = new Variable();
    v1.setName("v1");
    Variable v2 = new Variable();
    v2.setName("v2");
    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    Generic vg1 = new Generic();
    vg1.setName("v3");
    Generic vg2 = new Generic();
    vg2.setName("v4");
    modelDescription.getGenerics().add(vg1);
    modelDescription.getGenerics().add(vg2);

    NamesValidator namesValidator = new NamesValidator();
    namesValidator.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Name>> diagnostics = namesValidator.validate();

    assertEquals(0, diagnostics.size());
  }

  @Test
  public void validSystemStructure() {
    SystemStructure systemStructure = new SystemStructure();
    Simulator s1 = new Simulator();
    s1.setName("s1");
    Simulator s2 = new Simulator();
    s2.setName("s2");

    ModelDescription modelDescription = new ModelDescription();
    Variable v1 = new Variable();
    v1.setName("v1");
    Variable v2 = new Variable();
    v2.setName("v2");
    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    Generic vg1 = new Generic();
    vg1.setName("vg1");
    Generic vg2 = new Generic();
    vg2.setName("vg2");
    modelDescription.getGenerics().add(vg1);
    modelDescription.getGenerics().add(vg2);

    s1.setModelDescription(modelDescription);

    systemStructure.getSimulators().add(s1);
    systemStructure.getSimulators().add(s2);

    NamesValidator namesValidator = new NamesValidator();
    namesValidator.setContext(new ValidationContext(systemStructure));
    List<ValidationDiagnostic<Name>> diagnostics = namesValidator.validate();

    assertEquals(0, diagnostics.size());
  }
}