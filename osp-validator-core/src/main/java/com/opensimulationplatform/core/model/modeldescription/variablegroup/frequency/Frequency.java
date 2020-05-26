package com.opensimulationplatform.core.model.modeldescription.variablegroup.frequency;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;

import java.util.Collections;
import java.util.List;

public class Frequency extends FixedSizeVariableGroup {
  @Override
  protected int size() {
    return 1;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.emptyList();
  }
}
