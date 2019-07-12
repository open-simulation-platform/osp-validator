package com.opensimulationplatform.datamodel.configuration;

import com.opensimulationplatform.datamodel.modeldefinition.Plug;
import com.opensimulationplatform.datamodel.modeldefinition.Simulator;
import com.opensimulationplatform.datamodel.modeldefinition.Socket;

public class PlugSocketConnection {
  private Simulator sourceSimulator;
  private Plug plug;
  private Simulator targetSimulator;
  private Socket socket;
  
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
