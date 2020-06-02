/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription.variablegroup.batteryfeedback;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electricpower.ElectricPower;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;

import java.util.Arrays;
import java.util.List;

public class BatteryFeedback extends FixedSizeVariableGroup {

  private ElectricPower electricPower;

  @Override
  protected int size() {
    return 1;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(electricPower);
  }

  public ElectricPower getElectricPower() {
    return electricPower;
  }

  public void setElectricPower(ElectricPower electricPower) {
    this.electricPower = electricPower;
  }
}
