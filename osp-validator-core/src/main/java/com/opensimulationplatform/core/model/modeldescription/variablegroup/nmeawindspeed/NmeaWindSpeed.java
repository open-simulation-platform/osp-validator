package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawindspeed;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;

import java.util.Collections;
import java.util.List;

public class NmeaWindSpeed extends FixedSizeVariableGroup {

  @Override
  protected int size() {
    return 2;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.emptyList();
  }
}
