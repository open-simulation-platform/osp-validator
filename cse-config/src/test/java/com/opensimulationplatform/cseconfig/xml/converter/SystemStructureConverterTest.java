package com.opensimulationplatform.cseconfig.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.*;
import com.opensimulationplatform.cseconfig.xml.model.*;
import com.opensimulationplatform.modeldescription.xml.converter.OspModelDescriptionConverter;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SystemStructureConverterTest {
  @Test
  public void canConvert() {
    TestSetup testSetup = new TestSetup();
    OspSystemStructure ospSystemStructure = testSetup.ospSystemStructure;
    HashMap<Simulators.Simulator, ModelDescription> modelDescriptions = new HashMap<>();
    Simulators simulators = ospSystemStructure.getSimulators();
    simulators.getSimulator().forEach(s -> {
      modelDescriptions.put(s, OspModelDescriptionConverter.convert(testSetup.ospModelDescription));
    });
    
    SystemStructure systemStructure = SystemStructureConverter.convert(ospSystemStructure, modelDescriptions);
  
    Map<String, OspSimulator> ospSimulators = systemStructure.getSimulators();
    List<Simulators.Simulator> simulatorList = simulators.getSimulator();
    assertEquals(simulatorList.size(), ospSimulators.size());
    
    for (Simulators.Simulator simulator : simulatorList) {
      assertEquals(simulator.getName(), ospSimulators.get(simulator.getName()).getName());
    }
  
    List<BondConnections.BondConnection> bondConnections = ospSystemStructure.getBondConnections().getBondConnection();
    List<OspBondConnection> ospBondConnections = systemStructure.getOspBondConnections();
    assertEquals(bondConnections.size(), ospBondConnections.size());
    for (int i = 0; i < bondConnections.size(); i++) {
      BondConnections.BondConnection bondConnection = bondConnections.get(i);
      OspBondConnection ospBondConnection = ospBondConnections.get(i);
  
      ConnectionEndpoint bondA = bondConnection.getBond().get(0);
      ConnectionEndpoint bondB = bondConnection.getBond().get(1);
      
      assertEquals(bondA.getEndpoint(), ospBondConnection.getOspBondA().getName());
      assertEquals(bondB.getEndpoint(), ospBondConnection.getOspBondB().getName());
      assertEquals(bondA.getSimulator(), ospBondConnection.getOspSimulatorA().getName());
      assertEquals(bondB.getSimulator(), ospBondConnection.getOspSimulatorB().getName());
    }
  
    List<PlugSocketConnections.PlugSocketConnection> plugSocketConnections = ospSystemStructure.getPlugSocketConnections().getPlugSocketConnection();
    List<OspPlugSocketConnection> ospPlugSocketConnections = systemStructure.getOspPlugSocketConnections();
    assertEquals(plugSocketConnections.size(), ospPlugSocketConnections.size());
    for (int i = 0; i < plugSocketConnections.size(); i++) {
      PlugSocketConnections.PlugSocketConnection plugSocketConnection = plugSocketConnections.get(i);
      OspPlugSocketConnection ospPlugSocketConnection = ospPlugSocketConnections.get(i);
  
      ConnectionEndpoint source = plugSocketConnection.getSource();
      ConnectionEndpoint target = plugSocketConnection.getTarget();
      
      assertEquals(source.getEndpoint(), ospPlugSocketConnection.getOspPlug().getName());
      assertEquals(target.getEndpoint(), ospPlugSocketConnection.getOspSocket().getName());
      assertEquals(source.getSimulator(), ospPlugSocketConnection.getSourceOspSimulator().getName());
      assertEquals(target.getSimulator(), ospPlugSocketConnection.getTargetOspSimulator().getName());
    }
  
    List<VariableConnections.ScalarConnection> variableConnections = ospSystemStructure.getVariableConnections().getScalarConnection();
    List<OspVariableConnection> ospVariableConnections = systemStructure.getOspVariableConnections();
    assertEquals(variableConnections.size(), ospVariableConnections.size());
    for (int i = 0; i < variableConnections.size(); i++) {
      VariableConnections.ScalarConnection variableConnection = variableConnections.get(i);
      OspVariableConnection ospVariableConnection = ospVariableConnections.get(i);
  
      ConnectionEndpoint source = variableConnection.getSource();
      ConnectionEndpoint target = variableConnection.getTarget();
      
      assertEquals(source.getSimulator(), ospVariableConnection.getSourceOspSimulator().getName());
      assertEquals(source.getEndpoint(), ospVariableConnection.getSourceOspVariable().getName());
      assertEquals(target.getSimulator(), ospVariableConnection.getTargetOspSimulator().getName());
      assertEquals(target.getEndpoint(), ospVariableConnection.getTargetOspVariable().getName());
    }
  }
}