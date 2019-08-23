package com.opensimulationplatform.cseconfig.jsonmodel;

import com.opensimulationplatform.cseconfig.TestResources;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConfigurationJsonFileParserTest {
  @Test
  public void canParse() {
    JsonCseConfiguration configuration = CseConfigurationJsonFileParser.parse(TestResources.CSE_CONFIG_JSON.toFile());
    
    List<JsonSimulator> simulators = configuration.getSimulators();
    assertEquals(2, simulators.size());
    for (int i = 0; i < simulators.size(); i++) {
      JsonSimulator simulator = simulators.get(i);
      assertEquals("Simulator_" + (i + 1), simulator.getName());
  
      String modelDefinition = simulator.getModelDefinition();
      assertEquals("./src/test/resources/parsing/model-definition.json", modelDefinition);
  
      String source = simulator.getSource();
      assertEquals("./path/to/simulator.fmu", source);
    }
  
    List<JsonVariableConnection> variableConnections = configuration.getVariableConnections();
    assertEquals(1, variableConnections.size());
    for (JsonVariableConnection variableConnection : variableConnections) {
      assertEquals("Simulator_1", variableConnection.getSourceSimulator());
      assertEquals("o_variable_1", variableConnection.getSourceVariable());
      assertEquals("Simulator_2", variableConnection.getTargetSimulator());
      assertEquals("i_variable_1", variableConnection.getTargetVariable());
    }
  
    List<JsonPlugSocketConnection> plugSocketConnections = configuration.getPlugSocketConnections();
    assertEquals(1, plugSocketConnections.size());
    for (JsonPlugSocketConnection plugSocketConnection : plugSocketConnections) {
      assertEquals("Simulator_1", plugSocketConnection.getSourceSimulator());
      assertEquals("plug_5", plugSocketConnection.getPlug());
      assertEquals("Simulator_2", plugSocketConnection.getTargetSimulator());
      assertEquals("socket_5", plugSocketConnection.getSocket());
    }
  
    List<JsonBondConnection> bondConnections = configuration.getBondConnections();
    assertEquals(1, bondConnections.size());
    for (JsonBondConnection bondConnection : bondConnections) {
      assertEquals("Simulator_1", bondConnection.getSimulatorA());
      assertEquals("bond_1", bondConnection.getBondA());
      assertEquals("Simulator_2", bondConnection.getSimulatorB());
      assertEquals("bond_2", bondConnection.getBondB());
    }
  }
}