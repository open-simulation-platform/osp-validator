package com.opensimulationplatform.cseconfig.json.model;

import java.util.List;

public class JsonCseConfiguration {
  private List<JsonCseSimulator> simulators;
  private List<JsonCseBondConnection> bondConnections;
  private List<JsonCsePlugSocketConnection> plugSocketConnections;
  private List<JsonCseVariableConnection> variableConnections;
  
  public void setSimulators(List<JsonCseSimulator> simulators) {
    this.simulators = simulators;
  }
  
  public void setBondConnections(List<JsonCseBondConnection> bondConnections) {
    this.bondConnections = bondConnections;
  }
  
  public void setPlugSocketConnections(List<JsonCsePlugSocketConnection> plugSocketConnections) {
    this.plugSocketConnections = plugSocketConnections;
  }
  
  public void setVariableConnections(List<JsonCseVariableConnection> variableConnections) {
    this.variableConnections = variableConnections;
  }
  
  public List<JsonCseSimulator> getSimulators() {
    return simulators;
  }
  
  public List<JsonCseBondConnection> getBondConnections() {
    return bondConnections;
  }
  
  public List<JsonCsePlugSocketConnection> getPlugSocketConnections() {
    return plugSocketConnections;
  }
  
  public List<JsonCseVariableConnection> getVariableConnections() {
    return variableConnections;
  }
}
