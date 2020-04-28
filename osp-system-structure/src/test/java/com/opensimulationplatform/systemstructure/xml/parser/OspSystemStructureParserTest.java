package com.opensimulationplatform.systemstructure.xml.parser;

import com.opensimulationplatform.systemstructure.TestResources;
import com.opensimulationplatform.systemstructure.xml.model.Connections;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;
import com.opensimulationplatform.systemstructure.xml.model.VariableEndpoint;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OspSystemStructureParserTest {
  @Test
  public void valid() {
    OspSystemStructureParser parser = new OspSystemStructureParser();
    OspSystemStructure ospSystemStructure = parser.parse(TestResources.PARSER_SYSTEM_STRUCTURE_VALID_XML);
    List<Simulators.Simulator> simulators = ospSystemStructure.getSimulators().getSimulator();
    List<Connections.VariableConnection> variableConnections = ospSystemStructure.getConnections().getVariableConnection();
    List<Connections.VariableGroupConnection> variableGroupConnections = ospSystemStructure.getConnections().getVariableGroupConnection();

    assertEquals(2, simulators.size());
    Simulators.Simulator s1 = simulators.get(0);
    Simulators.Simulator s2 = simulators.get(1);
    assertEquals("s1", s1.getName());
    assertEquals("s2", s2.getName());

    assertEquals(1, variableConnections.size());
    Connections.VariableConnection variableConnection = variableConnections.get(0);
    List<VariableEndpoint> variableEndpoints = variableConnection.getVariable();
    assertEquals(2, variableEndpoints.size());
    VariableEndpoint variableEndpointA = variableEndpoints.get(0);
    assertEquals("s1", variableEndpointA.getSimulator());
    assertEquals("v1", variableEndpointA.getName());
    VariableEndpoint variableEndpointB = variableEndpoints.get(1);
    assertEquals("s2", variableEndpointB.getSimulator());
    assertEquals("v2", variableEndpointB.getName());

    assertEquals(1, variableGroupConnections.size());
    Connections.VariableGroupConnection variableGroupConnection = variableGroupConnections.get(0);
    List<VariableEndpoint> variableGroupEndpoints = variableGroupConnection.getVariableGroup();
    assertEquals(2, variableGroupEndpoints.size());
    VariableEndpoint variableGroupEndpointA = variableGroupEndpoints.get(0);
    assertEquals("s1", variableGroupEndpointA.getSimulator());
    assertEquals("vg1", variableGroupEndpointA.getName());
    VariableEndpoint variableGroupEndpointB = variableGroupEndpoints.get(1);
    assertEquals("s2", variableGroupEndpointB.getSimulator());
    assertEquals("vg2", variableGroupEndpointB.getName());
  }

  @Test(expected = RuntimeException.class)
  public void invalid() {
    OspSystemStructureParser parser = new OspSystemStructureParser();
    parser.parse(TestResources.PARSER_SYSTEM_STRUCTURE_INVALID_XML);
  }
}