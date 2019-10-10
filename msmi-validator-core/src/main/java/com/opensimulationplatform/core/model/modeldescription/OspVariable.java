package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.systemstructure.OspSimulator;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class OspVariable {
  
  private final String name;
  private OspSimulator ospSimulator;
  private final Map<String, OspPlug> plugs = new HashMap<>();
  private OspSocket ospSocket;
  
  public OspVariable(String name) {
    this.name = name;
  }
  
  public void setOspSimulator(OspSimulator ospSimulator) {
    if (ospSimulator != null) {
      if (tryingToChangeSimulator(ospSimulator)) {
        throw new RuntimeException("Can not change simulator for existing bond");
      } else if (this.ospSimulator == null) {
        this.ospSimulator = ospSimulator;
        if (hasSocket()) {
          ospSocket.setOspSimulator(ospSimulator);
        } else {
          plugs.forEach((plugName, plug) -> plug.setOspSimulator(ospSimulator));
        }
        if (!ospSimulator.getVariables().containsValue(this)) {
          ospSimulator.addVariable(this);
        }
      }
    }
  }
  
  public void addPlug(OspPlug ospPlug) {
    if (isNull(ospSocket)) {
      this.plugs.put(ospPlug.getName(), ospPlug);
      ospPlug.setOspSimulator(ospSimulator);
      if (!ospPlug.getVariables().containsValue(this)) {
        ospPlug.addVariable(this);
      }
    } else {
      throw new RuntimeException("A variable can not be added to both sockets and plugs");
    }
  }
  
  public void addSocket(OspSocket ospSocket) {
    if (!plugs.isEmpty()) {
      throw new RuntimeException("A variable can not be added to both sockets and plugs");
    } else if (nonNull(this.ospSocket) && !this.ospSocket.equals(ospSocket)) {
      throw new RuntimeException("Can not change socket for existing variable");
    } else {
      this.ospSocket = ospSocket;
      ospSocket.setOspSimulator(ospSimulator);
      if (!ospSocket.getVariables().containsValue(this)) {
        ospSocket.addVariable(this);
      }
    }
  }
  
  public String getName() {
    return name;
  }
  
  public OspSimulator getOspSimulator() {
    return ospSimulator;
  }
  
  public Map<String, OspPlug> getPlugs() {
    return plugs;
  }
  
  public OspSocket getOspSocket() {
    return ospSocket;
  }
  
  private boolean tryingToChangeSimulator(OspSimulator ospSimulator) {
    return nonNull(this.ospSimulator) && this.ospSimulator != ospSimulator;
  }
  
  private boolean hasSocket() {
    return nonNull(ospSocket);
  }
}
