package com.opensimulationplatform.datamodel.modeldefinition;

import java.util.HashMap;
import java.util.Map;

public class Plug extends Entity {
  
  private String type;
  private Simulator simulator;
  private Map<String, Bond> bonds = new HashMap<>();
  private Map<String, Variable> variables;
  
  public Plug(String type, String name, Map<String, Variable> variables) {
    super(name);
    this.type = type;
    
    variables.forEach((s, variable) -> variable.setParent(this));
    this.variables = variables;
  }
  
  public Plug(String type, String name) {
    this(type, name, new HashMap<>());
  }
  
  public String getType() {
    return type;
  }
  
  public Map<String, Variable> getVariables() {
    return variables;
  }
  
  public void setSimulator(Simulator simulator) {
    if (simulator != null) {
      if (tryingToChangeSimulator(simulator)) {
        throw new RuntimeException("Can not change simulator for existing plug");
      } else if (this.simulator == null){
        this.simulator = simulator;
        variables.forEach((variableName, variable) -> variable.setSimulator(simulator));
        bonds.forEach((bondName, bond) -> bond.setSimulator(simulator));
        if (!simulator.getPlugs().containsValue(this)) {
          simulator.addPlug(this);
        }
      }
    }
  }
  
  private boolean tryingToChangeSimulator(Simulator simulator) {
    return this.simulator != simulator && this.simulator != null;
  }
  
  void addVariable(Variable variable) {
    variables.put(variable.getName(), variable);
    variable.setSimulator(simulator);
    if (!variable.getPlugs().containsValue(this)) {
      variable.addPlug(this);
    }
  }
  
  public Simulator getSimulator() {
    return simulator;
  }
  
  public Map<String, Bond> getBonds() {
    return bonds;
  }
  
  public void addBond(Bond bond) {
    bonds.put(bond.getName(), bond);
    bond.setSimulator(simulator);
    if (!bond.getPlugs().contains(this)) {
      bond.addPlug(this);
    }
  }
}
