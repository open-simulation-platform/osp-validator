package com.opensimulationplatform.json.model.modeldefinition;

import java.util.List;

public class JsonModelDefinition {
  private String name;
  private List<JsonBond> bonds;
  private List<JsonPlug> plugs;
  private List<JsonSocket> sockets;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<JsonBond> getBonds() {
    return bonds;
  }
  
  public void setBonds(List<JsonBond> bonds) {
    this.bonds = bonds;
  }
  
  public List<JsonPlug> getPlugs() {
    return plugs;
  }
  
  public void setPlugs(List<JsonPlug> plugs) {
    this.plugs = plugs;
  }
  
  public List<JsonSocket> getSockets() {
    return sockets;
  }
  
  public void setSockets(List<JsonSocket> sockets) {
    this.sockets = sockets;
  }
}
