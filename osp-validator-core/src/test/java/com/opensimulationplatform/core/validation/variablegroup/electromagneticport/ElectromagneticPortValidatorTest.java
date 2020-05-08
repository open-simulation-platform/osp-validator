package com.opensimulationplatform.core.validation.variablegroup.electromagneticport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticport.ElectromagneticPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.current.Current;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElectromagneticPortValidatorTest {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Voltage voltage = new Voltage();
    voltage.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    Current current = new Current();
    current.setVariables(Arrays.asList(v2));

    ElectromagneticPort electromagneticPort = new ElectromagneticPort();
    electromagneticPort.setVoltage(voltage);
    electromagneticPort.setCurrent(current);

    modelDescription.getElectromagneticPorts().add(electromagneticPort);

    ElectromagneticPortValidator electromagneticPortValidator = new ElectromagneticPortValidator();
    List<ValidationDiagnostic<ElectromagneticPort>> diagnostics = electromagneticPortValidator.validate(modelDescription);

    assertEquals(1, diagnostics.size());
    assertEquals(electromagneticPort, diagnostics.get(0).getValidatedObject());
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
    Current current = new Current();
    current.setVariables(Arrays.asList(v2));

    ElectromagneticPort electromagneticPort = new ElectromagneticPort();
    electromagneticPort.setVoltage(voltage);
    electromagneticPort.setCurrent(current);

    modelDescription.getElectromagneticPorts().add(electromagneticPort);

    ElectromagneticPortValidator electromagneticPortValidator = new ElectromagneticPortValidator();
    List<ValidationDiagnostic<ElectromagneticPort>> diagnostics = electromagneticPortValidator.validate(modelDescription);

    assertTrue(diagnostics.isEmpty());
  }
}
