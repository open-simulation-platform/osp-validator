package com.opensimulationplatform.cseconfig.xml.parser;

import com.opensimulationplatform.cseconfig.TestResources;
import com.opensimulationplatform.cseconfig.xml.model.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class OspSystemStructureParserTest {
  @Test
  public void canParse() {
    OspSystemStructure configuration = OspSystemStructureParser.parse(TestResources.SYSTEM_STRUCTURE_XML.toFile());
  
    List<Simulators.Simulator> simulators = configuration.getSimulators().getSimulator();
    assertEquals(2, simulators.size());
    for (int i = 0; i < simulators.size(); i++) {
      Simulators.Simulator simulator = simulators.get(i);
      assertEquals("Simulator_" + (i + 1), simulator.getName());
    
      String source = simulator.getSource();
      assertEquals("./path/to/simulator.fmu", source);
    }
  
    List<VariableConnections.ScalarConnection> variableConnections = configuration.getVariableConnections().getScalarConnection();
    assertEquals(1, variableConnections.size());
    for (VariableConnections.ScalarConnection variableConnection : variableConnections) {
      ConnectionEndpoint source = variableConnection.getSource();
      ConnectionEndpoint target = variableConnection.getTarget();
      assertEquals("Simulator_1", source.getSimulator());
      assertEquals("o_variable_1", source.getEndpoint());
      assertEquals("Simulator_2", target.getSimulator());
      assertEquals("i_variable_1", target.getEndpoint());
    }
  
    List<PlugSocketConnections.PlugSocketConnection> plugSocketConnections = configuration.getPlugSocketConnections().getPlugSocketConnection();
    assertEquals(1, plugSocketConnections.size());
    for (PlugSocketConnections.PlugSocketConnection plugSocketConnection : plugSocketConnections) {
      ConnectionEndpoint source = plugSocketConnection.getSource();
      ConnectionEndpoint target = plugSocketConnection.getTarget();
      assertEquals("Simulator_1", source.getSimulator());
      assertEquals("plug_5", source.getEndpoint());
      assertEquals("Simulator_2", target.getSimulator());
      assertEquals("socket_5", target.getEndpoint());
    }
  
    List<BondConnections.BondConnection> bondConnections = configuration.getBondConnections().getBondConnection();
    assertEquals(1, bondConnections.size());
    for (BondConnections.BondConnection bondConnection : bondConnections) {
      ConnectionEndpoint bondA = bondConnection.getBond().get(0);
      ConnectionEndpoint bondB = bondConnection.getBond().get(1);
      assertEquals("Simulator_1", bondA.getSimulator());
      assertEquals("bond_1", bondA.getEndpoint());
      assertEquals("Simulator_2", bondB.getSimulator());
      assertEquals("bond_2", bondB.getEndpoint());
    }
  }
}