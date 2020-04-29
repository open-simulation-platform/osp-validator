package com.opensimulationplatform.core.validation.variablegroup.electromagneticquasiport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElectromagneticQuasiPortValidatorTest {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Voltage voltage = new Voltage();
    voltage.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    Charge charge = new Charge();
    charge.setVariables(Arrays.asList(v2));

    ElectromagneticQuasiPort electromagneticQuasiPort = new ElectromagneticQuasiPort();
    electromagneticQuasiPort.setVoltage(voltage);
    electromagneticQuasiPort.setCharge(charge);

    modelDescription.getElectromagneticQuasiPorts().add(electromagneticQuasiPort);

    ElectromagneticQuasiPortValidator electromagneticQuasiPortValidator = new ElectromagneticQuasiPortValidator();
    List<ValidationDiagnostic<ElectromagneticQuasiPort>> diagnostics = electromagneticQuasiPortValidator.validate(modelDescription);

    assertEquals(1, diagnostics.size());
    assertEquals(electromagneticQuasiPort, diagnostics.get(0).getValidatedObject());
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Voltage voltage = new Voltage();
    voltage.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.INPUT);
    Charge charge = new Charge();
    charge.setVariables(Arrays.asList(v2));

    ElectromagneticQuasiPort electromagneticQuasiPort = new ElectromagneticQuasiPort();
    electromagneticQuasiPort.setVoltage(voltage);
    electromagneticQuasiPort.setCharge(charge);

    modelDescription.getElectromagneticQuasiPorts().add(electromagneticQuasiPort);

    ElectromagneticQuasiPortValidator electromagneticQuasiPortValidator = new ElectromagneticQuasiPortValidator();
    List<ValidationDiagnostic<ElectromagneticQuasiPort>> diagnostics = electromagneticQuasiPortValidator.validate(modelDescription);

    assertTrue(diagnostics.isEmpty());
  }
}
