package com.opensimulationplatform.validator.model.ospmodeldescription;

import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;

import java.util.ArrayList;
import java.util.List;

public class OspModelDescription {
  private String name;
  private List<Plug> plugs = new ArrayList<>();
  private List<Socket> sockets = new ArrayList<>();
  private List<Bond> bonds = new ArrayList<>();
  private FmiModelDescription fmiModelDescription;
  
  public OspModelDescription(FmiModelDescription fmiModelDescription) {
    this.fmiModelDescription = fmiModelDescription;
  }
  
  public OspModelDescription() {
  }
  
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
  
  public FmiModelDescription getFmiModelDescription() {
    return fmiModelDescription;
  }
  
  void setFmiModelDescription(FmiModelDescription fmiModelDescription) {
    this.fmiModelDescription = fmiModelDescription;
  }
}
