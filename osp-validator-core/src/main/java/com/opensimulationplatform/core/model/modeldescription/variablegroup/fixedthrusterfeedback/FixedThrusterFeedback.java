package com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedthrusterfeedback;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch.BladePitch;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed.ShaftSpeed;

import java.util.List;

public class FixedThrusterFeedback extends FixedSizeVariableGroup {

  private ShaftSpeed shaftSpeed;
  private BladePitch bladePitch;

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
}
