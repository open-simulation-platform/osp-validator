package com.opensimulationplatform.core.model.modeldescription;

import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;

import java.util.ArrayList;
import java.util.List;

public class OspModelDescription {
  private String name;
  private List<OspPlug> ospPlugs = new ArrayList<>();
  private List<OspSocket> ospSockets = new ArrayList<>();
  private List<OspBond> ospBonds = new ArrayList<>();
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
  
  public List<OspPlug> getOspPlugs() {
    return ospPlugs;
  }
  
  public void setOspPlugs(List<OspPlug> ospPlugs) {
    this.ospPlugs = ospPlugs;
  }
  
  public List<OspSocket> getOspSockets() {
    return ospSockets;
  }
  
  public void setOspSockets(List<OspSocket> ospSockets) {
    this.ospSockets = ospSockets;
  }
  
  public List<OspBond> getOspBonds() {
    return ospBonds;
  }
  
  public void setOspBonds(List<OspBond> ospBonds) {
    this.ospBonds = ospBonds;
  }
  
  public FmiModelDescription getFmiModelDescription() {
    return fmiModelDescription;
  }
  
  public void setFmiModelDescription(FmiModelDescription fmiModelDescription) {
    this.fmiModelDescription = fmiModelDescription;
  }
}
