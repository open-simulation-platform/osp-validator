package com.opensimulationplatform.cseconfig.json.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.*;
import com.opensimulationplatform.cseconfig.json.model.OspSystemStructure;
import com.opensimulationplatform.cseconfig.json.model.Simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemStructureConverter {
  public static SystemStructure convert(OspSystemStructure ospSystemStructure, Map<Simulator, ModelDescription> modelDescriptions) {
    SystemStructure systemStructure = new SystemStructure();
    
    ospSystemStructure.getSimulators().forEach(simulator -> {
      ModelDescription modelDescription = modelDescriptions.get(simulator);
      OspSimulator ospSimulator = SimulatorConverter.convert(simulator, ospSystemStructure, modelDescription);
      systemStructure.addSimulator(ospSimulator);
    });
  
    List<OspSimulator> ospSimulators = new ArrayList<>(systemStructure.getSimulators().values());
    
    ospSystemStructure.getVariableConnections().forEach(variableConnection -> {
      OspVariableConnection ospVariableConnection = VariableConnectionConverter.convert(variableConnection, ospSimulators);
      systemStructure.addVariableConnection(ospVariableConnection);
    });
  
    ospSystemStructure.getPlugSocketConnections().forEach(plugSocketConnection -> {
      OspPlugSocketConnection ospPlugSocketConnection = PlugSocketConnectionConverter.convert(plugSocketConnection, ospSimulators);
      systemStructure.addPlugSocketConnection(ospPlugSocketConnection);
    });
    
    ospSystemStructure.getBondConnections().forEach(bondConnection -> {
      OspBondConnection ospBondConnection = BondConnectionConverter.convert(bondConnection, ospSimulators);
      systemStructure.addBondConnection(ospBondConnection);
    });
    
    return systemStructure;
  }
}
