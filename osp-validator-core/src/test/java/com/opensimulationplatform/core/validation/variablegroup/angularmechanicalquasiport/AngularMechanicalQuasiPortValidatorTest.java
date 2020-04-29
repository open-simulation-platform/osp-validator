package com.opensimulationplatform.core.validation.variablegroup.angularmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AngularMechanicalQuasiPortValidatorTest {
  @Test
  public void invalid() {
    ModelDescription modelDescription = new ModelDescription();

    Variable v1 = new Variable();
    v1.setCausality(Variable.Causality.OUTPUT);
    Torque torque = new Torque();
    torque.setVariables(Arrays.asList(v1));

    Variable v2 = new Variable();
    v2.setCausality(Variable.Causality.OUTPUT);
    AngularDisplacement angularDisplacement = new AngularDisplacement();
    angularDisplacement.setVariables(Arrays.asList(v2));

    AngularMechanicalQuasiPort angularMechanicalQuasiPort = new AngularMechanicalQuasiPort();
    angularMechanicalQuasiPort.setTorque(torque);
    angularMechanicalQuasiPort.setAngularDisplacement(angularDisplacement);

    modelDescription.getAngularMechanicalQuasiPorts().add(angularMechanicalQuasiPort);

    AngularMechanicalQuasiPortValidator angularMechanicalQuasiPortValidator = new AngularMechanicalQuasiPortValidator();
    List<ValidationDiagnostic<AngularMechanicalQuasiPort>> diagnostics = angularMechanicalQuasiPortValidator.validate(modelDescription);

    assertEquals(1, diagnostics.size());
    assertEquals(angularMechanicalQuasiPort, diagnostics.get(0).getValidatedObject());
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
    AngularDisplacement angularDisplacement = new AngularDisplacement();
    angularDisplacement.setVariables(Arrays.asList(v2));

    AngularMechanicalQuasiPort angularMechanicalQuasiPort = new AngularMechanicalQuasiPort();
    angularMechanicalQuasiPort.setTorque(torque);
    angularMechanicalQuasiPort.setAngularDisplacement(angularDisplacement);

    modelDescription.getAngularMechanicalQuasiPorts().add(angularMechanicalQuasiPort);

    AngularMechanicalQuasiPortValidator angularMechanicalQuasiPortValidator = new AngularMechanicalQuasiPortValidator();
    List<ValidationDiagnostic<AngularMechanicalQuasiPort>> diagnostics = angularMechanicalQuasiPortValidator.validate(modelDescription);

    assertTrue(diagnostics.isEmpty());
  }
}
