package com.opensimulationplatform.cseconfig.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.core.model.systemstructure.OspVariableConnection;
import com.opensimulationplatform.cseconfig.xml.model.ConnectionEndpoint;
import com.opensimulationplatform.cseconfig.xml.model.VariableConnections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableConnectionsConverter {
  public static List<OspVariableConnection> convert(VariableConnections variableConnections, List<OspSimulator> ospSimulators) {
    List<OspVariableConnection> ospVariableConnections = new ArrayList<>();
    
    Map<String, OspSimulator> ospSimulatorMap = new HashMap<>();
    ospSimulators.forEach(s -> ospSimulatorMap.put(s.getName(), s));
    
    variableConnections.getScalarConnection().forEach(connection -> {
      ConnectionEndpoint source = connection.getSource();
      OspSimulator sourceOspSimulator = ospSimulatorMap.get(source.getSimulator());
      OspVariable sourceOspVariable = sourceOspSimulator.getVariables().get(source.getEndpoint());
      
      ConnectionEndpoint target = connection.getTarget();
      OspSimulator targetOspSimulator = ospSimulatorMap.get(target.getSimulator());
      OspVariable targetOspVariable = targetOspSimulator.getVariables().get(target.getEndpoint());
      
      ospVariableConnections.add(new OspVariableConnection(sourceOspSimulator, sourceOspVariable, targetOspSimulator, targetOspVariable));
    });
    
    return ospVariableConnections;
  }
}
