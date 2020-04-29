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

public class TorqueValidatorTest {
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

    TorqueValidator v = new TorqueValidator();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Torque>> diagnostics = v.validate();

    assertTrue(diagnostics.isEmpty());
  }

  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setType(Variable.Type.INTEGER);
    Unit unit = new Unit();
    unit.setExponent(Unit.Exponent.KILOGRAM, 1);
    v2.setUnit(unit);

    Torque torque = new Torque();
    torque.setVariables(Arrays.asList(v1, v2));

    modelDescription.getTorques().add(torque);

    TorqueValidator v = new TorqueValidator();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Torque>> diagnostics = v.validate();

    assertEquals(3, diagnostics.size());
    for (ValidationDiagnostic<Torque> diagnostic : diagnostics) {
      assertEquals(torque, diagnostic.getValidatedObject());
    }
  }
}
