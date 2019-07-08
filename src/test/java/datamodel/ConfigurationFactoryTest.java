package datamodel;

import jsonmodel.parsing.ConfigurationJsonFileParser;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConfigurationFactoryTest {
  @Test
  public void canCreate() {
    Configuration configuration = ConfigurationFactory.create(ConfigurationJsonFileParser.parse(new File("./src/test/resources/cse-config.json")));
  
    assertTrue(configuration.getSimulators().containsKey("Simulator_1"));
    assertTrue(configuration.getSimulators().containsKey("Simulator_2"));
    
    List<VariableConnection> variableConnections = configuration.getVariableConnections();
    assertEquals(1, variableConnections.size());
    for (VariableConnection variableConnection : variableConnections) {
      Variable sourceVariable = variableConnection.getSourceVariable();
      assertEquals("o_variable_1", sourceVariable.getName());
      assertEquals("Simulator_1", variableConnection.getSourceSimulator().getName());
      
      Variable targetVariable = variableConnection.getTargetVariable();
      assertEquals("i_variable_1", targetVariable.getName());
      assertEquals("Simulator_2", variableConnection.getTargetSimulator().getName());
    }
    
    List<PlugSocketConnection> plugSocketConnections = configuration.getPlugSocketConnections();
    assertEquals(1, plugSocketConnections.size());
    for (PlugSocketConnection plugSocketConnection : plugSocketConnections) {
      Plug plug = plugSocketConnection.getPlug();
      assertTrue(plugSocketConnection.getSourceSimulator().getPlugs().containsKey(plug.getName()));
      assertEquals("Simulator_1", plugSocketConnection.getSourceSimulator().getName());
      assertEquals("plug_5", plug.getName());
      
      Socket socket = plugSocketConnection.getSocket();
      assertTrue(plugSocketConnection.getTargetSimulator().getSockets().containsKey(socket.getName()));
      assertEquals("Simulator_2", plugSocketConnection.getTargetSimulator().getName());
      assertEquals("socket_5", socket.getName());
    }
    
    List<BondConnection> bondConnections = configuration.getBondConnections();
    assertEquals(1, bondConnections.size());
    for (BondConnection bondConnection : bondConnections) {
      List<Socket> aSockets = bondConnection.getBondA().getSockets();
      List<Plug> bPlugs = bondConnection.getBondB().getPlugs();
      assertEquals(aSockets.size(), bPlugs.size());
      assertEquals(2, aSockets.size());
      assertEquals("plug_3", bPlugs.get(0).getName());
      assertEquals("plug_4", bPlugs.get(1).getName());
      assertEquals("socket_1", aSockets.get(0).getName());
      assertEquals("socket_2", aSockets.get(1).getName());
  
      List<Socket> bSockets = bondConnection.getBondB().getSockets();
      List<Plug> aPlugs = bondConnection.getBondA().getPlugs();
      assertEquals(bSockets.size(), aPlugs.size());
      assertEquals(2, bSockets.size());
      assertEquals("plug_1", aPlugs.get(0).getName());
      assertEquals("plug_2", aPlugs.get(1).getName());
      assertEquals("socket_3", bSockets.get(0).getName());
      assertEquals("socket_4", bSockets.get(1).getName());
    }
  }
}