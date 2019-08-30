package com.opensimulationplatform.modeldescription.json.model;

import java.util.List;

public class JsonOspModelDescription {
  private String name;
  private List<JsonOspBond> bonds;
  private List<JsonOspPlug> plugs;
  private List<JsonOspSocket> sockets;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<JsonOspBond> getBonds() {
    return bonds;
  }
  
  public void setBonds(List<JsonOspBond> bonds) {
    this.bonds = bonds;
  }
  
  public List<JsonOspPlug> getPlugs() {
    return plugs;
  }
  
  public void setPlugs(List<JsonOspPlug> plugs) {
    this.plugs = plugs;
  }
  
  public List<JsonOspSocket> getSockets() {
    return sockets;
  }
  
  public void setSockets(List<JsonOspSocket> sockets) {
    this.sockets = sockets;
  }
}
