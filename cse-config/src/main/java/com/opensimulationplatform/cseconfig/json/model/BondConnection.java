package com.opensimulationplatform.cseconfig.json.model;

public class BondConnection {
  private String simulatorA;
  private String bondA;
  private String simulatorB;
  private String bondB;
  
  public void setSimulatorA(String simulatorA) {
    this.simulatorA = simulatorA;
  }
  
  public void setBondA(String bondA) {
    this.bondA = bondA;
  }
  
  public void setSimulatorB(String simulatorB) {
    this.simulatorB = simulatorB;
  }
  
  public void setBondB(String bondB) {
    this.bondB = bondB;
  }
  
  public String getSimulatorA() {
    return simulatorA;
  }
  
  public String getBondA() {
    return bondA;
  }
  
  public String getSimulatorB() {
    return simulatorB;
  }
  
  public String getBondB() {
    return bondB;
  }
}
