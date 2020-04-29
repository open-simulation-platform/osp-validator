package com.opensimulationplatform.core.validation.variablegroup.hydraulicquasiport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicquasiport.HydraulicQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volume.Volume;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HydraulicQuasiPortValidatorTest {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Pressure pressure = new Pressure();
    pressure.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    Volume volume = new Volume();
    volume.setVariables(Arrays.asList(v2));

    HydraulicQuasiPort hydraulicQuasiPort = new HydraulicQuasiPort();
    hydraulicQuasiPort.setPressure(pressure);
    hydraulicQuasiPort.setVolume(volume);

    modelDescription.getHydraulicQuasiPorts().add(hydraulicQuasiPort);

    HydraulicQuasiPortValidator hydraulicQuasiPortValidator = new HydraulicQuasiPortValidator();
    List<ValidationDiagnostic<HydraulicQuasiPort>> diagnostics = hydraulicQuasiPortValidator.validate(modelDescription);

    assertEquals(1, diagnostics.size());
    assertEquals(hydraulicQuasiPort, diagnostics.get(0).getValidatedObject());
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Pressure pressure = new Pressure();
    pressure.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.INPUT);
    Volume volume = new Volume();
    volume.setVariables(Arrays.asList(v2));

    HydraulicQuasiPort hydraulicQuasiPort = new HydraulicQuasiPort();
    hydraulicQuasiPort.setPressure(pressure);
    hydraulicQuasiPort.setVolume(volume);

    modelDescription.getHydraulicQuasiPorts().add(hydraulicQuasiPort);

    HydraulicQuasiPortValidator hydraulicQuasiPortValidator = new HydraulicQuasiPortValidator();
    List<ValidationDiagnostic<HydraulicQuasiPort>> diagnostics = hydraulicQuasiPortValidator.validate(modelDescription);

    assertTrue(diagnostics.isEmpty());
  }
}
