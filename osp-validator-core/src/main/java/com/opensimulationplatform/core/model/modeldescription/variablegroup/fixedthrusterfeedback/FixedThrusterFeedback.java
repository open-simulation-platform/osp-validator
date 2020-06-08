/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedthrusterfeedback;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch.BladePitch;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed.ShaftSpeed;

import java.util.List;

public class FixedThrusterFeedback extends FixedSizeVariableGroup {

  private ShaftSpeed shaftSpeed;
  private BladePitch bladePitch;
  private Force force;

  @Override
  protected int size() {
    return 0;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return null;
  }

  public ShaftSpeed getShaftSpeed() {
    return shaftSpeed;
  }

  public void setShaftSpeed(ShaftSpeed shaftSpeed) {
    this.shaftSpeed = shaftSpeed;
  }

  public BladePitch getBladePitch() {
    return bladePitch;
  }

  public void setBladePitch(BladePitch bladePitch) {
    this.bladePitch = bladePitch;
  }

  public Force getForce() {
    return force;
  }

  public void setForce(Force force) {
    this.force = force;
  }
}
