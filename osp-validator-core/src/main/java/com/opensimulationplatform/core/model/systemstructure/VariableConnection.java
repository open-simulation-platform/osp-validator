/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.systemstructure;

import com.opensimulationplatform.core.model.ID;
import com.opensimulationplatform.core.model.modeldescription.Variable;

public class VariableConnection {
  private final ID id = new ID();
  private Variable variableA;
  private Variable variableB;
  private Simulator simulatorA;
  private Simulator simulatorB;

  public ID getId() {
    return id;
  }

  public Variable getVariableA() {
    return variableA;
  }

  public void setVariableA(Variable variableA) {
    this.variableA = variableA;
  }

  public Variable getVariableB() {
    return variableB;
  }

  public void setVariableB(Variable variableB) {
    this.variableB = variableB;
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
