package com.opensimulationplatform.core.validation.variable;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class VariablesValidatorTest {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();
    Variable v1 = new Variable();
    v1.setName("v1");
    v1.setCausality(Variable.Causality.UNDEFINED);
    v1.setType(Variable.Type.UNDEFINED);
    v1.setAxis(Variable.Axis.UNDEFINED);
    modelDescription.getVariables().add(v1);

    VariablesValidator variablesValidator = new VariablesValidator();
    List<ValidationDiagnostic<Variable>> diagnostics = variablesValidator.validate(modelDescription);

    assertEquals(1, diagnostics.size());
    Variable invalidVariable = diagnostics.get(0).getValidatedObject();
    assertEquals(v1, invalidVariable);
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();
    Variable v1 = new Variable();
    v1.setName("v1");
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setAxis(Variable.Axis.X);
    modelDescription.getVariables().add(v1);

    VariablesValidator variablesValidator = new VariablesValidator();
    List<ValidationDiagnostic<Variable>> diagnostics = variablesValidator.validate(modelDescription);

    assertEquals(0, diagnostics.size());
  }
}