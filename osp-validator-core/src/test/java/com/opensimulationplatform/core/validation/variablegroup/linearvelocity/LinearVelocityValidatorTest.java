package com.opensimulationplatform.core.validation.variablegroup.linearvelocity;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinearVelocityValidatorTest {
  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    LinearVelocity linearVelocity = new LinearVelocity();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(v1.getCausality());
    v2.setType(v1.getType());
    v2.setUnit(v1.getUnit());

    linearVelocity.setVariables(Arrays.asList(v1, v2));

    modelDescription.getLinearVelocities().add(linearVelocity);

    LinearVelocityValidator v = new LinearVelocityValidator();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<LinearVelocity>> diagnostics = v.validate();

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

    LinearVelocity linearVelocity = new LinearVelocity();
    linearVelocity.setVariables(Arrays.asList(v1, v2));

    modelDescription.getLinearVelocities().add(linearVelocity);

    LinearVelocityValidator v = new LinearVelocityValidator();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<LinearVelocity>> diagnostics = v.validate();

    assertEquals(3, diagnostics.size());
    for (ValidationDiagnostic<LinearVelocity> diagnostic : diagnostics) {
      assertEquals(linearVelocity, diagnostic.getValidatedObject());
    }
  }
}
