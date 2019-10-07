package com.opensimulationplatform.core.model.systemstructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SystemStructure {
  private List<OspSimulator> ospSimulators = new ArrayList<>();
  private List<OspBondConnection> ospBondConnections = new ArrayList<>();
  private List<OspPlugSocketConnection> ospPlugSocketConnections = new ArrayList<>();
  private List<OspVariableConnection> ospVariableConnections = new ArrayList<>();
  
  public List<OspSimulator> getOspSimulators() {
    return ospSimulators;
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
    ospSimulators.add(ospSimulator);
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
  
  public OspSimulator getSimulatorByName(String simulatorName) {
    Optional<OspSimulator> ospSimulator = ospSimulators.stream().filter(s -> s.getName().equals(simulatorName)).findFirst();
    return ospSimulator.orElse(null);
  }
}
