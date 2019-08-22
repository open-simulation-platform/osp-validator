package com.opensimulationplatform.validator.model.ospmodeldescription;

import java.util.List;

public class OspModelDescription {
  private String name;
  private List<Plug> plugs;
  private List<Socket> sockets;
  private List<Bond> bonds;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
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
  
  public List<Bond> getBonds() {
    return bonds;
  }
  
  public void setBonds(List<Bond> bonds) {
    this.bonds = bonds;
  }
}
