package com.opensimulationplatform.core.model.systemstructure;

import com.opensimulationplatform.core.model.OspObject;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;

public class OspPlugSocketConnection implements OspObject {
  private final OspSimulator sourceOspSimulator;
  private final OspPlug ospPlug;
  private final OspSimulator targetOspSimulator;
  private final OspSocket ospSocket;
  
  public OspPlugSocketConnection(OspSimulator sourceOspSimulator, OspPlug ospPlug, OspSimulator targetOspSimulator, OspSocket ospSocket) {
    this.sourceOspSimulator = sourceOspSimulator;
    this.ospPlug = ospPlug;
    this.targetOspSimulator = targetOspSimulator;
    this.ospSocket = ospSocket;
  }
  
  public OspSimulator getSourceOspSimulator() {
    return sourceOspSimulator;
  }
  
  public OspPlug getOspPlug() {
    return ospPlug;
  }
  
  public OspSimulator getTargetOspSimulator() {
    return targetOspSimulator;
  }
  
  public OspSocket getOspSocket() {
    return ospSocket;
  }
}
