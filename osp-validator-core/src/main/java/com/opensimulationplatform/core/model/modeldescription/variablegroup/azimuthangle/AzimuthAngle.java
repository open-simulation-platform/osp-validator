package com.opensimulationplatform.core.model.modeldescription.variablegroup.azimuthangle;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;

import java.util.List;

public class AzimuthAngle extends AngularDisplacement {
  @Override
  public void setVariables(List<Variable> variables) {
    if (variables.size() != 1) {
      throw new RuntimeException(this.getClass().getSimpleName() + " variable group must contain exactly " + 1 + " variable");
    } else {
      this.variables = variables;
    }
  }
}
