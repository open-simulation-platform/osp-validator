package com.opensimulationplatform.msmivalidator.model.modeldefinition;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class Simulator  {
  
  private final String name;
  private final String source;
  private final String modelDefinition;
  private final Map<String, Variable> variables = new HashMap<>();
  private final Map<String, Bond> bonds = new HashMap<>();
  private final Map<String, Plug> plugs = new HashMap<>();
  private final Map<String, Socket> sockets = new HashMap<>();
  
  public Simulator(String name, String source, String modelDefinition) {
    this.name = name;
    this.source = source;
    this.modelDefinition = modelDefinition;
  }
  
  public void addVariable(Variable variable) {
    if (nonNull(variable)) {
      Variable old = variables.put(variable.getName(), variable);
      if (nonNull(old) && old != variable) {
        throw new RuntimeException("Can not add two variables with same name");
      } else if (variable.getSimulator() != this) {
        variable.setSimulator(this);
      }
    }
  }
  
  public void addPlug(Plug plug) {
    if (nonNull(plug)) {
      Plug old = plugs.put(plug.getName(), plug);
      if (nonNull(old) && old != plug) {
        throw new RuntimeException("Can not add two plugs with same name");
      } else if (plug.getSimulator() != this) {
        plug.setSimulator(this);
      }
    }
  }
  
  public void addSocket(Socket socket) {
    if (nonNull(socket)) {
      Socket old = sockets.put(socket.getName(), socket);
      if (nonNull(old) && old != socket) {
        throw new RuntimeException("Can not add two sockets with same name");
      } else if (socket.getSimulator() != this) {
        socket.setSimulator(this);
      }
    }
  }
  
  public void addBond(Bond bond) {
    bonds.put(bond.getName(), bond);
    if (bond.getSimulator() != this) {
      bond.setSimulator(this);
    }
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
  
  public String getName() {
    return name;
  }
}
