package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.OspBondConnection;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.modeldescription.xml.converter.OspModelDescriptionConverter;
import com.opensimulationplatform.systemstructure.xml.model.BondConnections;
import com.opensimulationplatform.systemstructure.xml.model.ConnectionEndpoint;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BondConnectionsConverterTest {
  @Test
  public void canConvert() {
    TestSetup testSetup = new TestSetup();
    
    BondConnections bondConnections = testSetup.bondConnections;
    OspSystemStructure ospSystemStructure = testSetup.ospSystemStructure;
    
    Simulators.Simulator simulatorA = testSetup.simulators.getSimulator().get(0);
    Simulators.Simulator simulatorB = testSetup.simulators.getSimulator().get(1);
    
    ModelDescription modelDescriptionA = OspModelDescriptionConverter.convert(testSetup.ospModelDescription);
    ModelDescription modelDescriptionB = OspModelDescriptionConverter.convert(testSetup.ospModelDescription);
    
    Map<Simulators.Simulator, ModelDescription> modelDescriptions = new HashMap<>();
    modelDescriptions.put(simulatorA, modelDescriptionA);
    modelDescriptions.put(simulatorB, modelDescriptionB);
    
    List<OspSimulator> ospSimulators = SimulatorsConverter.convert(testSetup.simulators, ospSystemStructure, modelDescriptions);
    
    List<OspBondConnection> ospBondConnections = BondConnectionsConverter.convert(bondConnections, ospSimulators);
  
    for (int i = 0; i < ospBondConnections.size(); i++) {
      BondConnections.BondConnection bondConnection = bondConnections.getBondConnection().get(i);
      ConnectionEndpoint bondA = bondConnection.getBond().get(0);
      ConnectionEndpoint bondB = bondConnection.getBond().get(1);
      OspBondConnection ospBondConnection = ospBondConnections.get(i);
      
      assertEquals(bondA.getSimulator(), ospBondConnection.getOspSimulatorA().getName());
      assertEquals(bondA.getEndpoint(), ospBondConnection.getOspBondA().getName());
      assertEquals(bondB.getSimulator(), ospBondConnection.getOspSimulatorB().getName());
      assertEquals(bondB.getEndpoint(), ospBondConnection.getOspBondB().getName());
    }
  }
}