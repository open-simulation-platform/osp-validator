package com.opensimulationplatform.validator.model.modeldefinition;

import com.opensimulationplatform.json.model.modeldefinition.JsonBond;
import com.opensimulationplatform.json.model.modeldefinition.JsonModelDefinition;
import com.opensimulationplatform.json.model.modeldefinition.JsonPlug;
import com.opensimulationplatform.json.model.modeldefinition.JsonSocket;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ModelDefinitionFactory {
  public static ModelDefinition create(JsonModelDefinition jsonModelDefinition) {
    ModelDefinition modelDefinition = new ModelDefinition();
    
    modelDefinition.setName(jsonModelDefinition.getName());
    
    Map<String, Plug> plugs = createPlugs(jsonModelDefinition);
    Map<String, Socket> sockets = createSockets(jsonModelDefinition);
    List<Bond> bonds = createBonds(jsonModelDefinition, plugs, sockets);
    
    Simulator s = new Simulator(jsonModelDefinition.getName(), null, null);
    plugs.forEach((name, plug) -> s.addPlug(plug));
    sockets.forEach((name, socket) -> s.addSocket(socket));
    bonds.forEach(s::addBond);
    
    modelDefinition.setPlugs(new ArrayList<>(s.getPlugs().values()));
    modelDefinition.setSockets(new ArrayList<>(s.getSockets().values()));
    modelDefinition.setBonds(new ArrayList<>(s.getBonds().values()));
    
    return modelDefinition;
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
    Map<String, Socket> sockets = new LinkedHashMap<>();
    for (JsonSocket jsonSocket : jsonModelDefinition.getSockets()) {
      Socket s = new Socket(jsonSocket.getType(), jsonSocket.getName());
      List<Variable> variables = createVariables(jsonSocket.getVariables());
      variables.forEach(s::addVariable);
      sockets.put(s.getName(), s);
    }
    return sockets;
  }
  
  private static Map<String, Plug> createPlugs(JsonModelDefinition jsonModelDefinition) {
    Map<String, Plug> plugs = new LinkedHashMap<>();
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
