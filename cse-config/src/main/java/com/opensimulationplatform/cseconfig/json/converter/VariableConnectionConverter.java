package com.opensimulationplatform.cseconfig.json.converter;

import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.core.model.systemstructure.OspVariableConnection;
import com.opensimulationplatform.cseconfig.json.model.VariableConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VariableConnectionConverter {
  
  public static OspVariableConnection convert(VariableConnection variableConnection, List<OspSimulator> ospSimulators) {
    Map<String, OspSimulator> ospSimulatorMap = new HashMap<>();
    ospSimulators.forEach(s -> ospSimulatorMap.put(s.getName(), s));
    
    OspSimulator sourceSimulator = ospSimulatorMap.get(variableConnection.getSourceSimulator());
    OspVariable sourceVariable = sourceSimulator.getVariables().get(variableConnection.getSourceVariable());
    OspSimulator targetSimulator = ospSimulatorMap.get(variableConnection.getTargetSimulator());
    OspVariable targetVariable = targetSimulator.getVariables().get(variableConnection.getTargetVariable());
  
    return new OspVariableConnection(sourceSimulator, sourceVariable, targetSimulator, targetVariable);
  }
}
