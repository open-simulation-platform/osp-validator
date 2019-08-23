package com.opensimulationplatform.cseconfig.validator;

import com.opensimulationplatform.core.validator.model.configuration.BondConnection;
import com.opensimulationplatform.core.validator.model.configuration.Configuration;
import com.opensimulationplatform.core.validator.model.configuration.PlugSocketConnection;
import com.opensimulationplatform.core.validator.model.configuration.VariableConnection;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.*;
import com.opensimulationplatform.cseconfig.jsonmodel.JsonBondConnection;
import com.opensimulationplatform.cseconfig.jsonmodel.JsonCseConfiguration;
import com.opensimulationplatform.cseconfig.jsonmodel.JsonPlugSocketConnection;
import com.opensimulationplatform.cseconfig.jsonmodel.JsonVariableConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ConfigurationFactory {
  
  static Configuration create(JsonCseConfiguration jsonCseConfiguration) {
    Map<String, Simulator> simulators = SimulatorFactory.create(jsonCseConfiguration);
    List<VariableConnection> variableConnections = createVariableConnections(jsonCseConfiguration, simulators);
    List<PlugSocketConnection> plugSocketConnections = createPlugSocketConnections(jsonCseConfiguration, simulators);
    List<BondConnection> bondConnections = createBondConnections(jsonCseConfiguration, simulators);
    
    return new Configuration(simulators, bondConnections, plugSocketConnections, variableConnections);
  }
  
  private static List<BondConnection> createBondConnections(JsonCseConfiguration jsonCseConfiguration, Map<String, Simulator> simulators) {
    List<BondConnection> bondConnections = new ArrayList<>();
    for (JsonBondConnection jsonBondConnection : jsonCseConfiguration.getBondConnections()) {
      Simulator simulatorA = simulators.get(jsonBondConnection.getSimulatorA());
      Bond bondA = simulatorA.getBonds().get(jsonBondConnection.getBondA());
      Simulator simulatorB = simulators.get(jsonBondConnection.getSimulatorB());
      Bond bondB = simulatorB.getBonds().get(jsonBondConnection.getBondB());
      BondConnection bondConnection = new BondConnection(simulatorA, bondA, simulatorB, bondB);
      bondConnections.add(bondConnection);
    }
    return bondConnections;
  }
  
  private static List<PlugSocketConnection> createPlugSocketConnections(JsonCseConfiguration jsonCseConfiguration, Map<String, Simulator> simulators) {
    List<PlugSocketConnection> plugSocketConnections = new ArrayList<>();
    for (JsonPlugSocketConnection jsonPlugSocketConnection : jsonCseConfiguration.getPlugSocketConnections()) {
      Simulator sourceSimulator = simulators.get(jsonPlugSocketConnection.getSourceSimulator());
      Plug plug = sourceSimulator.getPlugs().get(jsonPlugSocketConnection.getPlug());
      Simulator targetSimulator = simulators.get(jsonPlugSocketConnection.getTargetSimulator());
      Socket socket = targetSimulator.getSockets().get(jsonPlugSocketConnection.getSocket());
      PlugSocketConnection plugSocketConnection = new PlugSocketConnection(sourceSimulator, plug, targetSimulator, socket);
      plugSocketConnections.add(plugSocketConnection);
    }
    return plugSocketConnections;
  }
  
  private static List<VariableConnection> createVariableConnections(JsonCseConfiguration jsonCseConfiguration, Map<String, Simulator> simulators) {
    List<VariableConnection> variableConnections = new ArrayList<>();
    for (JsonVariableConnection jsonVariableConnection : jsonCseConfiguration.getVariableConnections()) {
      Variable sourceVariable = new Variable(jsonVariableConnection.getSourceVariable());
      Simulator sourceSimulator = simulators.get(jsonVariableConnection.getSourceSimulator());
      if (!sourceSimulator.getVariables().containsKey(sourceVariable.getName())) {
        sourceSimulator.addVariable(sourceVariable);
      }
      
      Variable targetVariable = new Variable(jsonVariableConnection.getTargetVariable());
      Simulator targetSimulator = simulators.get(jsonVariableConnection.getTargetSimulator());
      if (!targetSimulator.getVariables().containsKey(targetVariable.getName())) {
        targetSimulator.addVariable(targetVariable);
      }
      
      VariableConnection variableConnection = new VariableConnection(sourceSimulator, sourceVariable, targetSimulator, targetVariable);
      variableConnections.add(variableConnection);
    }
    return variableConnections;
  }
  
}
