package com.opensimulationplatform.core.validation.variablegroupconnection;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_VariableGroupConnection1_Test {
  @Test
  public void invalid() {
    SystemStructure systemStructure = new SystemStructure();
    Simulator simulator = new Simulator();
    systemStructure.getSimulators().add(simulator);
    ModelDescription modelDescription = new ModelDescription();
    simulator.setModelDescription(modelDescription);

    Force va = new Force();
    va.setName("va");

    LinearVelocity vb = new LinearVelocity();
    vb.setName("vb");

    VariableGroupConnection vgc = new VariableGroupConnection();
    vgc.setVariableGroupA(va);
    vgc.setVariableGroupB(vb);
    vgc.setSimulatorA(new Simulator());
    vgc.setSimulatorB(new Simulator());

    modelDescription.getForces().add(va);
    modelDescription.getLinearVelocities().add(vb);
    systemStructure.getVariableGroupConnections().add(vgc);

    VE_VariableGroupConnection_1 v = new VE_VariableGroupConnection_1();
    v.setContext(new ValidationContext(systemStructure));
    List<ValidationDiagnostic<VariableGroupConnection>> diagnostics = v.validate();
    assertEquals(1, diagnostics.size());
    VariableGroupConnection invalidVariableConnection = diagnostics.get(0).getValidatedObject();
    assertEquals(vgc, invalidVariableConnection);
    assertEquals(va, invalidVariableConnection.getVariableGroupA());
    assertEquals(vb, invalidVariableConnection.getVariableGroupB());
  }

  @Test
  public void valid() {
    SystemStructure systemStructure = new SystemStructure();
    Simulator simulator = new Simulator();
    systemStructure.getSimulators().add(simulator);
    ModelDescription modelDescription = new ModelDescription();
    simulator.setModelDescription(modelDescription);

    VariableGroup va = new Force();
    va.setName("va");

    VariableGroup vb = new Force();
    vb.setName("vb");

    VariableGroupConnection vgc = new VariableGroupConnection();
    vgc.setVariableGroupA(va);
    vgc.setVariableGroupB(vb);
    vgc.setSimulatorA(new Simulator());
    vgc.setSimulatorB(new Simulator());


    VE_VariableGroupConnection_1 v = new VE_VariableGroupConnection_1();
    v.setContext(new ValidationContext(systemStructure));
    List<ValidationDiagnostic<VariableGroupConnection>> diagnostics = v.validate();
    assertTrue(diagnostics.isEmpty());
  }
}