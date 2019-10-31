package com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Charge extends VariableGroup {

  private final List<Variable> variables = new ArrayList<>();

  @Override
  public List<Variable> getVariables() {
    return variables;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.emptyList();
  }
}
