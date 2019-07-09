package com.opensimulationplatform.datamodel;

public class BondConnection extends Entity {
  private Simulator simulatorA;
  private Bond bondA;
  private Simulator simulatorB;
  private Bond bondB;
  
  public BondConnection(Simulator simulatorA, Bond bondA, Simulator simulatorB, Bond bondB) {
    super(bondA.getId() + "__" + bondB.getId());
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
