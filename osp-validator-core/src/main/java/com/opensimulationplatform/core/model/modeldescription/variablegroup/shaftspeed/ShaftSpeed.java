package com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;

import java.util.Arrays;
import java.util.List;

public class ShaftSpeed extends FixedSizeVariableGroup {

  private AngularVelocity angularVelocity;

  @Override
  protected int size() {
    return 0;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(angularVelocity);
  }

  public AngularVelocity getAngularVelocity() {
    return angularVelocity;
  }

  public void setAngularVelocity(AngularVelocity angularVelocity) {
    this.angularVelocity = angularVelocity;
  }
}
