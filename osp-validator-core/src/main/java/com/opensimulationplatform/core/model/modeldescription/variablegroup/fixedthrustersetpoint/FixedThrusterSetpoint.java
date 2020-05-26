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
