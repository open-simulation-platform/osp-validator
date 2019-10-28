package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.systemstructure.xml.model.ConnectionEndpoint;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimulatorsConverter {
  private static final Logger LOG = LoggerFactory.getLogger(SimulatorsConverter.class);
  
  public static List<OspSimulator> convert(Simulators simulators, OspSystemStructure ospSystemStructure, Map<Simulators.Simulator, ModelDescription> modelDescriptions) {
    LOG.debug("Converting simulators...");
    List<OspSimulator> ospSimulators = new ArrayList<>();
    for (Simulators.Simulator simulator : simulators.getSimulator()) {
      ospSimulators.add(convertSimulator(ospSystemStructure, modelDescriptions, simulator));
    }
    LOG.debug("Done converting simulators");
    return ospSimulators;
  }
  
  private static OspSimulator convertSimulator(OspSystemStructure ospSystemStructure, Map<Simulators.Simulator, ModelDescription> modelDescriptions, Simulators.Simulator simulator) {
    LOG.debug("Converting simulator '" + simulator.getName() + "'...");
  
    OspSimulator ospSimulator = new OspSimulator(simulator.getName(), simulator.getSource());
    ModelDescription modelDescription = modelDescriptions.get(simulator);
    
    addPlugsToSimulator(simulator, ospSimulator, modelDescription);
    addSocketsToSimulator(simulator, ospSimulator, modelDescription);
    addBondsToSimulator(simulator, ospSimulator, modelDescription);
    addVariablesInVariableConnectionsToSimulator(simulator, ospSimulator, ospSystemStructure);
  
    LOG.debug("Done converting simulator '" + simulator.getName() + "'");
  
    return ospSimulator;
  }
  
  private static void addPlugsToSimulator(Simulators.Simulator simulator, OspSimulator ospSimulator, ModelDescription modelDescription) {
    modelDescription.getOspPlugs().values().forEach(ospPlug -> {
      LOG.debug("Adding plug '" + ospPlug.getName() + "' to simulator '" + simulator.getName() + "'");
      ospSimulator.addPlug(ospPlug);
      LOG.debug("Done adding plug '" + ospPlug.getName() + "' to simulator '" + simulator.getName() + "'");
    });
  }
  
  private static void addSocketsToSimulator(Simulators.Simulator simulator, OspSimulator ospSimulator, ModelDescription modelDescription) {
    modelDescription.getOspSockets().values().forEach(ospSocket -> {
      LOG.debug("Adding socket '" + ospSocket.getName() + "' to simulator '" + simulator.getName() + "'");
      ospSimulator.addSocket(ospSocket);
      LOG.debug("Done adding socket '" + ospSocket.getName() + "' to simulator '" + simulator.getName() + "'");
    });
  }
  
  private static void addBondsToSimulator(Simulators.Simulator simulator, OspSimulator ospSimulator, ModelDescription modelDescription) {
    modelDescription.getOspBonds().values().forEach(ospBond -> {
      LOG.debug("Adding bond '" + ospBond.getName() + "' to simulator '" + simulator.getName() + "'");
      ospSimulator.addBond(ospBond);
      LOG.debug("Done adding bond '" + ospBond.getName() + "' to simulator '" + simulator.getName() + "'");
    });
  }
  
  private static void addVariablesInVariableConnectionsToSimulator(Simulators.Simulator simulator, OspSimulator ospSimulator, OspSystemStructure ospSystemStructure) {
    LOG.debug("Adding variables in VariableConnections to simulator '" + simulator.getName() + "'...");
    ospSystemStructure.getVariableConnections().getScalarConnection().forEach(scalarConnection -> {
      ConnectionEndpoint source = scalarConnection.getSource();
      ConnectionEndpoint target = scalarConnection.getTarget();
      if (source.getSimulator().equals(simulator.getName()) && !ospSimulator.getVariables().containsKey(source.getEndpoint())) {
        LOG.debug("Adding variable '" + source.getEndpoint() + "' to simulator '" + simulator.getName() + "'");
        ospSimulator.addVariable(new OspVariable(source.getEndpoint()));
        LOG.debug("Done adding variable '" + source.getEndpoint() + "' to simulator '" + simulator.getName() + "'");
      } else if (target.getSimulator().equals(simulator.getName()) && !ospSimulator.getVariables().containsKey(target.getEndpoint())) {
        LOG.debug("Adding variable '" + target.getEndpoint() + "' to simulator '" + simulator.getName() + "'");
        ospSimulator.addVariable(new OspVariable(target.getEndpoint()));
        LOG.debug("Done adding variable '" + target.getEndpoint() + "' to simulator '" + simulator.getName() + "'");
      }
    });
    LOG.debug("Done adding variables in VariableConnections to simulator '" + simulator.getName() + "'...");
  }
}
