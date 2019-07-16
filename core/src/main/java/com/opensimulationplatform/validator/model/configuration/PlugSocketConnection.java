package com.opensimulationplatform.validator.model.configuration;

import com.opensimulationplatform.validator.model.modeldefinition.Plug;
import com.opensimulationplatform.validator.model.modeldefinition.Simulator;
import com.opensimulationplatform.validator.model.modeldefinition.Socket;

public class PlugSocketConnection {
  private final Simulator sourceSimulator;
  private final Plug plug;
  private final Simulator targetSimulator;
  private final Socket socket;
  
  public PlugSocketConnection(Simulator sourceSimulator, Plug plug, Simulator targetSimulator, Socket socket) {
    this.sourceSimulator = sourceSimulator;
    this.plug = plug;
    this.targetSimulator = targetSimulator;
    this.socket = socket;
  }
  
  public Simulator getSourceSimulator() {
    return sourceSimulator;
  }
  
  public Plug getPlug() {
    return plug;
  }
  
  public Simulator getTargetSimulator() {
    return targetSimulator;
  }
  
  public Socket getSocket() {
    return socket;
  }
}
