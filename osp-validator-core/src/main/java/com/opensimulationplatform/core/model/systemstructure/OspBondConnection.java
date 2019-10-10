package com.opensimulationplatform.core.model.systemstructure;

import com.opensimulationplatform.core.model.OspObject;
import com.opensimulationplatform.core.model.modeldescription.OspBond;

public class OspBondConnection implements OspObject {
  private final OspSimulator ospSimulatorA;
  private final OspBond ospBondA;
  private final OspSimulator ospSimulatorB;
  private final OspBond ospBondB;
  
  public OspBondConnection(OspSimulator ospSimulatorA, OspBond ospBondA, OspSimulator ospSimulatorB, OspBond ospBondB) {
    this.ospSimulatorA = ospSimulatorA;
    this.ospBondA = ospBondA;
    this.ospSimulatorB = ospSimulatorB;
    this.ospBondB = ospBondB;
  }
  
  public OspSimulator getOspSimulatorA() {
    return ospSimulatorA;
  }
  
  public OspBond getOspBondA() {
    return ospBondA;
  }
  
  public OspSimulator getOspSimulatorB() {
    return ospSimulatorB;
  }
  
  public OspBond getOspBondB() {
    return ospBondB;
  }
}
