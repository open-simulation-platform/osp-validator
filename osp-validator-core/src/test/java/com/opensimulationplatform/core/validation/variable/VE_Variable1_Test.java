package com.opensimulationplatform.core.validation.variable;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Variable1_Test {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setName("v1");
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setName("v2");
    v2.setCausality(Variable.Causality.UNDEFINED);
    v2.setType(Variable.Type.REAL);

    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    VE_Variable_1 v = new VE_Variable_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Variable>> diagnostics = v.validate();
    assertEquals(1, diagnostics.size());
    Variable invalidVariable = diagnostics.get(0).getValidatedObject();
    assertEquals(invalidVariable, v2);
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setName("v1");
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setName("v2");
    v2.setCausality(Variable.Causality.INPUT);
    v2.setType(Variable.Type.REAL);

    modelDescription.getVariables().add(v1);
    modelDescription.getVariables().add(v2);

    VE_Variable_1 v = new VE_Variable_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Variable>> diagnostics = v.validate();
    assertTrue(diagnostics.isEmpty());
  }
}