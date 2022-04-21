package com.opensimulationplatform.core.validation.variableconnection;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class VariableConnectionsValidatorTest {
  @Test
  public void invalid() {
    SystemStructure systemStructure = new SystemStructure();

    VariableConnection variableConnection = new VariableConnection();

    Simulator simulatorA = new Simulator();

    Variable variableA = new Variable();

    Unit unitA = new Unit();
    unitA.setExponent(Unit.Exponent.KILOGRAM, 2);

    variableA.setName("variableA");
    variableA.setUnit(unitA);
    variableA.setType(Variable.Type.INTEGER);
    variableA.setCausality(Variable.Causality.INPUT);
    variableA.setAxis(Variable.Axis.X);

    simulatorA.getModelDescription().getVariables().add(variableA);

    variableConnection.setSimulatorA(simulatorA);
    variableConnection.setVariableA(variableA);

    Simulator simulatorB = new Simulator();

    Variable variableB = new Variable();

    Unit unitB = new Unit();
    unitB.setExponent(Unit.Exponent.KILOGRAM, 1);

    variableB.setName("variableB");
    variableB.setUnit(unitB);
    variableB.setType(Variable.Type.REAL);
    variableB.setCausality(Variable.Causality.INPUT);
    variableB.setAxis(Variable.Axis.Y);

    simulatorB.getModelDescription().getVariables().add(variableB);

    variableConnection.setSimulatorB(simulatorB);
    variableConnection.setVariableB(variableB);

    systemStructure.getSimulators().add(simulatorA);
    systemStructure.getSimulators().add(simulatorB);

    systemStructure.getVariableConnections().add(variableConnection);

    VariableConnectionsValidator validator = new VariableConnectionsValidator();

    List<ValidationDiagnostic<VariableConnection>> diagnostics = validator.validate(systemStructure);

    assertEquals(4, diagnostics.size());
  }

  @Test
  public void valid() {
    SystemStructure systemStructure = new SystemStructure();

    VariableConnection variableConnection = new VariableConnection();

    Simulator simulatorA = new Simulator();

    Variable variableA = new Variable();

    Unit unitA = new Unit();
    unitA.setExponent(Unit.Exponent.KILOGRAM, 1);

    variableA.setName("variableA");
    variableA.setUnit(unitA);
    variableA.setType(Variable.Type.REAL);
    variableA.setCausality(Variable.Causality.OUTPUT);

    simulatorA.getModelDescription().getVariables().add(variableA);

    variableConnection.setSimulatorA(simulatorA);
    variableConnection.setVariableA(variableA);

    Simulator simulatorB = new Simulator();

    Variable variableB = new Variable();

    Unit unitB = new Unit();
    unitB.setExponent(Unit.Exponent.KILOGRAM, 1);

    variableB.setName("variableB");
    variableB.setUnit(unitB);
    variableB.setType(Variable.Type.REAL);
    variableB.setCausality(Variable.Causality.INPUT);

    simulatorB.getModelDescription().getVariables().add(variableB);

    variableConnection.setSimulatorB(simulatorB);
    variableConnection.setVariableB(variableB);

    systemStructure.getSimulators().add(simulatorA);
    systemStructure.getSimulators().add(simulatorB);

    systemStructure.getVariableConnections().add(variableConnection);

    VariableConnectionsValidator validator = new VariableConnectionsValidator();

    List<ValidationDiagnostic<VariableConnection>> diagnostics = validator.validate(systemStructure);

    assertEquals(0, diagnostics.size());
  }
}