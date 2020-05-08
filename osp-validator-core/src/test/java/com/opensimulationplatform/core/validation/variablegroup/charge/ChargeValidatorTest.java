package com.opensimulationplatform.core.validation.variablegroup.charge;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChargeValidatorTest {
  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Charge charge = new Charge();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(v1.getCausality());
    v2.setType(v1.getType());
    v2.setUnit(v1.getUnit());

    charge.setVariables(Arrays.asList(v1, v2));

    modelDescription.getCharges().add(charge);

    ChargeValidator v = new ChargeValidator();
    List<ValidationDiagnostic<Charge>> diagnostics = v.validate(modelDescription);

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

    Charge charge = new Charge();
    charge.setVariables(Arrays.asList(v1, v2));

    modelDescription.getCharges().add(charge);

    ChargeValidator v = new ChargeValidator();
    List<ValidationDiagnostic<Charge>> diagnostics = v.validate(modelDescription);

    assertEquals(3, diagnostics.size());
    for (ValidationDiagnostic<Charge> diagnostic : diagnostics) {
      assertEquals(charge, diagnostic.getValidatedObject());
    }
  }
}
