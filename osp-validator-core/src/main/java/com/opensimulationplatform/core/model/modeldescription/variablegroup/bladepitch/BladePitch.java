package com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;

import java.util.Arrays;
import java.util.List;

public class BladePitch extends FixedSizeVariableGroup {

  private AngularDisplacement angularDisplacement;

  @Override
  protected int size() {
    return 0;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(angularDisplacement);
  }

  public AngularDisplacement getAngularDisplacement() {
    return angularDisplacement;
  }

  public void setAngularDisplacement(AngularDisplacement angularDisplacement) {
    this.angularDisplacement = angularDisplacement;
  }
}
