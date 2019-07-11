package com.opensimulationplatform.datamodel.modeldefinition;

import java.util.HashMap;
import java.util.Map;

public class Variable extends Entity {
  
  private Simulator simulator;
  private Map<String, Plug> plugs = new HashMap<>();
  private Socket socket;
  
  public Variable(String name) {
    super(name);
  }
  
  public Simulator getSimulator() {
    return simulator;
  }
  
  public void setSimulator(Simulator simulator) {
    if (simulator != null) {
      if (tryingToChangeSimulator(simulator)) {
        throw new RuntimeException("Can not change simulator for existing bond");
      } else if (this.simulator == null) {
        this.simulator = simulator;
        if (socket != null) {
          socket.setSimulator(simulator);
        } else {
          plugs.forEach((plugName, plug) -> plug.setSimulator(simulator));
        }
        if (!simulator.getVariables().containsValue(this)) {
          simulator.addVariable(this);
        }
      }
    }
  }
  
  private boolean tryingToChangeSimulator(Simulator simulator) {
    return this.simulator != simulator && this.simulator != null;
  }
  
  void addPlug(Plug plug) {
    if (socket == null) {
      this.plugs.put(plug.getName(), plug);
      plug.setSimulator(simulator);
      if (!plug.getVariables().containsValue(this)) {
        plug.addVariable(this);
      }
    } else {
      throw new RuntimeException("A variable can not be added to both socket and plugs");
    }
  }
  
  public Map<String, Plug> getPlugs() {
    return plugs;
  }
  
  void addSocket(Socket socket) {
    if (plugs.isEmpty()) {
      this.socket = socket;
      socket.setSimulator(simulator);
      if (!socket.getVariables().containsValue(this)) {
        socket.addVariable(this);
      }
    } else {
      throw new RuntimeException("A variable can not be added to both socket and plugs");
    }
  }
  
  public Socket getSocket() {
    return socket;
  }
}
