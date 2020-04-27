package com.opensimulationplatform.core.validation.variableconnection;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.validation.ValidationContext;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VE_VariableConnection_3_Test {
  @Test
  public void invalid() {
    SystemStructure systemStructure = new SystemStructure();
    Simulator simulator = new Simulator();
    systemStructure.getSimulators().add(simulator);
    ModelDescription modelDescription = new ModelDescription();
    simulator.setModelDescription(modelDescription);

    Unit ua = new Unit();
    ua.setExponent(Unit.Exponent.KILOGRAM, 1);

    Unit ub = new Unit();
    ub.setExponent(Unit.Exponent.KILOGRAM, 2);

    Variable va = new Variable();
    va.setName("va");
    va.setType(Variable.Type.REAL);
    va.setCausality(Variable.Causality.INPUT);
    va.setUnit(ua);

    Variable vb = new Variable();
    vb.setName("vb");
    vb.setType(Variable.Type.INTEGER);
    vb.setCausality(Variable.Causality.INPUT);
    vb.setUnit(ub);

    VariableConnection vc = new VariableConnection();
    vc.setVariableA(va);
    vc.setVariableB(vb);
    vc.setSimulatorA(new Simulator());
    vc.setSimulatorB(new Simulator());

    modelDescription.getUnits().add(ua);
    modelDescription.getUnits().add(ub);
    modelDescription.getVariables().add(va);
    modelDescription.getVariables().add(vb);
    systemStructure.getVariableConnections().add(vc);

    VE_VariableConnection_3 v = new VE_VariableConnection_3();
    v.setContext(new ValidationContext(systemStructure));
    List<ValidationDiagnostic<VariableConnection>> diagnostics = v.validate();
    assertEquals(1, diagnostics.size());
    VariableConnection invalidVariableConnection = diagnostics.get(0).getValidatedObject();
    assertEquals(vc, invalidVariableConnection);
    assertEquals(va, invalidVariableConnection.getVariableA());
    assertEquals(vb, invalidVariableConnection.getVariableB());
  }

  @Test
  public void valid() {
    SystemStructure systemStructure = new SystemStructure();
    Simulator simulator = new Simulator();
    systemStructure.getSimulators().add(simulator);
    ModelDescription modelDescription = new ModelDescription();
    simulator.setModelDescription(modelDescription);

    Unit ua = new Unit();
    ua.setExponent(Unit.Exponent.KILOGRAM, 1);

    Unit ub = new Unit();
    ub.setExponent(Unit.Exponent.KILOGRAM, 1);

    Variable va = new Variable();
    va.setName("va");
    va.setType(Variable.Type.REAL);
    va.setCausality(Variable.Causality.INPUT);
    va.setUnit(ua);

    Variable vb = new Variable();
    vb.setName("vb");
    vb.setType(Variable.Type.INTEGER);
    vb.setCausality(Variable.Causality.INPUT);
    vb.setUnit(ub);

    VariableConnection vc = new VariableConnection();
    vc.setVariableA(va);
    vc.setVariableB(vb);
    vc.setSimulatorA(new Simulator());
    vc.setSimulatorB(new Simulator());

    modelDescription.getUnits().add(ua);
    modelDescription.getUnits().add(ub);
    modelDescription.getVariables().add(va);
    modelDescription.getVariables().add(vb);
    systemStructure.getVariableConnections().add(vc);

    VE_VariableConnection_3 v = new VE_VariableConnection_3();
    v.setContext(new ValidationContext(systemStructure));
    List<ValidationDiagnostic<VariableConnection>> diagnostics = v.validate();
    assertTrue(diagnostics.isEmpty());
  }
}