package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstellipse;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;

import java.util.Collections;
import java.util.List;

public class NmeaGstEllipse extends FixedSizeVariableGroup {
  @Override
  protected int size() {
    return 3;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.emptyList();
  }
}
