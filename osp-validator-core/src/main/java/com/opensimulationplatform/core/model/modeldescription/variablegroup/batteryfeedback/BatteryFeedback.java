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
