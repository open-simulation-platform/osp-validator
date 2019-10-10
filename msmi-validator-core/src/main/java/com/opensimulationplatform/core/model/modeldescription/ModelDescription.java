package com.opensimulationplatform.core.model.modeldescription;

import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ModelDescription {
  private String name;
  private Map<String, OspPlug> ospPlugs = new TreeMap<>();
  private Map<String, OspSocket> ospSockets = new TreeMap<>();
  private Map<String, OspBond> ospBonds = new TreeMap<>();
  private FmiModelDescription fmiModelDescription;
  
  public ModelDescription(FmiModelDescription fmiModelDescription) {
    this.fmiModelDescription = fmiModelDescription;
  }
  
  public ModelDescription() {
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Map<String, OspPlug> getOspPlugs() {
    return ospPlugs;
  }
  
  public void setOspPlugs(List<OspPlug> ospPlugs) {
    ospPlugs.forEach(this::addPlug);
  }
  
  public Map<String, OspSocket> getOspSockets() {
    return ospSockets;
  }
  
  public void setOspSockets(List<OspSocket> ospSockets) {
    ospSockets.forEach(this::addSocket);
  }
  
  public Map<String, OspBond> getOspBonds() {
    return ospBonds;
  }
  
  public void setOspBonds(List<OspBond> ospBonds) {
    ospBonds.forEach(this::addBond);
  }
  
  public FmiModelDescription getFmiModelDescription() {
    return fmiModelDescription;
  }
  
  public void setFmiModelDescription(FmiModelDescription fmiModelDescription) {
    this.fmiModelDescription = fmiModelDescription;
  }
  
  public void addPlug(OspPlug ospPlug) {
    if (ospPlugs.containsKey(ospPlug.getName())) {
      throw new RuntimeException("Can not add two plugs with the same name");
    } else {
      ospPlugs.put(ospPlug.getName(), ospPlug);
    }
  }
  
  public void addSocket(OspSocket ospSocket) {
    if (ospSockets.containsKey(ospSocket.getName())) {
      throw new RuntimeException("Can not add two sockets with the same name");
    } else {
      ospSockets.put(ospSocket.getName(), ospSocket);
    }
  }
  
  public void addBond(OspBond ospBond) {
    if (ospBonds.containsKey(ospBond.getName())) {
      throw new RuntimeException("Can not add two bonds with the same name");
    } else {
      ospBonds.put(ospBond.getName(), ospBond);
    }
  }
}
