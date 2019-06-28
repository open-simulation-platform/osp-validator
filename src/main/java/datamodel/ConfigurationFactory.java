package datamodel;

import jsonmodel.configuration.*;
import jsonmodel.modeldefinition.JsonBond;
import jsonmodel.modeldefinition.JsonModelDefinition;
import jsonmodel.modeldefinition.JsonPlug;
import jsonmodel.modeldefinition.JsonSocket;
import jsonmodel.parsing.ModelDefinitionJsonFileParer;

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
      Simulator sourceSimulator = simulators.get(jsonVariableConnection.getSourceSimulator());
      Variable sourceVariable = new Variable(jsonVariableConnection.getSourceVariable());
      Simulator targetSimulator = simulators.get(jsonVariableConnection.getTargetSimulator());
      Variable targetVariable = new Variable(jsonVariableConnection.getTargetVariable());
      VariableConnection variableConnection = new VariableConnection(sourceSimulator, sourceVariable, targetSimulator, targetVariable);
      variableConnections.add(variableConnection);
    }
    return variableConnections;
  }
  
  private static Map<String, Simulator> createSimulators(JsonConfiguration jsonConfiguration) {
    Map<String, Simulator> simulators = new HashMap<>();
    for (JsonSimulator jsonSimulator : jsonConfiguration.getSimulators()) {
      JsonModelDefinition jsonModelDefinition = ModelDefinitionJsonFileParer.parse(new File(jsonSimulator.getModelDefinition()));
      Map<String, Plug> plugs = createPlugs(jsonModelDefinition);
      Map<String, Socket> sockets = createSockets(jsonModelDefinition);
      Map<String, Bond> bonds = createBonds(jsonModelDefinition, plugs, sockets);
      Simulator s = new Simulator(jsonSimulator.getName(), jsonSimulator.getSource(), jsonSimulator.getModelDefinition(), bonds, plugs, sockets);
      simulators.put(s.getName(), s);
    }
    return simulators;
  }
  
  private static Map<String, Bond> createBonds(JsonModelDefinition jsonModelDefinition, Map<String, Plug> plugs, Map<String, Socket> sockets) {
    Map<String, Bond> bonds = new HashMap<>();
    for (JsonBond jsonBond : jsonModelDefinition.getBonds()) {
      List<Plug> bondPlugs = new ArrayList<>();
      for (String plugNames : jsonBond.getPlugs()) {
        Plug plug = plugs.get(plugNames);
        bondPlugs.add(plug);
      }
      
      List<Socket> bondSockets = new ArrayList<>();
      for (String socketNames : jsonBond.getSockets()) {
        Socket socket = sockets.get(socketNames);
        bondSockets.add(socket);
      }
      
      Bond b = new Bond(jsonBond.getName(), bondPlugs, bondSockets);
      bonds.put(b.getName(), b);
    }
    return bonds;
  }
  
  private static Map<String, Socket> createSockets(JsonModelDefinition jsonModelDefinition) {
    Map<String, Socket> sockets = new HashMap<>();
    for (JsonSocket jsonSocket : jsonModelDefinition.getSockets()) {
      Map<String, Variable> variables = new HashMap<>();
      for (String variableName : jsonSocket.getVariables()) {
        Variable v = new Variable(variableName);
        variables.put(v.getName(), v);
      }
      Socket p = new Socket(jsonSocket.getType(), jsonSocket.getName(), variables);
      sockets.put(p.getName(), p);
    }
    return sockets;
  }
  
  private static Map<String, Plug> createPlugs(JsonModelDefinition jsonModelDefinition) {
    Map<String, Plug> plugs = new HashMap<>();
    for (JsonPlug jsonPlug : jsonModelDefinition.getPlugs()) {
      Map<String, Variable> variables = new HashMap<>();
      for (String variableName : jsonPlug.getVariables()) {
        Variable v = new Variable(variableName);
        variables.put(v.getName(), v);
      }
      Plug p = new Plug(jsonPlug.getType(), jsonPlug.getName(), variables);
      plugs.put(p.getName(), p);
    }
    return plugs;
  }
}
