/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup.NDimensionalVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AngularMechanicalQuasiPort extends NDimensionalVariableGroup {

  private Torque torque;
  private AngularDisplacement angularDisplacement;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(torque, angularDisplacement));
  }

  public Torque getTorque() {
    return torque;
  }

  public void setTorque(Torque torque) {
    this.torque = torque;
  }

  public AngularDisplacement getAngularDisplacement() {
    return angularDisplacement;
  }

  public void setAngularDisplacement(AngularDisplacement angularDisplacement) {
    this.angularDisplacement = angularDisplacement;
  }

  @Override
  protected int numberOfDimensions() {
    return 0;
  }
}
