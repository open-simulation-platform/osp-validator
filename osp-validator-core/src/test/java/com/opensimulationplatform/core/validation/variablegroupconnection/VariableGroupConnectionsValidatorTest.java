package com.opensimulationplatform.core.validation.variablegroupconnection;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class VariableGroupConnectionsValidatorTest {
  @Test
  public void invalid() {
    SystemStructure systemStructure = new SystemStructure();

    VariableGroupConnection variableGroupConnection = new VariableGroupConnection();

    Simulator simulatorA = new Simulator();

    Force force = new Force();

    Variable variableA = new Variable();

    Unit unitA = new Unit();
    unitA.setExponent(Unit.Exponent.KILOGRAM, 1);

    variableA.setUnit(unitA);
    variableA.setType(Variable.Type.REAL);
    variableA.setCausality(Variable.Causality.OUTPUT);

    force.getVariables().add(variableA);

    simulatorA.getModelDescription().getVariables().add(variableA);
    simulatorA.getModelDescription().getForces().add(force);

    variableGroupConnection.setSimulatorA(simulatorA);
    variableGroupConnection.setVariableGroupA(force);

    Simulator simulatorB = new Simulator();

    LinearVelocity linearVelocity = new LinearVelocity();

    Variable variableB = new Variable();

    Unit unitB = new Unit();
    unitB.setExponent(Unit.Exponent.KILOGRAM, 1);

    variableB.setUnit(unitB);
    variableB.setType(Variable.Type.REAL);
    variableB.setCausality(Variable.Causality.INPUT);

    linearVelocity.getVariables().add(variableB);

    simulatorB.getModelDescription().getVariables().add(variableB);
    simulatorB.getModelDescription().getLinearVelocities().add(linearVelocity);

    variableGroupConnection.setSimulatorB(simulatorB);
    variableGroupConnection.setVariableGroupB(linearVelocity);

    systemStructure.getSimulators().add(simulatorA);
    systemStructure.getSimulators().add(simulatorB);

    systemStructure.getVariableGroupConnections().add(variableGroupConnection);

    VariableGroupConnectionsValidator validator = new VariableGroupConnectionsValidator();

    List<ValidationDiagnostic<VariableGroupConnection>> diagnostics = validator.validate(systemStructure);

    assertEquals(1, diagnostics.size());
  }

  @Test
  public void valid() {
    SystemStructure systemStructure = new SystemStructure();

    VariableGroupConnection variableGroupConnection = new VariableGroupConnection();

    Simulator simulatorA = new Simulator();

    Force forceA = new Force();

    Variable variableA = new Variable();

    Unit unitA = new Unit();
    unitA.setExponent(Unit.Exponent.KILOGRAM, 1);

    variableA.setUnit(unitA);
    variableA.setType(Variable.Type.REAL);
    variableA.setCausality(Variable.Causality.OUTPUT);

    forceA.getVariables().add(variableA);

    simulatorA.getModelDescription().getVariables().add(variableA);
    simulatorA.getModelDescription().getForces().add(forceA);

    variableGroupConnection.setSimulatorA(simulatorA);
    variableGroupConnection.setVariableGroupA(forceA);

    Simulator simulatorB = new Simulator();

    Force forceB = new Force();

    Variable variableB = new Variable();

    Unit unitB = new Unit();
    unitB.setExponent(Unit.Exponent.KILOGRAM, 1);

    variableB.setUnit(unitB);
    variableB.setType(Variable.Type.REAL);
    variableB.setCausality(Variable.Causality.INPUT);

    forceB.getVariables().add(variableB);

    simulatorB.getModelDescription().getVariables().add(variableB);
    simulatorB.getModelDescription().getForces().add(forceB);

    variableGroupConnection.setSimulatorB(simulatorB);
    variableGroupConnection.setVariableGroupB(forceB);

    systemStructure.getSimulators().add(simulatorA);
    systemStructure.getSimulators().add(simulatorB);

    systemStructure.getVariableGroupConnections().add(variableGroupConnection);

    VariableGroupConnectionsValidator validator = new VariableGroupConnectionsValidator();
    List<ValidationDiagnostic<VariableGroupConnection>> diagnostics = validator.validate(systemStructure);

    assertEquals(0, diagnostics.size());
  }
}