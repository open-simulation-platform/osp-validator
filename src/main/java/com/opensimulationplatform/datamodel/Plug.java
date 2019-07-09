package com.opensimulationplatform.datamodel;

import java.util.Map;

public class Plug extends Entity {
  private String type;
  private String name;
  private Map<String, Variable> variables;
  
  public Plug(String type, String name, Map<String, Variable> variables) {
    super(type + "_plug_" + name);
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
