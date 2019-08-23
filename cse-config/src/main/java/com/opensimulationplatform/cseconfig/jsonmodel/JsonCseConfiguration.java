package com.opensimulationplatform.cseconfig.jsonmodel;

import java.util.List;

public class JsonCseConfiguration {
  private List<JsonSimulator> simulators;
  private List<JsonBondConnection> bondConnections;
  private List<JsonPlugSocketConnection> plugSocketConnections;
  private List<JsonVariableConnection> variableConnections;
  
  public void setSimulators(List<JsonSimulator> simulators) {
    this.simulators = simulators;
  }
  
  public void setBondConnections(List<JsonBondConnection> bondConnections) {
    this.bondConnections = bondConnections;
  }
  
  public void setPlugSocketConnections(List<JsonPlugSocketConnection> plugSocketConnections) {
    this.plugSocketConnections = plugSocketConnections;
  }
  
  public void setVariableConnections(List<JsonVariableConnection> variableConnections) {
    this.variableConnections = variableConnections;
  }
  
  public List<JsonSimulator> getSimulators() {
    return simulators;
  }
  
  public List<JsonBondConnection> getBondConnections() {
    return bondConnections;
  }
  
  public List<JsonPlugSocketConnection> getPlugSocketConnections() {
    return plugSocketConnections;
  }
  
  public List<JsonVariableConnection> getVariableConnections() {
    return variableConnections;
  }
}
