package com.opensimulationplatform.validator.model.configuration;

import com.opensimulationplatform.json.model.configuration.JsonBondConnection;
import com.opensimulationplatform.json.model.configuration.JsonConfiguration;
import com.opensimulationplatform.json.model.configuration.JsonPlugSocketConnection;
import com.opensimulationplatform.json.model.configuration.JsonVariableConnection;
import com.opensimulationplatform.validator.model.ospmodeldescription.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigurationFactory {
  
  public static Configuration create(JsonConfiguration jsonConfiguration) {
    Map<String, Simulator> simulators = SimulatorFactory.create(jsonConfiguration);
    List<VariableConnection> variableConnections = createVariableConnections(jsonConfiguration, simulators);
    List<PlugSocketConnection> plugSocketConnections = createPlugSocketConnections(jsonConfiguration, simulators);
    List<BondConnection> bondConnections = createBondConnections(jsonConfiguration, simulators);
    
    return new Configuration(simulators, bondConnections, plugSocketConnections, variableConnections);
  }
  
  private static List<BondConnection> createBondConnections(JsonConfiguration jsonConfiguration, Map<String, Simulator> simulators) {
    List<BondConnection> bondConnections = new ArrayList<>();
    for (JsonBondConnection jsonBondConnection : jsonConfiguration.getBondConnections()) {
      Simulator simulatorA = simulators.get(jsonBondConnection.getSimulatorA());
      Bond bondA = simulatorA.getBonds().get(jsonBondConnection.getBondA());
      Simulator simulatorB = simulators.get(jsonBondConnection.getSimulatorB());
      Bond bondB = simulatorB.getBonds().get(jsonBondConnection.getBondB());
      BondConnection bondConnection = new BondConnection(simulatorA, bondA, simulatorB, bondB);
      bondConnections.add(bondConnection);
    }
    return bondConnections;
  }
  
  private static List<PlugSocketConnection> createPlugSocketConnections(JsonConfiguration jsonConfiguration, Map<String, Simulator> simulators) {
    List<PlugSocketConnection> plugSocketConnections = new ArrayList<>();
    for (JsonPlugSocketConnection jsonPlugSocketConnection : jsonConfiguration.getPlugSocketConnections()) {
      Simulator sourceSimulator = simulators.get(jsonPlugSocketConnection.getSourceSimulator());
      Plug plug = sourceSimulator.getPlugs().get(jsonPlugSocketConnection.getPlug());
      Simulator targetSimulator = simulators.get(jsonPlugSocketConnection.getTargetSimulator());
      Socket socket = targetSimulator.getSockets().get(jsonPlugSocketConnection.getSocket());
      PlugSocketConnection plugSocketConnection = new PlugSocketConnection(sourceSimulator, plug, targetSimulator, socket);
      plugSocketConnections.add(plugSocketConnection);
    }
    return plugSocketConnections;
  }
  
  private static List<VariableConnection> createVariableConnections(JsonConfiguration jsonConfiguration, Map<String, Simulator> simulators) {
    List<VariableConnection> variableConnections = new ArrayList<>();
    for (JsonVariableConnection jsonVariableConnection : jsonConfiguration.getVariableConnections()) {
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
