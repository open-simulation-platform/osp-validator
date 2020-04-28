package com.opensimulationplatform.systemstructure.xml.factory;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.systemstructure.TestResources;
import com.opensimulationplatform.systemstructure.util.MockFmuLocator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SystemStructureFactoryTest {
  @Test
  public void canCreate() {
    SystemStructureFactory factory = new SystemStructureFactory();
    factory.setFmuLocator(new MockFmuLocator());
    SystemStructure systemStructure = factory.create(TestResources.OSP_SYSTEM_STRUCTURE_CRANE);

    List<Simulator> simulators = systemStructure.getSimulators();
    List<VariableConnection> variableConnections = systemStructure.getVariableConnections();
    List<VariableGroupConnection> variableGroupConnections = systemStructure.getVariableGroupConnections();

    assertEquals(2, simulators.size());
    Simulator s1 = simulators.get(0);
    assertEquals("crane_controller", s1.getName().get());
    Simulator s2 = simulators.get(1);
    assertEquals("knuckle_boom_crane", s2.getName().get());

    assertEquals(1, variableConnections.size());
    VariableConnection variableConnection = variableConnections.get(0);
    Simulator simulatorA = variableConnection.getSimulatorA();
    Variable variableA = variableConnection.getVariableA();
    Simulator simulatorB = variableConnection.getSimulatorB();
    Variable variableB = variableConnection.getVariableB();
    assertEquals("crane_controller", simulatorA.getName().get());
    assertEquals("Act_Limits[1]", variableA.getName().get());
    assertEquals("knuckle_boom_crane", simulatorB.getName().get());
    assertEquals("Act_Limits[1]", variableB.getName().get());

    assertEquals(1, variableGroupConnections.size());
    VariableGroupConnection variableGroupConnection = variableGroupConnections.get(0);
    Simulator simulatorA1 = variableGroupConnection.getSimulatorA();
    VariableGroup variableGroupA = variableGroupConnection.getVariableGroupA();
    Simulator simulatorB1 = variableGroupConnection.getSimulatorB();
    VariableGroup variableGroupB = variableGroupConnection.getVariableGroupB();
    assertEquals("crane_controller", simulatorA1.getName().get());
    assertEquals("linear_mechanical_port", variableGroupA.getName().get());
    assertEquals("knuckle_boom_crane", simulatorB1.getName().get());
    assertEquals("linear_mechanical_port", variableGroupB.getName().get());
  }
}