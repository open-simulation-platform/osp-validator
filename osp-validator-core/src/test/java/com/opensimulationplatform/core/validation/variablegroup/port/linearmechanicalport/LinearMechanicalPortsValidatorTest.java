package com.opensimulationplatform.core.validation.variablegroup.port.linearmechanicalport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.LinearMechanicalPort;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinearMechanicalPortsValidatorTest {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Force force = new Force();
    force.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    LinearVelocity linearVelocity = new LinearVelocity();
    linearVelocity.setVariables(Arrays.asList(v2));

    LinearMechanicalPort linearMechanicalPort = new LinearMechanicalPort();
    linearMechanicalPort.setForce(force);
    linearMechanicalPort.setLinearVelocity(linearVelocity);

    modelDescription.getLinearMechanicalPorts().add(linearMechanicalPort);

    LinearMechanicalPortsValidator linearMechanicalPortsValidator = new LinearMechanicalPortsValidator();
    List<ValidationDiagnostic<LinearMechanicalPort>> diagnostics = linearMechanicalPortsValidator.validate(modelDescription);

    assertEquals(1, diagnostics.size());
    assertEquals(linearMechanicalPort, diagnostics.get(0).getValidatedObject());
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Force force = new Force();
    force.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.INPUT);
    LinearVelocity linearVelocity = new LinearVelocity();
    linearVelocity.setVariables(Arrays.asList(v2));

    LinearMechanicalPort linearMechanicalPort = new LinearMechanicalPort();
    linearMechanicalPort.setForce(force);
    linearMechanicalPort.setLinearVelocity(linearVelocity);

    modelDescription.getLinearMechanicalPorts().add(linearMechanicalPort);

    LinearMechanicalPortsValidator linearMechanicalPortsValidator = new LinearMechanicalPortsValidator();
    List<ValidationDiagnostic<LinearMechanicalPort>> diagnostics = linearMechanicalPortsValidator.validate(modelDescription);

    assertTrue(diagnostics.isEmpty());
  }
}