package com.opensimulationplatform.core.validation.variablegroup.nmeaggafix;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix.NmeaGgaFix;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NmeaGgaFixValidatorTest {
  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    NmeaGgaFix nmeaGgaFix = new NmeaGgaFix();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(v1.getCausality());
    v2.setType(v1.getType());
    v2.setUnit(v1.getUnit());

    Variable v3 = new Variable();
    v3.setCausality(v1.getCausality());
    v3.setType(v1.getType());
    v3.setUnit(v1.getUnit());

    Variable v4 = new Variable();
    v4.setCausality(v1.getCausality());
    v4.setType(v1.getType());
    v4.setUnit(v1.getUnit());

    Variable v5 = new Variable();
    v5.setCausality(v1.getCausality());
    v5.setType(v1.getType());
    v5.setUnit(v1.getUnit());

    Variable v6 = new Variable();
    v6.setCausality(v1.getCausality());
    v6.setType(v1.getType());
    v6.setUnit(v1.getUnit());

    Variable v7 = new Variable();
    v7.setCausality(v1.getCausality());
    v7.setType(v1.getType());
    v7.setUnit(v1.getUnit());

    nmeaGgaFix.setVariables(Arrays.asList(v1, v2, v3, v4, v5, v6, v7));

    modelDescription.getNmeaGgaFixs().add(nmeaGgaFix);

    NmeaGgaFixValidator v = new NmeaGgaFixValidator();
    List<ValidationDiagnostic<NmeaGgaFix>> diagnostics = v.validate(modelDescription);

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

    Variable v3 = new Variable();
    v3.setCausality(v1.getCausality());
    v3.setType(v1.getType());
    v3.setUnit(v1.getUnit());

    Variable v4 = new Variable();
    v4.setCausality(v1.getCausality());
    v4.setType(v1.getType());
    v4.setUnit(v1.getUnit());

    Variable v5 = new Variable();
    v5.setCausality(v1.getCausality());
    v5.setType(v1.getType());
    v5.setUnit(v1.getUnit());

    Variable v6 = new Variable();
    v6.setCausality(v1.getCausality());
    v6.setType(v1.getType());
    v6.setUnit(v1.getUnit());

    Variable v7 = new Variable();
    v7.setCausality(v1.getCausality());
    v7.setType(v1.getType());
    v7.setUnit(v1.getUnit());

    NmeaGgaFix nmeaGgaFix = new NmeaGgaFix();
    nmeaGgaFix.setVariables(Arrays.asList(v1, v2, v3, v4, v5, v6, v7));

    modelDescription.getNmeaGgaFixs().add(nmeaGgaFix);

    NmeaGgaFixValidator v = new NmeaGgaFixValidator();
    List<ValidationDiagnostic<NmeaGgaFix>> diagnostics = v.validate(modelDescription);

    assertEquals(1, diagnostics.size());
    for (ValidationDiagnostic<NmeaGgaFix> diagnostic : diagnostics) {
      assertEquals(nmeaGgaFix, diagnostic.getValidatedObject());
    }
  }
}
