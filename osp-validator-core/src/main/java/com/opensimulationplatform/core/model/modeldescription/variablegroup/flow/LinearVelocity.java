package com.opensimulationplatform.core.model.modeldescription.variablegroup.flow;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinearVelocity extends VariableGroup {

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
