package com.opensimulationplatform.datamodel.modeldefinition;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Plug  {
  
  private String type;
  private final String name;
  private Simulator simulator;
  private Map<String, Bond> bonds = new HashMap<>();
  private Map<String, Variable> variables = new HashMap<>();
  
  public Plug(String type, String name) {
    this.type = type;
    this.name = name;
  }
  
  public void setSimulator(Simulator simulator) {
    if (nonNull(simulator)) {
      if (tryingToChangeSimulator(simulator)) {
        throw new RuntimeException("Can not change simulator for existing plug");
      } else if (isNull(this.simulator)) {
        this.simulator = simulator;
        variables.forEach((variableName, variable) -> variable.setSimulator(simulator));
        bonds.forEach((bondName, bond) -> bond.setSimulator(simulator));
        if (!simulator.getPlugs().containsValue(this)) {
          simulator.addPlug(this);
        }
      }
    }
  }
  
  public void addVariable(Variable variable) {
    variables.put(variable.getName(), variable);
    variable.setSimulator(simulator);
    if (!variable.getPlugs().containsValue(this)) {
      variable.addPlug(this);
    }
  }
  
  public void addBond(Bond bond) {
    if (nonNull(bond)) {
      Bond b = bonds.put(bond.getName(), bond);
      if (nonNull(b) && b != bond) {
        throw new RuntimeException("Can not add two bonds with same name");
      } else {
        bond.setSimulator(simulator);
        if (!bond.getPlugs().contains(this)) {
          bond.addPlug(this);
        }
      }
    }
  }
  
  private boolean tryingToChangeSimulator(Simulator simulator) {
    return nonNull(this.simulator) && this.simulator != simulator;
  }
  
  public Simulator getSimulator() {
    return simulator;
  }
  
  public Map<String, Variable> getVariables() {
    return variables;
  }
  
  public Map<String, Bond> getBonds() {
    return bonds;
  }
  
  public String getType() {
    return type;
  }
  
  public String getName() {
    return name;
  }
}
