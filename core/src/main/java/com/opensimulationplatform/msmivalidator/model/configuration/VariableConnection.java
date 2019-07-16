package com.opensimulationplatform.msmivalidator.model.configuration;

import com.opensimulationplatform.msmivalidator.model.modeldefinition.Simulator;
import com.opensimulationplatform.msmivalidator.model.modeldefinition.Variable;

public class VariableConnection {
  private final Simulator sourceSimulator;
  private final Variable sourceVariable;
  private final Simulator targetSimulator;
  private final Variable targetVariable;
  
  public VariableConnection(Simulator sourceSimulator, Variable sourceVariable, Simulator targetSimulator, Variable targetVariable) {
    this.sourceSimulator = sourceSimulator;
    this.sourceVariable = sourceVariable;
    this.targetSimulator = targetSimulator;
    this.targetVariable = targetVariable;
  }
  
  public Simulator getSourceSimulator() {
    return sourceSimulator;
  }
  
  public Variable getSourceVariable() {
    return sourceVariable;
  }
  
  public Simulator getTargetSimulator() {
    return targetSimulator;
  }
  
  public Variable getTargetVariable() {
    return targetVariable;
  }
}
