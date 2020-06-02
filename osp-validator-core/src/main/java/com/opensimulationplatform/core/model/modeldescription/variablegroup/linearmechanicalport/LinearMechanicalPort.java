/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup.NDimensionalVariableGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LinearMechanicalPort extends NDimensionalVariableGroup {

  private Force force;
  private LinearVelocity linearVelocity;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(force, linearVelocity));
  }

  public Force getForce() {
    return force;
  }

  public void setForce(Force force) {
    this.force = force;
  }

  public LinearVelocity getLinearVelocity() {
    return linearVelocity;
  }

  public void setLinearVelocity(LinearVelocity linearVelocity) {
    this.linearVelocity = linearVelocity;
  }

  @Override
  protected int numberOfDimensions() {
    return 0;
  }
}
