package com.opensimulationplatform.json.model.modeldefinition;

import java.util.List;

public class JsonBond {
  private String name;
  private List<String> plugs;
  private List<String> sockets;
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<String> getPlugs() {
    return plugs;
  }
  
  public void setPlugs(List<String> plugs) {
    this.plugs = plugs;
  }
  
  public List<String> getSockets() {
    return sockets;
  }
  
  public void setSockets(List<String> sockets) {
    this.sockets = sockets;
  }
}
