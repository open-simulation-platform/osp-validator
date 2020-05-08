package com.opensimulationplatform.core.validation.variablegroup.pressure;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PressureValidatorTest {
  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Pressure pressure = new Pressure();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(v1.getCausality());
    v2.setType(v1.getType());
    v2.setUnit(v1.getUnit());

    pressure.setVariables(Arrays.asList(v1, v2));

    modelDescription.getPressures().add(pressure);

    PressureValidator v = new PressureValidator();
    List<ValidationDiagnostic<Pressure>> diagnostics = v.validate(modelDescription);

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

    Pressure pressure = new Pressure();
    pressure.setVariables(Arrays.asList(v1, v2));

    modelDescription.getPressures().add(pressure);

    PressureValidator v = new PressureValidator();
    List<ValidationDiagnostic<Pressure>> diagnostics = v.validate(modelDescription);

    assertEquals(3, diagnostics.size());
    for (ValidationDiagnostic<Pressure> diagnostic : diagnostics) {
      assertEquals(pressure, diagnostic.getValidatedObject());
    }
  }
}
