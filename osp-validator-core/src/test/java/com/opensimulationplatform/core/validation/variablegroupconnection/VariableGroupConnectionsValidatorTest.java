package com.opensimulationplatform.core.validation.variablegroupconnection;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VariableGroupConnectionsValidatorTest {
  @Test
  public void invalid() {
    SystemStructure systemStructure = new SystemStructure();

    VariableGroupConnection variableGroupConnection = new VariableGroupConnection();

    Simulator simulatorA = new Simulator();

    Unit unitA = new Unit();
    unitA.setExponent(Unit.Exponent.METER, 1);

    Variable variableA = new Variable();
    variableA.setName("variableA");
    variableA.setUnit(unitA);
    variableA.setType(Variable.Type.REAL);
    variableA.setCausality(Variable.Causality.UNDEFINED);
    variableA.setAxis(Variable.Axis.X);

    Force force = new Force();
    force.setName("force");
    force.setVariables(Arrays.asList(variableA));

    simulatorA.getModelDescription().getVariables().add(variableA);
    simulatorA.getModelDescription().getForces().add(force);

    variableGroupConnection.setSimulatorA(simulatorA);
    variableGroupConnection.setVariableGroupA(force);

    Simulator simulatorB = new Simulator();

    Unit unitB = new Unit();
    unitB.setExponent(Unit.Exponent.KILOGRAM, 1);

    Variable variableB = new Variable();
    variableB.setName("variableB");
    variableB.setUnit(unitB);
    variableB.setType(Variable.Type.INTEGER);
    variableB.setCausality(Variable.Causality.OUTPUT);
    variableB.setAxis(Variable.Axis.Y);

    LinearVelocity linearVelocity = new LinearVelocity();
    linearVelocity.setName("linearVelocity");
    linearVelocity.setVariables(Arrays.asList(variableB));

    simulatorB.getModelDescription().getVariables().add(variableB);
    simulatorB.getModelDescription().getLinearVelocities().add(linearVelocity);

    variableGroupConnection.setSimulatorB(simulatorB);
    variableGroupConnection.setVariableGroupB(linearVelocity);

    systemStructure.getSimulators().add(simulatorA);
    systemStructure.getSimulators().add(simulatorB);

    systemStructure.getVariableGroupConnections().add(variableGroupConnection);

    VariableGroupConnectionsValidator validator = new VariableGroupConnectionsValidator();

    List<ValidationDiagnostic<VariableGroupConnection>> diagnostics = validator.validate(systemStructure);

    assertEquals(4, diagnostics.size());
  }

  @Test
  public void valid() {
    SystemStructure systemStructure = new SystemStructure();

    VariableGroupConnection variableGroupConnection = new VariableGroupConnection();

    Simulator simulatorA = new Simulator();

    Unit unitA = new Unit();
    unitA.setExponent(Unit.Exponent.KILOGRAM, 1);

    Variable variableA = new Variable();
    variableA.setName("variableA");
    variableA.setUnit(unitA);
    variableA.setType(Variable.Type.REAL);
    variableA.setCausality(Variable.Causality.INPUT);
    variableA.setAxis(Variable.Axis.X);

    Force f1 = new Force();
    f1.setName("f1");
    f1.setVariables(Arrays.asList(variableA));

    simulatorA.getModelDescription().getVariables().add(variableA);
    simulatorA.getModelDescription().getForces().add(f1);

    variableGroupConnection.setSimulatorA(simulatorA);
    variableGroupConnection.setVariableGroupA(f1);

    Simulator simulatorB = new Simulator();

    Unit unitB = new Unit();
    unitB.setExponent(Unit.Exponent.KILOGRAM, 1);

    Variable variableB = new Variable();
    variableB.setName("variableB");
    variableB.setUnit(unitB);
    variableB.setType(Variable.Type.REAL);
    variableB.setCausality(Variable.Causality.OUTPUT);
    variableB.setAxis(Variable.Axis.X);

    Force f2 = new Force();
    f2.setName("f2");
    f2.setVariables(Arrays.asList(variableB));

    simulatorB.getModelDescription().getVariables().add(variableB);
    simulatorB.getModelDescription().getForces().add(f2);

    variableGroupConnection.setSimulatorB(simulatorB);
    variableGroupConnection.setVariableGroupB(f2);

    systemStructure.getSimulators().add(simulatorA);
    systemStructure.getSimulators().add(simulatorB);

    systemStructure.getVariableGroupConnections().add(variableGroupConnection);

    VariableGroupConnectionsValidator validator = new VariableGroupConnectionsValidator();
    List<ValidationDiagnostic<VariableGroupConnection>> diagnostics = validator.validate(systemStructure);

    assertEquals(0, diagnostics.size());
  }
}