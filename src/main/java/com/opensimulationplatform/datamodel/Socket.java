package com.opensimulationplatform.datamodel;

import java.util.Map;

public class Socket extends Entity {
  private String type;
  private String name;
  private Map<String, Variable> variables;
  
  public Socket(String type, String name, Map<String, Variable> variables) {
    super(type + "_socket_" + name);
    this.type = type;
    this.name = name;
    
    variables.forEach((s, variable) -> variable.setParent(this));
    this.variables = variables;
  }
  
  public String getType() {
    return type;
  }
  
  public String getName() {
    return name;
  }
  
  public Map<String, Variable> getVariables() {
    return variables;
  }
}
