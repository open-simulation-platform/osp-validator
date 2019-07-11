package com.opensimulationplatform.datamodel.modeldefinition;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bond extends Entity {
  
  private Simulator simulator;
  private Set<Plug> plugs;
  private Set<Socket> sockets;
  
  public Bond(String name, List<Plug> plugs, List<Socket> sockets) {
    super(name);
    this.plugs = new HashSet<>(plugs);
    this.sockets = new HashSet<>(sockets);
  }
  
  public Bond(String name) {
    this(name, new ArrayList<>(), new ArrayList<>());
  }
  
  public List<Plug> getPlugs() {
    return new ArrayList<>(plugs);
  }
  
  public List<Socket> getSockets() {
    return new ArrayList<>(sockets);
  }
  
  public void addPlug(Plug plug) {
    plugs.add(plug);
    plug.setSimulator(simulator);
    if (!plug.getBonds().containsValue(this)) {
      plug.addBond(this);
    }
  }
  
  public void setSimulator(Simulator simulator) {
    if (simulator != null) {
      if (tryingToChangeSimulator(simulator)) {
        throw new RuntimeException("Can not change simulator for existing bond");
      } else if (this.simulator == null){
        this.simulator = simulator;
        plugs.forEach(plug -> plug.setSimulator(simulator));
        sockets.forEach(socket -> socket.setSimulator(simulator));
        if (!simulator.getBonds().containsValue(this)) {
          simulator.addBond(this);
        }
      }
    }
  }
  
  private boolean tryingToChangeSimulator(Simulator simulator) {
    return this.simulator != simulator && this.simulator != null;
  }
  
  public Simulator getSimulator() {
    return simulator;
  }
  
  public void addSocket(Socket socket) {
    sockets.add(socket);
    socket.setSimulator(simulator);
    if (!socket.getBonds().containsValue(this)) {
      socket.addBond(this);
    }
  }
}
