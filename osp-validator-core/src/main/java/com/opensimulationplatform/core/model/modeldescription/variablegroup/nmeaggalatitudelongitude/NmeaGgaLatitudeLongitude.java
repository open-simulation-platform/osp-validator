package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggalatitudelongitude;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;

import java.util.Collections;
import java.util.List;

public class NmeaGgaLatitudeLongitude extends FixedSizeVariableGroup {
  @Override
  protected int size() {
    return 4;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.emptyList();
  }
}
