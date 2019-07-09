package com.opensimulationplatform.datamodel;

public class Variable extends Entity {
  private String name;
  
  public Variable(String name) {
    super("variable_" + name);
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
}
