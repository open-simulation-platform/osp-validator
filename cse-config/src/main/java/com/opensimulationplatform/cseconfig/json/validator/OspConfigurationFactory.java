package com.opensimulationplatform.cseconfig.json.validator;

import com.opensimulationplatform.core.validator.model.configuration.OspBondConnection;
import com.opensimulationplatform.core.validator.model.configuration.OspConfiguration;
import com.opensimulationplatform.core.validator.model.configuration.OspPlugSocketConnection;
import com.opensimulationplatform.core.validator.model.configuration.OspVariableConnection;
import com.opensimulationplatform.core.validator.model.modeldescription.*;
import com.opensimulationplatform.cseconfig.json.model.JsonCseBondConnection;
import com.opensimulationplatform.cseconfig.json.model.JsonCseConfiguration;
import com.opensimulationplatform.cseconfig.json.model.JsonCsePlugSocketConnection;
import com.opensimulationplatform.cseconfig.json.model.JsonCseVariableConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class OspConfigurationFactory {
  
  static OspConfiguration create(JsonCseConfiguration jsonCseConfiguration) {
    Map<String, OspSimulator> simulators = OspSimulatorsFactory.create(jsonCseConfiguration);
    List<OspVariableConnection> ospVariableConnections = createVariableConnections(jsonCseConfiguration, simulators);
    List<OspPlugSocketConnection> ospPlugSocketConnections = createPlugSocketConnections(jsonCseConfiguration, simulators);
    List<OspBondConnection> ospBondConnections = createBondConnections(jsonCseConfiguration, simulators);
    
    return new OspConfiguration(simulators, ospBondConnections, ospPlugSocketConnections, ospVariableConnections);
  }
  
  private static List<OspBondConnection> createBondConnections(JsonCseConfiguration jsonCseConfiguration, Map<String, OspSimulator> simulators) {
    List<OspBondConnection> ospBondConnections = new ArrayList<>();
    for (JsonCseBondConnection jsonCseBondConnection : jsonCseConfiguration.getBondConnections()) {
      OspSimulator ospSimulatorA = simulators.get(jsonCseBondConnection.getSimulatorA());
      OspBond ospBondA = ospSimulatorA.getBonds().get(jsonCseBondConnection.getBondA());
      OspSimulator ospSimulatorB = simulators.get(jsonCseBondConnection.getSimulatorB());
      OspBond ospBondB = ospSimulatorB.getBonds().get(jsonCseBondConnection.getBondB());
      OspBondConnection ospBondConnection = new OspBondConnection(ospSimulatorA, ospBondA, ospSimulatorB, ospBondB);
      ospBondConnections.add(ospBondConnection);
    }
    return ospBondConnections;
  }
  
  private static List<OspPlugSocketConnection> createPlugSocketConnections(JsonCseConfiguration jsonCseConfiguration, Map<String, OspSimulator> simulators) {
    List<OspPlugSocketConnection> ospPlugSocketConnections = new ArrayList<>();
    for (JsonCsePlugSocketConnection jsonCsePlugSocketConnection : jsonCseConfiguration.getPlugSocketConnections()) {
      OspSimulator sourceOspSimulator = simulators.get(jsonCsePlugSocketConnection.getSourceSimulator());
      OspPlug ospPlug = sourceOspSimulator.getPlugs().get(jsonCsePlugSocketConnection.getPlug());
      OspSimulator targetOspSimulator = simulators.get(jsonCsePlugSocketConnection.getTargetSimulator());
      OspSocket ospSocket = targetOspSimulator.getSockets().get(jsonCsePlugSocketConnection.getSocket());
      OspPlugSocketConnection ospPlugSocketConnection = new OspPlugSocketConnection(sourceOspSimulator, ospPlug, targetOspSimulator, ospSocket);
      ospPlugSocketConnections.add(ospPlugSocketConnection);
    }
    return ospPlugSocketConnections;
  }
  
  private static List<OspVariableConnection> createVariableConnections(JsonCseConfiguration jsonCseConfiguration, Map<String, OspSimulator> simulators) {
    List<OspVariableConnection> ospVariableConnections = new ArrayList<>();
    for (JsonCseVariableConnection jsonCseVariableConnection : jsonCseConfiguration.getVariableConnections()) {
      OspVariable sourceOspVariable = new OspVariable(jsonCseVariableConnection.getSourceVariable());
      OspSimulator sourceOspSimulator = simulators.get(jsonCseVariableConnection.getSourceSimulator());
      if (!sourceOspSimulator.getVariables().containsKey(sourceOspVariable.getName())) {
        sourceOspSimulator.addVariable(sourceOspVariable);
      }
      
      OspVariable targetOspVariable = new OspVariable(jsonCseVariableConnection.getTargetVariable());
      OspSimulator targetOspSimulator = simulators.get(jsonCseVariableConnection.getTargetSimulator());
      if (!targetOspSimulator.getVariables().containsKey(targetOspVariable.getName())) {
        targetOspSimulator.addVariable(targetOspVariable);
      }
      
      OspVariableConnection ospVariableConnection = new OspVariableConnection(sourceOspSimulator, sourceOspVariable, targetOspSimulator, targetOspVariable);
      ospVariableConnections.add(ospVariableConnection);
    }
    return ospVariableConnections;
  }
  
}
