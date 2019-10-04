package com.opensimulationplatform.cseconfig.json.converter;

import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.systemstructure.OspPlugSocketConnection;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.cseconfig.json.model.PlugSocketConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlugSocketConnectionConverter {
  public static OspPlugSocketConnection convert(PlugSocketConnection plugSocketConnection, List<OspSimulator> ospSimulators) {
    Map<String, OspSimulator> ospSimulatorMap = new HashMap<>();
    ospSimulators.forEach(s -> ospSimulatorMap.put(s.getName(), s));
    
    OspSimulator sourceOspSimulator = ospSimulatorMap.get(plugSocketConnection.getSourceSimulator());
    OspPlug ospPlug = sourceOspSimulator.getPlugs().get(plugSocketConnection.getPlug());
    OspSimulator targetOspSimulator = ospSimulatorMap.get(plugSocketConnection.getTargetSimulator());
    OspSocket ospSocket = targetOspSimulator.getSockets().get(plugSocketConnection.getSocket());
    
    return new OspPlugSocketConnection(sourceOspSimulator, ospPlug, targetOspSimulator, ospSocket);
  }
}
