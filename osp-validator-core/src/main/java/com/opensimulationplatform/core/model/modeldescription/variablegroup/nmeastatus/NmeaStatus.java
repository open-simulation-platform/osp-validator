package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeastatus;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;

import java.util.Collections;
import java.util.List;

public class NmeaStatus extends VariableGroup {
  @Override
  public void setVariables(List<Variable> variables) {
    if (variables.size() != 1) {
      throw new RuntimeException("NmeaStatus variable group must contain exactly 1 variable");
    } else {
      this.variables = variables;
    }
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.emptyList();
  }
}
