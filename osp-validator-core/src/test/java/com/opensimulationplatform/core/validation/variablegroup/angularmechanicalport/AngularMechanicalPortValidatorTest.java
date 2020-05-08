package com.opensimulationplatform.core.validation.variablegroup.angularmechanicalport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalport.AngularMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AngularMechanicalPortValidatorTest {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Torque torque = new Torque();
    torque.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    AngularVelocity angularVelocity = new AngularVelocity();
    angularVelocity.setVariables(Arrays.asList(v2));

    AngularMechanicalPort angularMechanicalPort = new AngularMechanicalPort();
    angularMechanicalPort.setTorque(torque);
    angularMechanicalPort.setAngularVelocity(angularVelocity);

    modelDescription.getAngularMechanicalPorts().add(angularMechanicalPort);

    AngularMechanicalPortValidator angularMechanicalPortValidator = new AngularMechanicalPortValidator();
    List<ValidationDiagnostic<AngularMechanicalPort>> diagnostics = angularMechanicalPortValidator.validate(modelDescription);

    assertEquals(1, diagnostics.size());
    assertEquals(angularMechanicalPort, diagnostics.get(0).getValidatedObject());
  }

  @Test
  public void valid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Torque torque = new Torque();
    torque.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.INPUT);
    AngularVelocity angularVelocity = new AngularVelocity();
    angularVelocity.setVariables(Arrays.asList(v2));

    AngularMechanicalPort angularMechanicalPort = new AngularMechanicalPort();
    angularMechanicalPort.setTorque(torque);
    angularMechanicalPort.setAngularVelocity(angularVelocity);

    modelDescription.getAngularMechanicalPorts().add(angularMechanicalPort);

    AngularMechanicalPortValidator angularMechanicalPortValidator = new AngularMechanicalPortValidator();
    List<ValidationDiagnostic<AngularMechanicalPort>> diagnostics = angularMechanicalPortValidator.validate(modelDescription);

    assertTrue(diagnostics.isEmpty());
  }
}
