package com.opensimulationplatform.modeldescription.json.model;

import java.util.List;

public class OspModelDescription {
  private String name;
  private List<Bond> bonds;
  private List<Plug> plugs;
  private List<Socket> sockets;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<Bond> getBonds() {
    return bonds;
  }
  
  public void setBonds(List<Bond> bonds) {
    this.bonds = bonds;
  }
  
  public List<Plug> getPlugs() {
    return plugs;
  }
  
  public void setPlugs(List<Plug> plugs) {
    this.plugs = plugs;
  }
  
  public List<Socket> getSockets() {
    return sockets;
  }
  
  public void setSockets(List<Socket> sockets) {
    this.sockets = sockets;
  }
}
