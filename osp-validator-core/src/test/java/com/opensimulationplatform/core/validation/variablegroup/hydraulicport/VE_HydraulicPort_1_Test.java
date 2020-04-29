package com.opensimulationplatform.core.validation.variablegroup.hydraulicport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_HydraulicPort_1_Test {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    v1.setUnit(new Unit());
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setUnit(new Unit());
    v2.setType(Variable.Type.REAL);

    Variable v3 = new Variable();
    v3.setCausality(Variable.Causality.OUTPUT);
    v3.setUnit(new Unit());
    v3.setType(Variable.Type.REAL);

    Variable v4 = new Variable();
    v4.setCausality(Variable.Causality.OUTPUT);
    v4.setUnit(new Unit());
    v4.setType(Variable.Type.REAL);

    Pressure pressure = new Pressure();
    pressure.setVariables(Arrays.asList(v1, v2));

    VolumeFlowRate volumeFlowRate = new VolumeFlowRate();
    volumeFlowRate.setVariables(Arrays.asList(v3, v4));

    HydraulicPort hydraulicPort = new HydraulicPort();
    hydraulicPort.setPressure(pressure);
    hydraulicPort.setVolumeFlowRate(volumeFlowRate);

    modelDescription.getHydraulicPorts().add(hydraulicPort);

    VE_HydraulicPort_1 v = new VE_HydraulicPort_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<HydraulicPort>> diagnostics = v.validate();

    assertEquals(1, diagnostics.size());
    HydraulicPort invalidHydraulicPort = diagnostics.get(0).getValidatedObject();
    assertEquals(hydraulicPort, invalidHydraulicPort);
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    v1.setUnit(new Unit());
    v1.setType(Variable.Type.REAL);

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    v2.setUnit(new Unit());
    v2.setType(Variable.Type.REAL);

    Variable v3 = new Variable();
    v3.setCausality(Variable.Causality.INPUT);
    v3.setUnit(new Unit());
    v3.setType(Variable.Type.REAL);

    Variable v4 = new Variable();
    v4.setCausality(Variable.Causality.INPUT);
    v4.setUnit(new Unit());
    v4.setType(Variable.Type.REAL);

    Pressure pressure = new Pressure();
    pressure.setVariables(Arrays.asList(v1, v2));

    VolumeFlowRate volumeFlowRate = new VolumeFlowRate();
    volumeFlowRate.setVariables(Arrays.asList(v3, v4));

    HydraulicPort hydraulicPort = new HydraulicPort();
    hydraulicPort.setPressure(pressure);
    hydraulicPort.setVolumeFlowRate(volumeFlowRate);

    modelDescription.getHydraulicPorts().add(hydraulicPort);

    VE_HydraulicPort_1 v = new VE_HydraulicPort_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<HydraulicPort>> diagnostics = v.validate();

    assertTrue(diagnostics.isEmpty());
  }
}
