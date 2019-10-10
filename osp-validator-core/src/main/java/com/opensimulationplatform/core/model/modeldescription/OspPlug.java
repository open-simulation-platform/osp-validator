package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.OspObject;
import com.opensimulationplatform.core.model.systemstructure.OspSimulator;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class OspPlug implements OspObject {
  private final String name;
  private final String type;
  private OspSimulator ospSimulator;
  private final Map<String, OspBond> bonds = new HashMap<>();
  private final Map<String, OspVariable> variables = new HashMap<>();
  
  public OspPlug(String type, String name) {
    this.type = type;
    this.name = name;
  }
  
  public void setOspSimulator(OspSimulator ospSimulator) {
    if (nonNull(ospSimulator)) {
      if (tryingToChangeSimulator(ospSimulator)) {
        throw new RuntimeException("Can not change simulator for existing plug");
      } else if (isNull(this.ospSimulator)) {
        this.ospSimulator = ospSimulator;
        variables.forEach((variableName, variable) -> variable.setOspSimulator(ospSimulator));
        bonds.forEach((bondName, bond) -> bond.setOspSimulator(ospSimulator));
        if (!ospSimulator.getPlugs().containsValue(this)) {
          ospSimulator.addPlug(this);
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
        if (!ospVariable.getPlugs().containsValue(this)) {
          ospVariable.addPlug(this);
        }
      }
    }
  }
  
  public void addBond(OspBond ospBond) {
    if (nonNull(ospBond)) {
      OspBond old = bonds.put(ospBond.getName(), ospBond);
      if (nonNull(old) && old != ospBond) {
        throw new RuntimeException("Can not add two bonds with same name");
      } else {
        ospBond.setOspSimulator(ospSimulator);
        if (!ospBond.getOspPlugs().contains(this)) {
          ospBond.addPlug(this);
        }
      }
    }
  }
  
  private boolean tryingToChangeSimulator(OspSimulator ospSimulator) {
    return nonNull(this.ospSimulator) && this.ospSimulator != ospSimulator;
  }
  
  public OspSimulator getOspSimulator() {
    return ospSimulator;
  }
  
  public Map<String, OspBond> getBonds() {
    return bonds;
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
}
