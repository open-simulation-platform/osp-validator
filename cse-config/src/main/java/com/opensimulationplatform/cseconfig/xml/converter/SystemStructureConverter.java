package com.opensimulationplatform.cseconfig.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.*;
import com.opensimulationplatform.cseconfig.xml.model.OspSystemStructure;
import com.opensimulationplatform.cseconfig.xml.model.Simulators;

import java.util.HashMap;
import java.util.List;

public class SystemStructureConverter {
  public static SystemStructure convert(OspSystemStructure ospSystemStructure, HashMap<Simulators.Simulator, ModelDescription> modelDescriptions) {
    SystemStructure systemStructure = new SystemStructure();
  
    List<OspSimulator> ospSimulators = SimulatorsConverter.convert(ospSystemStructure.getSimulators(), ospSystemStructure, modelDescriptions);
    List<OspVariableConnection> ospVariableConnections = VariableConnectionsConverter.convert(ospSystemStructure.getVariableConnections(), ospSimulators);
    List<OspPlugSocketConnection> ospPlugSocketConnections = PlugSocketConnectionsConverter.convert(ospSystemStructure.getPlugSocketConnections(), ospSimulators);
    List<OspBondConnection> ospBondConnections = BondConnectionsConverter.convert(ospSystemStructure.getBondConnections(), ospSimulators);
  
    ospSimulators.forEach(systemStructure::addSimulator);
    ospVariableConnections.forEach(systemStructure::addVariableConnection);
    ospPlugSocketConnections.forEach(systemStructure::addPlugSocketConnection);
    ospBondConnections.forEach(systemStructure::addBondConnection);
    
    return systemStructure;
  }
}
