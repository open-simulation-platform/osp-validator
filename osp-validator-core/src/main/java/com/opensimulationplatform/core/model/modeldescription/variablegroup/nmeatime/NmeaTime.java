package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatime;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;

import java.util.Collections;
import java.util.List;

public class NmeaTime extends VariableGroup {
  @Override
  public void setVariables(List<Variable> variables) {
    if (variables.size() != 1) {
      throw new RuntimeException("NmeaTime variable group must contain exactly 1 variable");
    } else {
      this.variables = variables;
    }
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.emptyList();
  }
}
