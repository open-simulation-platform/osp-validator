package com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.LinearDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LinearMechanicalQuasiPort extends VariableGroup {

  private Force force;
  private LinearDisplacement linearDisplacement;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(force, linearDisplacement);
  }

  public void setForce(Force force) {
    this.force = force;
  }

  public void setLinearDisplacement(LinearDisplacement linearDisplacement) {
    this.linearDisplacement = linearDisplacement;
  }
}
