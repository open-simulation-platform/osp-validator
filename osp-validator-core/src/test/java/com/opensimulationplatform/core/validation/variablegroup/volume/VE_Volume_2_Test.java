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

public class VE_Volume_2_Test {
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

    VE_Volume_2 v = new VE_Volume_2();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Volume>> diagnostics = v.validate();

    assertTrue(diagnostics.isEmpty());
  }

  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Volume volume = new Volume();
    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.INPUT);
    v1.setType(Variable.Type.REAL);
    v1.setUnit(new Unit());

    Variable v2 = new Variable();
    v2.setCausality(v1.getCausality());
    v2.setType(Variable.Type.INTEGER);
    v2.setUnit(v1.getUnit());

    volume.setVariables(Arrays.asList(v1, v2));

    modelDescription.getVolumes().add(volume);

    VE_Volume_2 v = new VE_Volume_2();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<Volume>> diagnostics = v.validate();

    assertEquals(1, diagnostics.size());
    Volume invalidObject = diagnostics.get(0).getValidatedObject();
    List<Variable> variables = invalidObject.getVariables();
    assertTrue(variables.contains(v1));
    assertTrue(variables.contains(v2));
  }
}
