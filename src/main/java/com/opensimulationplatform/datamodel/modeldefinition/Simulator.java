package com.opensimulationplatform.datamodel.modeldefinition;

import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toMap;

public class Simulator extends Entity {
  private String source;
  private String modelDefinition;
  private Map<String, Variable> variables = new HashMap<>();
  private Map<String, Bond> bonds = new HashMap<>();
  private Map<String, Plug> plugs = new HashMap<>();
  private Map<String, Socket> sockets = new HashMap<>();
  
  public Simulator(String name, String source, String modelDefinition, Map<String, Bond> bonds, Map<String, Plug> plugs, Map<String, Socket> sockets) {
    super(name);
    this.source = source;
    this.modelDefinition = modelDefinition;
    
    addBonds(bonds);
    addPlugs(plugs);
    addSockets(sockets);
  }
  
  public Simulator(String name, String source, String modelDefinition) {
    this(name, source, modelDefinition, emptyMap(), emptyMap(), emptyMap());
  }
  
  private void addBonds(Map<String, Bond> bonds) {
    bonds.forEach((bondName, bond) -> {
      bond.setParent(this);
      
      Map<String, Plug> plugs = bond.getPlugs().stream().collect(toMap(Plug::getName, plug -> plug));
      addPlugs(plugs);
      
      Map<String, Socket> sockets = bond.getSockets().stream().collect(toMap(Socket::getName, socket -> socket));
      addSockets(sockets);
    });
    
    this.bonds.putAll(bonds);
  }
  
  private void addSockets(Map<String, Socket> sockets) {
    sockets.forEach((socketName, socket) -> {
      socket.setParent(this);
      socket.getVariables().forEach((variableName, variable) -> addVariable(variable));
    });
    
    this.sockets.putAll(sockets);
  }
  
  private void addPlugs(Map<String, Plug> plugs) {
    plugs.forEach((plugName, plug) -> {
      plug.setSimulator(this);
      plug.getVariables().forEach((variableName, variable) -> addVariable(variable));
    });
    
    this.plugs.putAll(plugs);
  }
  
  public String getSource() {
    return source;
  }
  
  public String getModelDefinition() {
    return modelDefinition;
  }
  
  public Map<String, Bond> getBonds() {
    return bonds;
  }
  
  public Map<String, Plug> getPlugs() {
    return plugs;
  }
  
  public Map<String, Socket> getSockets() {
    return sockets;
  }
  
  public Map<String, Variable> getVariables() {
    return variables;
  }
  
  public void addVariable(Variable variable) {
    variables.put(variable.getName(), variable);
    if (variable.getSimulator() != this) {
      variable.setSimulator(this);
    }
  }
  
  void addPlug(Plug plug) {
    plugs.put(plug.getName(), plug);
    if (plug.getSimulator() != this) {
      plug.setSimulator(this);
    }
  }
  
  void addSocket(Socket socket) {
    sockets.put(socket.getName(), socket);
    if (socket.getSimulator() != this) {
      socket.setSimulator(this);
    }
  }
  
  void addBond(Bond bond) {
    bonds.put(bond.getName(), bond);
    if (bond.getSimulator() != this) {
      bond.setSimulator(this);
    }
  }
}
