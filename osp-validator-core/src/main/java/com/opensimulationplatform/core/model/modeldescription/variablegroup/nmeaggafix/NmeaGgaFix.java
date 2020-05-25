package com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;

import java.util.Collections;
import java.util.List;

public class NmeaGgaFix extends VariableGroup {

  @Override
  public void setVariables(List<Variable> variables) {
    if (variables.size() != 7) {
      throw new RuntimeException("NmeaGgaFix variable group requires 7 variables");
    } else {
      this.variables = variables;
    }
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.emptyList();
  }
}
