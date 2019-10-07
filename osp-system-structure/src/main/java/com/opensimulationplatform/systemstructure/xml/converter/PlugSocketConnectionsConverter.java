package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.systemstructure.OspPlugSocketConnection;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.systemstructure.xml.model.ConnectionEndpoint;
import com.opensimulationplatform.systemstructure.xml.model.PlugSocketConnections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlugSocketConnectionsConverter {
  public static List<OspPlugSocketConnection> convert(PlugSocketConnections plugSocketConnections, List<OspSimulator> ospSimulators) {
    List<OspPlugSocketConnection> ospPlugSocketConnections = new ArrayList<>();
    
    Map<String, OspSimulator> ospSimulatorMap = new HashMap<>();
    ospSimulators.forEach(s -> ospSimulatorMap.put(s.getName(), s));
    
    plugSocketConnections.getPlugSocketConnection().forEach(plugSocketConnection -> {
      ConnectionEndpoint source = plugSocketConnection.getSource();
      ConnectionEndpoint target = plugSocketConnection.getTarget();
      
      OspSimulator sourceOspSimulator = ospSimulatorMap.get(source.getSimulator());
      OspPlug ospPlug = sourceOspSimulator.getPlugs().get(source.getEndpoint());
      OspSimulator targetOspSimulator = ospSimulatorMap.get(target.getSimulator());
      OspSocket ospSocket = targetOspSimulator.getSockets().get(target.getEndpoint());
      
      ospPlugSocketConnections.add(new OspPlugSocketConnection(sourceOspSimulator, ospPlug, targetOspSimulator, ospSocket));
    });
    
    return ospPlugSocketConnections;
  }
}
