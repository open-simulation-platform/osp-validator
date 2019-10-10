package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.OspObject;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class OspSocket implements OspObject {
  private final String name;
  private final String type;
  private OspSimulator ospSimulator;
  private OspBond ospBond;
  private final Map<String, OspVariable> variables = new HashMap<>();
  
  public OspSocket(String type, String name) {
    this.type = type;
    this.name = name;
  }
  
  public void setOspSimulator(OspSimulator ospSimulator) {
    if (nonNull(ospSimulator)) {
      if (tryingToChangeSimulator(ospSimulator)) {
        throw new RuntimeException("Can not change simulator for existing socket");
      } else if (isNull(this.ospSimulator)) {
        this.ospSimulator = ospSimulator;
        variables.forEach((variableName, variable) -> variable.setOspSimulator(ospSimulator));
        if (hasBond()) {
          ospBond.setOspSimulator(ospSimulator);
        }
        if (!ospSimulator.getSockets().containsValue(this)) {
          ospSimulator.addSocket(this);
        }
      }
    }
  }
  
  public void addVariable(OspVariable ospVariable) {
    if (nonNull(ospVariable)) {
      OspVariable old = variables.put(ospVariable.getName(), ospVariable);
      if (nonNull(old) && old != ospVariable) {
        throw new RuntimeException("Can not add two variables with same name");
      } else {
        ospVariable.setOspSimulator(ospSimulator);
        if (ospVariable.getOspSocket() != this) {
          ospVariable.addSocket(this);
        }
      }
    }
  }
  
  public void addBond(OspBond ospBond) {
    if (hasBond() && !this.ospBond.equals(ospBond)) {
      throw new RuntimeException("Can not change bond for existing socket");
    } else {
      this.ospBond = ospBond;
      ospBond.setOspSimulator(ospSimulator);
      if (!ospBond.getOspSockets().contains(this)) {
        ospBond.addSocket(this);
      }
    }
  }
  
  public OspSimulator getOspSimulator() {
    return ospSimulator;
  }
  
  public OspBond getOspBond() {
    return ospBond;
  }
  
  public Map<String, OspVariable> getVariables() {
    return variables;
  }
  
  public String getType() {
    return type;
  }
  
  public String getName() {
    return name;
  }
  
  private boolean tryingToChangeSimulator(OspSimulator ospSimulator) {
    return nonNull(this.ospSimulator) && this.ospSimulator != ospSimulator;
  }
  
  private boolean hasBond() {
    return nonNull(this.ospBond);
  }
}
