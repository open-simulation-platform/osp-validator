package com.opensimulationplatform.cseconfig.json.model;

public class JsonCsePlugSocketConnection {
  private String sourceSimulator;
  private String plug;
  private String targetSimulator;
  private String socket;
  
  public void setSourceSimulator(String sourceSimulator) {
    this.sourceSimulator = sourceSimulator;
  }
  
  public void setPlug(String plug) {
    this.plug = plug;
  }
  
  public void setTargetSimulator(String targetSimulator) {
    this.targetSimulator = targetSimulator;
  }
  
  public void setSocket(String socket) {
    this.socket = socket;
  }
  
  public String getSourceSimulator() {
    return sourceSimulator;
  }
  
  public String getPlug() {
    return plug;
  }
  
  public String getTargetSimulator() {
    return targetSimulator;
  }
  
  public String getSocket() {
    return socket;
  }
}
