package com.opensimulationplatform.core.validation.variablegroup.torque;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_Torque_3_Test {
  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Torque torque = new Torque();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(v1.getCausality());
    v2.setType(v1.getType());
    v2.setUnit(v1.getUnit());

    torque.setVariables(Arrays.asList(v1, v2));

    modelDescription.getTorques().add(torque);

    VE_Torque_3 v = new VE_Torque_3();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Torque>> diagnostics = v.validate();

    assertTrue(diagnostics.isEmpty());
  }

  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Torque torque = new Torque();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(v1.getCausality());
    v2.setType(v1.getType());
    Unit u = new Unit();
    u.setExponent(Unit.Exponent.KILOGRAM, 1);
    v2.setUnit(u);

    torque.setVariables(Arrays.asList(v1, v2));

    modelDescription.getTorques().add(torque);

    VE_Torque_3 v = new VE_Torque_3();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Torque>> diagnostics = v.validate();

    assertEquals(1, diagnostics.size());
    Torque invalidObject = diagnostics.get(0).getValidatedObject();
    List<Variable> variables = invalidObject.getVariables();
    assertTrue(variables.contains(v1));
    assertTrue(variables.contains(v2));
  }
}
