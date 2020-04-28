package com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;

import java.util.List;

public abstract class NDimensionalVariableGroup extends VariableGroup {

  protected abstract int numberOfDimensions();

  @Override
  public void setVariables(List<Variable> variables) {
    if (variables.size() > numberOfDimensions()) {
      throw new RuntimeException("Maximum number of variables is: " + numberOfDimensions());
    } else {
      this.variables = variables;
    }
  }
}
