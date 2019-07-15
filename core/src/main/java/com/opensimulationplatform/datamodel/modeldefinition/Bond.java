package com.opensimulationplatform.datamodel.modeldefinition;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Bond  {
  
  private Simulator simulator;
  private final List<Plug> plugs = new ArrayList<>();
  private final List<Socket> sockets = new ArrayList<>();
  private final String name;
  
  public Bond(String name) {
    this.name = name;
  }
  
  public void setSimulator(Simulator simulator) {
    if (nonNull(simulator)) {
      if (tryingToChangeSimulator(simulator)) {
        throw new RuntimeException("Can not change simulator for existing bond");
      } else if (isNull(this.simulator)) {
        this.simulator = simulator;
        plugs.forEach(plug -> plug.setSimulator(simulator));
        sockets.forEach(socket -> socket.setSimulator(simulator));
        if (!simulator.getBonds().containsValue(this)) {
          simulator.addBond(this);
        }
      }
    }
  }
  
  public void addPlug(Plug plug) {
    if (nonNull(plug) && !plugs.contains(plug)) {
      plugs.add(plug);
      plug.setSimulator(simulator);
      if (!plug.getBonds().containsValue(this)) {
        plug.addBond(this);
      }
    }
  }
  
  public void addSocket(Socket socket) {
    if (nonNull(socket) && !sockets.contains(socket)) {
      sockets.add(socket);
      socket.setSimulator(simulator);
      if (socket.getBond() != this) {
        socket.addBond(this);
      }
    }
  }
  
  private boolean tryingToChangeSimulator(Simulator simulator) {
    return nonNull(this.simulator) && this.simulator != simulator;
  }
  
  public Simulator getSimulator() {
    return simulator;
  }
  
  public List<Plug> getPlugs() {
    return new ArrayList<>(plugs);
  }
  
  public List<Socket> getSockets() {
    return new ArrayList<>(sockets);
  }
  
  public String getName() {
    return name;
  }
}
