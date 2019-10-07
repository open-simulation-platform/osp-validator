package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.systemstructure.xml.model.ConnectionEndpoint;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulatorsConverter {
  public static List<OspSimulator> convert(Simulators simulators, OspSystemStructure ospSystemStructure, Map<Simulators.Simulator, ModelDescription> modelDescriptions) {
    List<OspSimulator> ospSimulators = new ArrayList<>();
    
    for (Simulators.Simulator simulator : simulators.getSimulator()) {
      OspSimulator ospSimulator = new OspSimulator(simulator.getName(), simulator.getSource());
      ModelDescription modelDescription = modelDescriptions.get(simulator);
      
      modelDescription.getOspPlugs().values().forEach(ospSimulator::addPlug);
      modelDescription.getOspSockets().values().forEach(ospSimulator::addSocket);
      modelDescription.getOspBonds().values().forEach(ospSimulator::addBond);
      
      ospSystemStructure.getVariableConnections().getScalarConnection().forEach(scalarConnection -> {
        ConnectionEndpoint source = scalarConnection.getSource();
        ConnectionEndpoint target = scalarConnection.getTarget();
        if (source.getSimulator().equals(simulator.getName())) {
          ospSimulator.addVariable(new OspVariable(source.getEndpoint()));
        } else if (target.getSimulator().equals(simulator.getName())) {
          ospSimulator.addVariable(new OspVariable(target.getEndpoint()));
        }
      });
      
      ospSimulators.add(ospSimulator);
    }
    
    return ospSimulators;
  }
}
