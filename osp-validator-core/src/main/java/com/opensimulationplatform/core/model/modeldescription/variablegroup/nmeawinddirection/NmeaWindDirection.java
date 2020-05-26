package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawinddirection;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;

import java.util.Collections;
import java.util.List;

public class NmeaWindDirection extends FixedSizeVariableGroup {
  @Override
  protected int size() {
    return 2;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.emptyList();
  }
}
