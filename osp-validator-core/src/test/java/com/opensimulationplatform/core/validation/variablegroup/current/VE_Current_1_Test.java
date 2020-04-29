package com.opensimulationplatform.core.validation.variablegroup.current;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.current.Current;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Current_1_Test {
  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Current current = new Current();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(v1.getCausality());
    v2.setType(v1.getType());
    v2.setUnit(v1.getUnit());

    current.setVariables(Arrays.asList(v1, v2));

    modelDescription.getCurrents().add(current);

    VE_Current_1 v = new VE_Current_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Current>> diagnostics = v.validate();

    assertTrue(diagnostics.isEmpty());
  }

  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Current current = new Current();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setType(v1.getType());
    v2.setUnit(v1.getUnit());

    current.setVariables(Arrays.asList(v1, v2));

    modelDescription.getCurrents().add(current);

    VE_Current_1 v = new VE_Current_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Current>> diagnostics = v.validate();

    assertEquals(1, diagnostics.size());
    Current invalidObject = diagnostics.get(0).getValidatedObject();
    List<Variable> variables = invalidObject.getVariables();
    assertTrue(variables.contains(v1));
    assertTrue(variables.contains(v2));
  }
}
