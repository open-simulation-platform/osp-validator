package com.opensimulationplatform.core.model.modeldescription.variablegroup.port;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup.NDimensionalVariableGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LinearMechanicalPort extends NDimensionalVariableGroup {

  private Force force;
  private LinearVelocity linearVelocity;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(force, linearVelocity));
  }

  public Force getForce() {
    return force;
  }

  public void setForce(Force force) {
    this.force = force;
  }

  public LinearVelocity getLinearVelocity() {
    return linearVelocity;
  }

  public void setLinearVelocity(LinearVelocity linearVelocity) {
    this.linearVelocity = linearVelocity;
  }

  @Override
  protected int numberOfDimensions() {
    return 0;
  }
}
