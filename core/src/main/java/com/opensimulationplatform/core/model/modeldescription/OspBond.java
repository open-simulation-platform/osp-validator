package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.systemstructure.OspSimulator;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class OspBond {
  
  private OspSimulator ospSimulator;
  private final List<OspPlug> ospPlugs = new ArrayList<>();
  private final List<OspSocket> ospSockets = new ArrayList<>();
  private final String name;
  
  public OspBond(String name) {
    this.name = name;
  }
  
  public void setOspSimulator(OspSimulator ospSimulator) {
    if (nonNull(ospSimulator)) {
      if (tryingToChangeSimulator(ospSimulator)) {
        throw new RuntimeException("Can not change simulator for existing bond");
      } else if (isNull(this.ospSimulator)) {
        this.ospSimulator = ospSimulator;
        ospPlugs.forEach(plug -> plug.setOspSimulator(ospSimulator));
        ospSockets.forEach(socket -> socket.setOspSimulator(ospSimulator));
        if (!ospSimulator.getBonds().containsValue(this)) {
          ospSimulator.addBond(this);
        }
      }
    }
  }
  
  public void addPlug(OspPlug ospPlug) {
    if (!ospPlugs.contains(ospPlug) && nonNull(ospPlug)) {
      if (plugWithSameNameAlreadyAdded(ospPlug)) {
        throw new RuntimeException("Can not add two plugs with same name");
      } else {
        ospPlugs.add(ospPlug);
        ospPlug.setOspSimulator(ospSimulator);
        if (!ospPlug.getBonds().containsValue(this)) {
          ospPlug.addBond(this);
        }
      }
    }
  }
  
  private boolean plugWithSameNameAlreadyAdded(OspPlug ospPlug) {
    return ospPlugs.stream().anyMatch(p -> p.getName().equals(ospPlug.getName()));
  }
  
  public void addSocket(OspSocket ospSocket) {
    if (!ospSockets.contains(ospSocket) && nonNull(ospSocket)) {
      if (socketWithSameNameAlreadyAdded(ospSocket)) {
        throw new RuntimeException("Can not add two plugs with same name");
      } else {
        ospSockets.add(ospSocket);
        ospSocket.setOspSimulator(ospSimulator);
        if (ospSocket.getOspBond() != this) {
          ospSocket.addBond(this);
        }
      }
    }
  }
  
  private boolean socketWithSameNameAlreadyAdded(OspSocket ospSocket) {
    return ospSockets.stream().anyMatch(s -> s.getName().equals(ospSocket.getName()));
  }
  
  private boolean tryingToChangeSimulator(OspSimulator ospSimulator) {
    return nonNull(this.ospSimulator) && this.ospSimulator != ospSimulator;
  }
  
  public OspSimulator getOspSimulator() {
    return ospSimulator;
  }
  
  public List<OspPlug> getOspPlugs() {
    return new ArrayList<>(ospPlugs);
  }
  
  public List<OspSocket> getOspSockets() {
    return new ArrayList<>(ospSockets);
  }
  
  public String getName() {
    return name;
  }
}
