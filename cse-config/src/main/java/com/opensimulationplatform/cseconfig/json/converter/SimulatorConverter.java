package com.opensimulationplatform.cseconfig.json.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.cseconfig.json.model.OspSystemStructure;
import com.opensimulationplatform.cseconfig.json.model.Simulator;

public class SimulatorConverter {
  
  public static OspSimulator convert(Simulator simulator, OspSystemStructure ospSystemStructure, ModelDescription modelDescription) {
    OspSimulator ospSimulator = new OspSimulator(simulator.getName(), simulator.getSource());
    
    modelDescription.getOspPlugs().values().forEach(ospSimulator::addPlug);
    modelDescription.getOspSockets().values().forEach(ospSimulator::addSocket);
    modelDescription.getOspBonds().values().forEach(ospSimulator::addBond);
    
    ospSystemStructure.getVariableConnections().forEach(variableConnection -> {
      if (variableConnection.getSourceSimulator().equals(simulator.getName())) {
        ospSimulator.addVariable(new OspVariable(variableConnection.getSourceVariable()));
      } else if (variableConnection.getTargetSimulator().equals(simulator.getName())) {
        ospSimulator.addVariable(new OspVariable(variableConnection.getTargetVariable()));
      }
    });
    
    return ospSimulator;
  }
}
