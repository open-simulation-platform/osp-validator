package com.opensimulationplatform.core.model.configuration;

import com.opensimulationplatform.core.model.modeldescription.OspVariable;

public class OspVariableConnection {
  private final OspSimulator sourceOspSimulator;
  private final OspVariable sourceOspVariable;
  private final OspSimulator targetOspSimulator;
  private final OspVariable targetOspVariable;
  
  public OspVariableConnection(OspSimulator sourceOspSimulator, OspVariable sourceOspVariable, OspSimulator targetOspSimulator, OspVariable targetOspVariable) {
    this.sourceOspSimulator = sourceOspSimulator;
    this.sourceOspVariable = sourceOspVariable;
    this.targetOspSimulator = targetOspSimulator;
    this.targetOspVariable = targetOspVariable;
  }
  
  public OspSimulator getSourceOspSimulator() {
    return sourceOspSimulator;
  }
  
  public OspVariable getSourceOspVariable() {
    return sourceOspVariable;
  }
  
  public OspSimulator getTargetOspSimulator() {
    return targetOspSimulator;
  }
  
  public OspVariable getTargetOspVariable() {
    return targetOspVariable;
  }
}
