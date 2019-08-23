package com.opensimulationplatform.core.validator.model.configuration;

import com.opensimulationplatform.core.validator.model.modeldescription.OspSimulator;

import java.util.List;
import java.util.Map;

public class OspConfiguration {
  private final Map<String, OspSimulator> simulators;
  private final List<OspBondConnection> ospBondConnections;
  private final List<OspPlugSocketConnection> ospPlugSocketConnections;
  private final List<OspVariableConnection> ospVariableConnections;
  
  public OspConfiguration(Map<String, OspSimulator> simulators, List<OspBondConnection> ospBondConnections, List<OspPlugSocketConnection> ospPlugSocketConnections, List<OspVariableConnection> ospVariableConnections) {
    this.simulators = simulators;
    this.ospBondConnections = ospBondConnections;
    this.ospPlugSocketConnections = ospPlugSocketConnections;
    this.ospVariableConnections = ospVariableConnections;
  }
  
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
}
