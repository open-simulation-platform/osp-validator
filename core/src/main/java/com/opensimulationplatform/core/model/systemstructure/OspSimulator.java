package com.opensimulationplatform.core.model.systemstructure;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.modeldescription.OspVariable;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class OspSimulator {
  private final String name;
  private final String source;
  private final Map<String, OspVariable> variables = new HashMap<>();
  private final Map<String, OspBond> bonds = new HashMap<>();
  private final Map<String, OspPlug> plugs = new HashMap<>();
  private final Map<String, OspSocket> sockets = new HashMap<>();
  
  public OspSimulator(String name, String source) {
    this.name = name;
    this.source = source;
  }
  
  public void addVariable(OspVariable ospVariable) {
    if (nonNull(ospVariable)) {
      OspVariable old = variables.put(ospVariable.getName(), ospVariable);
      if (nonNull(old) && old != ospVariable) {
        throw new RuntimeException("Can not add two variables with same name");
      } else if (ospVariable.getOspSimulator() != this) {
        ospVariable.setOspSimulator(this);
      }
    }
  }
  
  public void addPlug(OspPlug ospPlug) {
    if (nonNull(ospPlug)) {
      OspPlug old = plugs.put(ospPlug.getName(), ospPlug);
      if (nonNull(old) && old != ospPlug) {
        throw new RuntimeException("Can not add two plugs with same name");
      } else if (ospPlug.getOspSimulator() != this) {
        ospPlug.setOspSimulator(this);
      }
    }
  }
  
  public void addSocket(OspSocket ospSocket) {
    if (nonNull(ospSocket)) {
      OspSocket old = sockets.put(ospSocket.getName(), ospSocket);
      if (nonNull(old) && old != ospSocket) {
        throw new RuntimeException("Can not add two sockets with same name");
      } else if (ospSocket.getOspSimulator() != this) {
        ospSocket.setOspSimulator(this);
      }
    }
  }
  
  public void addBond(OspBond ospBond) {
    bonds.put(ospBond.getName(), ospBond);
    if (ospBond.getOspSimulator() != this) {
      ospBond.setOspSimulator(this);
    }
  }
  
  public String getSource() {
    return source;
  }
  
  public Map<String, OspBond> getBonds() {
    return bonds;
  }
  
  public Map<String, OspPlug> getPlugs() {
    return plugs;
  }
  
  public Map<String, OspSocket> getSockets() {
    return sockets;
  }
  
  public Map<String, OspVariable> getVariables() {
    return variables;
  }
  
  public String getName() {
    return name;
  }
}
