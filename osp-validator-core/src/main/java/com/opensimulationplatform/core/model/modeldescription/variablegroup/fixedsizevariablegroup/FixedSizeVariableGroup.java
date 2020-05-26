package com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;

import java.util.List;

public abstract class FixedSizeVariableGroup extends VariableGroup {

  protected abstract int size();

  @Override
  public void setVariables(List<Variable> variables) {
    if (variables.size() != this.size()) {
      throw new RuntimeException(this.getClass().getSimpleName() + " variable group must contain exactly " + this.size() + " variable");
    } else {
      this.variables = variables;
    }
  }
}
