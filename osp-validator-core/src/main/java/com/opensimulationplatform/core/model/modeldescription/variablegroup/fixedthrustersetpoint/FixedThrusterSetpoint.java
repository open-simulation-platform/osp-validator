/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedthrustersetpoint;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch.BladePitch;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed.ShaftSpeed;

import java.util.Arrays;
import java.util.List;

public class FixedThrusterSetpoint extends FixedSizeVariableGroup {

  private ShaftSpeed shaftSpeed;
  private BladePitch bladePitch;

  @Override
  protected int size() {
    return 0;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(shaftSpeed, bladePitch);
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
}
