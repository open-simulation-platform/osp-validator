package com.opensimulationplatform.validator.model.configuration;

import com.opensimulationplatform.json.model.configuration.*;
import com.opensimulationplatform.json.model.modeldefinition.JsonBond;
import com.opensimulationplatform.json.model.modeldefinition.JsonModelDefinition;
import com.opensimulationplatform.json.model.modeldefinition.JsonPlug;
import com.opensimulationplatform.json.model.modeldefinition.JsonSocket;
import com.opensimulationplatform.json.model.parsing.ModelDefinitionJsonFileParser;
import com.opensimulationplatform.validator.model.modeldefinition.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigurationFactory {
  
  public static Configuration create(JsonConfiguration jsonConfiguration) {
    Map<String, Simulator> simulators = createSimulators(jsonConfiguration);
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
  
  private static Map<String, Simulator> createSimulators(JsonConfiguration jsonConfiguration) {
    Map<String, Simulator> simulators = new HashMap<>();
    for (JsonSimulator jsonSimulator : jsonConfiguration.getSimulators()) {
      JsonModelDefinition jsonModelDefinition = ModelDefinitionJsonFileParser.parse(new File(jsonSimulator.getModelDefinition()));
      Map<String, Plug> plugs = createPlugs(jsonModelDefinition);
      Map<String, Socket> sockets = createSockets(jsonModelDefinition);
      List<Bond> bonds = createBonds(jsonModelDefinition, plugs, sockets);
      
      Simulator s = new Simulator(jsonSimulator.getName(), jsonSimulator.getSource(), jsonSimulator.getModelDefinition());
      
      plugs.forEach((name, plug) -> s.addPlug(plug));
      sockets.forEach((name, socket) -> s.addSocket(socket));
      bonds.forEach(s::addBond);
      
      simulators.put(s.getName(), s);
    }
    return simulators;
  }
  
  private static List<Bond> createBonds(JsonModelDefinition jsonModelDefinition, Map<String, Plug> plugs, Map<String, Socket> sockets) {
    List<Bond> bonds = new ArrayList<>();
    for (JsonBond jsonBond : jsonModelDefinition.getBonds()) {
      Bond b = new Bond(jsonBond.getName());
      
      List<Plug> bondPlugs = new ArrayList<>();
      for (String plugNames : jsonBond.getPlugs()) {
        Plug plug = plugs.get(plugNames);
        bondPlugs.add(plug);
      }
      bondPlugs.forEach(b::addPlug);
      
      List<Socket> bondSockets = new ArrayList<>();
      for (String socketNames : jsonBond.getSockets()) {
        Socket socket = sockets.get(socketNames);
        bondSockets.add(socket);
      }
      bondSockets.forEach(b::addSocket);
      
      bonds.add(b);
    }
    
    return bonds;
  }
  
  private static Map<String, Socket> createSockets(JsonModelDefinition jsonModelDefinition) {
    Map<String, Socket> sockets = new HashMap<>();
    for (JsonSocket jsonSocket : jsonModelDefinition.getSockets()) {
      Socket s = new Socket(jsonSocket.getType(), jsonSocket.getName());
      List<Variable> variables = createVariables(jsonSocket.getVariables());
      variables.forEach(s::addVariable);
      sockets.put(s.getName(), s);
    }
    return sockets;
  }
  
  private static Map<String, Plug> createPlugs(JsonModelDefinition jsonModelDefinition) {
    Map<String, Plug> plugs = new HashMap<>();
    for (JsonPlug jsonPlug : jsonModelDefinition.getPlugs()) {
      Plug p = new Plug(jsonPlug.getType(), jsonPlug.getName());
      List<Variable> variables = createVariables(jsonPlug.getVariables());
      variables.forEach(p::addVariable);
      plugs.put(p.getName(), p);
    }
    return plugs;
  }
  
  private static List<Variable> createVariables(List<String> variableList) {
    List<Variable> variables = new ArrayList<>();
    for (String variableName : variableList) {
      Variable v = new Variable(variableName);
      variables.add(v);
    }
    return variables;
  }
}
