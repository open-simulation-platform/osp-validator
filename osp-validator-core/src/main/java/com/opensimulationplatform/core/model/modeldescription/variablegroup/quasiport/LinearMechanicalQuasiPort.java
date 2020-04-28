package com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.LinearDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup.NDimensionalVariableGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LinearMechanicalQuasiPort extends NDimensionalVariableGroup {

  private Force force;
  private LinearDisplacement linearDisplacement;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(force, linearDisplacement));
  }

  public Force getForce() {
    return force;
  }

  public void setForce(Force force) {
    this.force = force;
  }

  public LinearDisplacement getLinearDisplacement() {
    return linearDisplacement;
  }

  public void setLinearDisplacement(LinearDisplacement linearDisplacement) {
    this.linearDisplacement = linearDisplacement;
  }

  @Override
  protected int numberOfDimensions() {
    return 0;
  }
}
