package com.opensimulationplatform.datamodel;

import java.util.Map;

public class Simulator extends Entity {
  private String name;
  private String source;
  private String modelDefinition;
  private Map<String, Bond> bonds;
  private Map<String, Plug> plugs;
  private Map<String, Socket> sockets;
  
  public Simulator(String name, String source, String modelDefinition, Map<String, Bond> bonds, Map<String, Plug> plugs, Map<String, Socket> sockets) {
    super("simulator_" + name);
    this.name = name;
    this.source = source;
    this.modelDefinition = modelDefinition;
    
    bonds.forEach((s, bond) -> bond.setParent(this));
    this.bonds = bonds;
    
    plugs.forEach((s, plug) -> plug.setParent(this));
    this.plugs = plugs;
    
    sockets.forEach((s, socket) -> socket.setParent(this));
    this.sockets = sockets;
  }
  
  public String getName() {
    return name;
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
}
