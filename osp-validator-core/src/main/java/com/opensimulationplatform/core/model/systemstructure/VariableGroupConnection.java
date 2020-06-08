/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.systemstructure;

import com.opensimulationplatform.core.model.ID;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;

import java.util.UUID;

public class VariableGroupConnection {
  private final String id = UUID.randomUUID().toString();
  private VariableGroup variableGroupA;
  private VariableGroup variableGroupB;
  private Simulator simulatorA;
  private Simulator simulatorB;

  public ID getId() {
    return () -> id;
  }

  public VariableGroup getVariableGroupA() {
    return variableGroupA;
  }

  public void setVariableGroupA(VariableGroup variableGroupA) {
    this.variableGroupA = variableGroupA;
  }

  public VariableGroup getVariableGroupB() {
    return variableGroupB;
  }

  public void setVariableGroupB(VariableGroup variableGroupB) {
    this.variableGroupB = variableGroupB;
  }

  public Simulator getSimulatorA() {
    return simulatorA;
  }

  public void setSimulatorA(Simulator simulatorA) {
    this.simulatorA = simulatorA;
  }

  public Simulator getSimulatorB() {
    return simulatorB;
  }

  public void setSimulatorB(Simulator simulatorB) {
    this.simulatorB = simulatorB;
  }
}
