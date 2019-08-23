package com.opensimulationplatform.cseconfig.jsonmodel;

public class JsonSimulator {
  private String name;
  private String source;
  private String modelDefinition;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setSource(String source) {
    this.source = source;
  }
  
  public void setModelDefinition(String modelDefinition) {
    this.modelDefinition = modelDefinition;
  }
  
  public String getName() {
    return name;
  }
  
  public String getSource() {
    return source;
  }
  
  public String getModelDefinition() {
    return modelDefinition;
  }
}
