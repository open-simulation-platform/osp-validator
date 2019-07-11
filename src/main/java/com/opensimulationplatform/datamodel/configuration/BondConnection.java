package com.opensimulationplatform.datamodel.configuration;

import com.opensimulationplatform.datamodel.modeldefinition.Bond;
import com.opensimulationplatform.datamodel.modeldefinition.Simulator;

public class BondConnection {
  private Simulator simulatorA;
  private Bond bondA;
  private Simulator simulatorB;
  private Bond bondB;
  
  public BondConnection(Simulator simulatorA, Bond bondA, Simulator simulatorB, Bond bondB) {
    this.simulatorA = simulatorA;
    this.bondA = bondA;
    this.simulatorB = simulatorB;
    this.bondB = bondB;
  }
  
  public Simulator getSimulatorA() {
    return simulatorA;
  }
  
  public Bond getBondA() {
    return bondA;
  }
  
  public Simulator getSimulatorB() {
    return simulatorB;
  }
  
  public Bond getBondB() {
    return bondB;
  }
}
