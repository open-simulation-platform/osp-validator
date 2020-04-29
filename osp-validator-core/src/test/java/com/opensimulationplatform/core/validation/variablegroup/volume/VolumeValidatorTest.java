package com.opensimulationplatform.core.validation.variablegroup.volume;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volume.Volume;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VolumeValidatorTest {
  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Volume volume = new Volume();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(v1.getCausality());
    v2.setType(v1.getType());
    v2.setUnit(v1.getUnit());

    volume.setVariables(Arrays.asList(v1, v2));

    modelDescription.getVolumes().add(volume);

    VolumeValidator v = new VolumeValidator();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Volume>> diagnostics = v.validate();

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

    Volume volume = new Volume();
    volume.setVariables(Arrays.asList(v1, v2));

    modelDescription.getVolumes().add(volume);

    VolumeValidator v = new VolumeValidator();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Volume>> diagnostics = v.validate();

    assertEquals(3, diagnostics.size());
    for (ValidationDiagnostic<Volume> diagnostic : diagnostics) {
      assertEquals(volume, diagnostic.getValidatedObject());
    }
  }
}
