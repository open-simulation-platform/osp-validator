package com.opensimulationplatform.cseconfig.json.model;

import java.util.List;

public class OspSystemStructure {
  private List<Simulator> simulators;
  private List<BondConnection> bondConnections;
  private List<PlugSocketConnection> plugSocketConnections;
  private List<VariableConnection> variableConnections;
  
  public void setSimulators(List<Simulator> simulators) {
    this.simulators = simulators;
  }
  
  public void setBondConnections(List<BondConnection> bondConnections) {
    this.bondConnections = bondConnections;
  }
  
  public void setPlugSocketConnections(List<PlugSocketConnection> plugSocketConnections) {
    this.plugSocketConnections = plugSocketConnections;
  }
  
  public void setVariableConnections(List<VariableConnection> variableConnections) {
    this.variableConnections = variableConnections;
  }
  
  public List<Simulator> getSimulators() {
    return simulators;
  }
  
  public List<BondConnection> getBondConnections() {
    return bondConnections;
  }
  
  public List<PlugSocketConnection> getPlugSocketConnections() {
    return plugSocketConnections;
  }
  
  public List<VariableConnection> getVariableConnections() {
    return variableConnections;
  }
}
