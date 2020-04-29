package com.opensimulationplatform.core.validation.variablegroup.angularmechanicalport;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalport.AngularMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_AngularMechanicalPort_1_Test {
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

    Torque torque = new Torque();
    torque.setVariables(Arrays.asList(v1, v2));

    AngularVelocity angularVelocity = new AngularVelocity();
    angularVelocity.setVariables(Arrays.asList(v3, v4));

    AngularMechanicalPort angularMechanicalPort = new AngularMechanicalPort();
    angularMechanicalPort.setTorque(torque);
    angularMechanicalPort.setAngularVelocity(angularVelocity);

    modelDescription.getAngularMechanicalPorts().add(angularMechanicalPort);

    VE_AngularMechanicalPort_1 v = new VE_AngularMechanicalPort_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<AngularMechanicalPort>> diagnostics = v.validate();

    assertEquals(1, diagnostics.size());
    AngularMechanicalPort invalidAngularMechanicalPort = diagnostics.get(0).getValidatedObject();
    assertEquals(angularMechanicalPort, invalidAngularMechanicalPort);
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

    Torque torque = new Torque();
    torque.setVariables(Arrays.asList(v1, v2));

    AngularVelocity angularVelocity = new AngularVelocity();
    angularVelocity.setVariables(Arrays.asList(v3, v4));

    AngularMechanicalPort angularMechanicalPort = new AngularMechanicalPort();
    angularMechanicalPort.setTorque(torque);
    angularMechanicalPort.setAngularVelocity(angularVelocity);

    modelDescription.getAngularMechanicalPorts().add(angularMechanicalPort);

    VE_AngularMechanicalPort_1 v = new VE_AngularMechanicalPort_1();
    v.setContext(new ValidationContext(modelDescription));
    List<ValidationDiagnostic<AngularMechanicalPort>> diagnostics = v.validate();

    assertTrue(diagnostics.isEmpty());
  }
}
