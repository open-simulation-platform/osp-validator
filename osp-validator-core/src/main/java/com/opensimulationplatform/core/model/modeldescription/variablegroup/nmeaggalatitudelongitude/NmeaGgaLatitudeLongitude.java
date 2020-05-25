package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggalatitudelongitude;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;

import java.util.Collections;
import java.util.List;

public class NmeaGgaLatitudeLongitude extends VariableGroup {
  @Override
  public void setVariables(List<Variable> variables) {
    if (variables.size() != 4) {
      throw new RuntimeException("NmeaGgaLatitudeLongitude variable group must contain exactly 4 variable");
    } else {
      this.variables = variables;
    }
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.emptyList();
  }
}
