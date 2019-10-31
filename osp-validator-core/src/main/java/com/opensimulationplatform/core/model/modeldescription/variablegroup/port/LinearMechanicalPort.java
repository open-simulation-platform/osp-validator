package com.opensimulationplatform.core.model.modeldescription.variablegroup.port;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LinearMechanicalPort extends VariableGroup {

  private Force force;
  private LinearVelocity linearVelocity;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(force, linearVelocity);
  }

  public void setForce(Force force) {
    this.force = force;
  }

  public void setLinearVelocity(LinearVelocity linearVelocity) {
    this.linearVelocity = linearVelocity;
  }

  public Force getForce() {
    return force;
  }

  public LinearVelocity getLinearVelocity() {
    return linearVelocity;
  }
}
