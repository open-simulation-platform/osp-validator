package com.opensimulationplatform.modeldescription.json.model;

import java.util.List;

public class JsonOspPlug {
  private String type;
  private String name;
  private List<String> variables;
  
  public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public List<String> getVariables() {
    return variables;
  }
  
  public void setVariables(List<String> variables) {
    this.variables = variables;
  }
}
