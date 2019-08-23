package com.opensimulationplatform.core.validator.model.ospmodeldescription;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Variable {
  
  private Simulator simulator;
  private final Map<String, Plug> plugs = new HashMap<>();
  private Socket socket;
  private final String name;
  
  public Variable(String name) {
    this.name = name;
  }
  
  public void setSimulator(Simulator simulator) {
    if (simulator != null) {
      if (tryingToChangeSimulator(simulator)) {
        throw new RuntimeException("Can not change simulator for existing bond");
      } else if (this.simulator == null) {
        this.simulator = simulator;
        if (hasSocket()) {
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
  
  public void addPlug(Plug plug) {
    if (isNull(socket)) {
      this.plugs.put(plug.getName(), plug);
      plug.setSimulator(simulator);
      if (!plug.getVariables().containsValue(this)) {
        plug.addVariable(this);
      }
    } else {
      throw new RuntimeException("A variable can not be added to both sockets and plugs");
    }
  }
  
  public void addSocket(Socket socket) {
    if (!plugs.isEmpty()) {
      throw new RuntimeException("A variable can not be added to both sockets and plugs");
    } else if (nonNull(this.socket) && !this.socket.equals(socket)) {
      throw new RuntimeException("Can not change socket for existing variable");
    } else {
      this.socket = socket;
      socket.setSimulator(simulator);
      if (!socket.getVariables().containsValue(this)) {
        socket.addVariable(this);
      }
    }
  }
  
  public String getName() {
    return name;
  }
  
  public Simulator getSimulator() {
    return simulator;
  }
  
  public Map<String, Plug> getPlugs() {
    return plugs;
  }
  
  public Socket getSocket() {
    return socket;
  }
  
  private boolean tryingToChangeSimulator(Simulator simulator) {
    return nonNull(this.simulator) && this.simulator != simulator;
  }
  
  private boolean hasSocket() {
    return nonNull(socket);
  }
}
