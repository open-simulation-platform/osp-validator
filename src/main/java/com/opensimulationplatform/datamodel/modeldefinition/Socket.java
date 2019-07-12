package com.opensimulationplatform.datamodel.modeldefinition;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Socket {
  
  private String type;
  private final String name;
  private Simulator simulator;
  private Bond bond;
  private Map<String, Variable> variables = new HashMap<>();
  
  public Socket(String type, String name) {
    this.type = type;
    this.name = name;
  }
  
  public String getType() {
    return type;
  }
  
  public Map<String, Variable> getVariables() {
    return variables;
  }
  
  public void setSimulator(Simulator simulator) {
    if (nonNull(simulator)) {
      if (tryingToChangeSimulator(simulator)) {
        throw new RuntimeException("Can not change simulator for existing socket");
      } else if (isNull(this.simulator)) {
        this.simulator = simulator;
        variables.forEach((variableName, variable) -> variable.setSimulator(simulator));
        if (nonNull(bond)) {
          bond.setSimulator(simulator);
        }
        if (!simulator.getSockets().containsValue(this)) {
          simulator.addSocket(this);
        }
      }
    }
  }
  
  private boolean tryingToChangeSimulator(Simulator simulator) {
    return nonNull(this.simulator) && this.simulator != simulator;
  }
  
  public void addVariable(Variable variable) {
    variables.put(variable.getName(), variable);
    variable.setSimulator(simulator);
    if (variable.getSocket() != this) {
      variable.addSocket(this);
    }
  }
  
  public void addBond(Bond bond) {
    if (nonNull(this.bond) && !this.bond.equals(bond)) {
      throw new RuntimeException("Can not change bond for existing socket");
    } else {
      this.bond = bond;
      bond.setSimulator(simulator);
      if (!bond.getSockets().contains(this)) {
        bond.addSocket(this);
      }
    }
  }
  
  public Simulator getSimulator() {
    return simulator;
  }
  
  public Bond getBond() {
    return bond;
  }
  
  public String getName() {
    return name;
  }
}
