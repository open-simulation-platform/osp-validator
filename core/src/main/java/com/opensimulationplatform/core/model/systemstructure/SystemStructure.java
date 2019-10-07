package com.opensimulationplatform.core.model.systemstructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemStructure {
  private Map<String, OspSimulator> simulators = new HashMap<>();
  private List<OspBondConnection> ospBondConnections = new ArrayList<>();
  private List<OspPlugSocketConnection> ospPlugSocketConnections = new ArrayList<>();
  private List<OspVariableConnection> ospVariableConnections = new ArrayList<>();
  
  public Map<String, OspSimulator> getSimulators() {
    return simulators;
  }
  
  public List<OspBondConnection> getOspBondConnections() {
    return ospBondConnections;
  }
  
  public List<OspPlugSocketConnection> getOspPlugSocketConnections() {
    return ospPlugSocketConnections;
  }
  
  public List<OspVariableConnection> getOspVariableConnections() {
    return ospVariableConnections;
  }
  
  public void addSimulator(OspSimulator ospSimulator) {
    simulators.put(ospSimulator.getName(), ospSimulator);
  }
  
  public void addVariableConnection(OspVariableConnection ospVariableConnection) {
    ospVariableConnections.add(ospVariableConnection);
  }
  
  public void addPlugSocketConnection(OspPlugSocketConnection ospPlugSocketConnection) {
    ospPlugSocketConnections.add(ospPlugSocketConnection);
  }
  
  public void addBondConnection(OspBondConnection ospBondConnection) {
    ospBondConnections.add(ospBondConnection);
  }
}
