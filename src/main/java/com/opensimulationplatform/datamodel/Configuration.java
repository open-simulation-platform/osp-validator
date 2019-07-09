package com.opensimulationplatform.datamodel;

import java.util.List;
import java.util.Map;

public class Configuration {
  private Map<String, Simulator> simulators;
  private List<BondConnection> bondConnections;
  private List<PlugSocketConnection> plugSocketConnections;
  private List<VariableConnection> variableConnections;
  
  public Configuration(Map<String, Simulator> simulators, List<BondConnection> bondConnections, List<PlugSocketConnection> plugSocketConnections, List<VariableConnection> variableConnections) {
    this.simulators = simulators;
    this.bondConnections = bondConnections;
    this.plugSocketConnections = plugSocketConnections;
    this.variableConnections = variableConnections;
  }
  
  public Map<String, Simulator> getSimulators() {
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
