package com.opensimulationplatform.core.validation.variablegroup.hydraulicport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HydraulicPortValidatorTest {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Pressure pressure = new Pressure();
    pressure.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    VolumeFlowRate volumeFlowRate = new VolumeFlowRate();
    volumeFlowRate.setVariables(Arrays.asList(v2));

    HydraulicPort hydraulicPort = new HydraulicPort();
    hydraulicPort.setPressure(pressure);
    hydraulicPort.setVolumeFlowRate(volumeFlowRate);

    modelDescription.getHydraulicPorts().add(hydraulicPort);

    HydraulicPortValidator hydraulicPortValidator = new HydraulicPortValidator();
    List<ValidationDiagnostic<HydraulicPort>> diagnostics = hydraulicPortValidator.validate(modelDescription);

    assertEquals(1, diagnostics.size());
    assertEquals(hydraulicPort, diagnostics.get(0).getValidatedObject());
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
    VolumeFlowRate volumeFlowRate = new VolumeFlowRate();
    volumeFlowRate.setVariables(Arrays.asList(v2));

    HydraulicPort hydraulicPort = new HydraulicPort();
    hydraulicPort.setPressure(pressure);
    hydraulicPort.setVolumeFlowRate(volumeFlowRate);

    modelDescription.getHydraulicPorts().add(hydraulicPort);

    HydraulicPortValidator hydraulicPortValidator = new HydraulicPortValidator();
    List<ValidationDiagnostic<HydraulicPort>> diagnostics = hydraulicPortValidator.validate(modelDescription);

    assertTrue(diagnostics.isEmpty());
  }
}
