package com.opensimulationplatform.datamodel.modeldefinition;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Plug {
  
  private final String type;
  private final String name;
  private Simulator simulator;
  private final Map<String, Bond> bonds = new HashMap<>();
  private final Map<String, Variable> variables = new HashMap<>();
  
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
    if (nonNull(variable)) {
      Variable old = variables.put(variable.getName(), variable);
      if (nonNull(old) && old != variable) {
        throw new RuntimeException("Can not add two variables with same name");
      } else {
        variable.setSimulator(simulator);
        if (!variable.getPlugs().containsValue(this)) {
          variable.addPlug(this);
        }
      }
    }
  }
  
  public void addBond(Bond bond) {
    if (nonNull(bond)) {
      Bond old = bonds.put(bond.getName(), bond);
      if (nonNull(old) && old != bond) {
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
  
  public Map<String, Bond> getBonds() {
    return bonds;
  }
  
  public Map<String, Variable> getVariables() {
    return variables;
  }
  
  public String getType() {
    return type;
  }
  
  public String getName() {
    return name;
  }
}
