package com.opensimulationplatform.json.model.configuration;

public class JsonVariableConnection {
  private String sourceSimulator;
  private String sourceVariable;
  private String targetSimulator;
  private String targetVariable;
  
  public void setSourceSimulator(String sourceSimulator) {
    this.sourceSimulator = sourceSimulator;
  }
  
  public void setSourceVariable(String sourceVariable) {
    this.sourceVariable = sourceVariable;
  }
  
  public void setTargetSimulator(String targetSimulator) {
    this.targetSimulator = targetSimulator;
  }
  
  public void setTargetVariable(String targetVariable) {
    this.targetVariable = targetVariable;
  }
  
  public String getSourceSimulator() {
    return sourceSimulator;
  }
  
  public String getSourceVariable() {
    return sourceVariable;
  }
  
  public String getTargetSimulator() {
    return targetSimulator;
  }
  
  public String getTargetVariable() {
    return targetVariable;
  }
}
