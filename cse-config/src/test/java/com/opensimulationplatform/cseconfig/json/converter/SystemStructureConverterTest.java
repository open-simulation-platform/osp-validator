package com.opensimulationplatform.cseconfig.json.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.*;
import com.opensimulationplatform.cseconfig.json.model.*;
import com.opensimulationplatform.modeldescription.json.converter.OspModelDescriptionConverter;
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
    Map<Simulator, ModelDescription> modelDescriptions = new HashMap<>();
    List<Simulator> simulators = ospSystemStructure.getSimulators();
    for (Simulator simulator : simulators) {
      ModelDescription modelDescription = OspModelDescriptionConverter.convert(testSetup.ospModelDescription);
      modelDescriptions.put(simulator, modelDescription);
    }
  
    SystemStructure systemStructure = SystemStructureConverter.convert(ospSystemStructure, modelDescriptions);
    
    Map<String, OspSimulator> ospSimulators = systemStructure.getSimulators();
    assertEquals(simulators.size(), ospSimulators.size());
    for (Simulator simulator : simulators) {
      assertEquals(simulator.getName(), ospSimulators.get(simulator.getName()).getName());
    }
    
    List<BondConnection> bondConnections = ospSystemStructure.getBondConnections();
    List<OspBondConnection> ospBondConnections = systemStructure.getOspBondConnections();
    assertEquals(bondConnections.size(), ospBondConnections.size());
    for (int i = 0; i < bondConnections.size(); i++) {
      BondConnection bondConnection = bondConnections.get(i);
      OspBondConnection ospBondConnection = ospBondConnections.get(i);
      
      assertEquals(bondConnection.getBondA(), ospBondConnection.getOspBondA().getName());
      assertEquals(bondConnection.getBondB(), ospBondConnection.getOspBondB().getName());
      assertEquals(bondConnection.getSimulatorA(), ospBondConnection.getOspSimulatorA().getName());
      assertEquals(bondConnection.getSimulatorB(), ospBondConnection.getOspSimulatorB().getName());
    }
    
    List<PlugSocketConnection> plugSocketConnections = ospSystemStructure.getPlugSocketConnections();
    List<OspPlugSocketConnection> ospPlugSocketConnections = systemStructure.getOspPlugSocketConnections();
    assertEquals(plugSocketConnections.size(), ospPlugSocketConnections.size());
    for (int i = 0; i < plugSocketConnections.size(); i++) {
      PlugSocketConnection plugSocketConnection = plugSocketConnections.get(i);
      OspPlugSocketConnection ospPlugSocketConnection = ospPlugSocketConnections.get(i);
      
      assertEquals(plugSocketConnection.getPlug(), ospPlugSocketConnection.getOspPlug().getName());
      assertEquals(plugSocketConnection.getSocket(), ospPlugSocketConnection.getOspSocket().getName());
      assertEquals(plugSocketConnection.getSourceSimulator(), ospPlugSocketConnection.getSourceOspSimulator().getName());
      assertEquals(plugSocketConnection.getTargetSimulator(), ospPlugSocketConnection.getTargetOspSimulator().getName());
    }
    
    List<VariableConnection> variableConnections = ospSystemStructure.getVariableConnections();
    List<OspVariableConnection> ospVariableConnections = systemStructure.getOspVariableConnections();
    assertEquals(variableConnections.size(), ospVariableConnections.size());
    for (int i = 0; i < variableConnections.size(); i++) {
      VariableConnection variableConnection = variableConnections.get(i);
      OspVariableConnection ospVariableConnection = ospVariableConnections.get(i);
      
      assertEquals(variableConnection.getSourceSimulator(), ospVariableConnection.getSourceOspSimulator().getName());
      assertEquals(variableConnection.getSourceVariable(), ospVariableConnection.getSourceOspVariable().getName());
      assertEquals(variableConnection.getTargetSimulator(), ospVariableConnection.getTargetOspSimulator().getName());
      assertEquals(variableConnection.getTargetVariable(), ospVariableConnection.getTargetOspVariable().getName());
    }
  }
}