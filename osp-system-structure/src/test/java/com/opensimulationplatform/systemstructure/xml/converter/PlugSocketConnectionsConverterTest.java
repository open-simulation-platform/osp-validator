package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.OspPlugSocketConnection;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.modeldescription.xml.converter.OspModelDescriptionConverter;
import com.opensimulationplatform.systemstructure.xml.model.ConnectionEndpoint;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.model.PlugSocketConnections;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PlugSocketConnectionsConverterTest {
  @Test
  public void canConvert() {
    TestSetup testSetup = new TestSetup();
    PlugSocketConnections plugSocketConnections = testSetup.plugSocketConnections;
    OspSystemStructure ospSystemStructure = testSetup.ospSystemStructure;
    
    Map<Simulators.Simulator, ModelDescription> modelDescriptionMap = new HashMap<>();
    List<Simulators.Simulator> simulator = testSetup.simulators.getSimulator();
    for (Simulators.Simulator s : simulator) {
      modelDescriptionMap.put(s, OspModelDescriptionConverter.convert(testSetup.ospModelDescription));
    }
  
    List<OspSimulator> ospSimulators = SimulatorsConverter.convert(testSetup.simulators, ospSystemStructure, modelDescriptionMap);
    
    List<OspPlugSocketConnection> ospPlugSocketConnections = PlugSocketConnectionsConverter.convert(plugSocketConnections, ospSimulators);
  
    assertEquals(plugSocketConnections.getPlugSocketConnection().size(), ospPlugSocketConnections.size());
    for (int i = 0; i < plugSocketConnections.getPlugSocketConnection().size(); i++) {
      PlugSocketConnections.PlugSocketConnection plugSocketConnection = plugSocketConnections.getPlugSocketConnection().get(i);
      OspPlugSocketConnection ospPlugSocketConnection = ospPlugSocketConnections.get(i);
  
      ConnectionEndpoint source = plugSocketConnection.getSource();
      ConnectionEndpoint target = plugSocketConnection.getTarget();
  
      assertEquals(source.getSimulator(), ospPlugSocketConnection.getSourceOspSimulator().getName());
      assertEquals(source.getEndpoint(), ospPlugSocketConnection.getOspPlug().getName());
      assertEquals(target.getSimulator(), ospPlugSocketConnection.getTargetOspSimulator().getName());
      assertEquals(target.getEndpoint(), ospPlugSocketConnection.getOspSocket().getName());
    }
  }
}