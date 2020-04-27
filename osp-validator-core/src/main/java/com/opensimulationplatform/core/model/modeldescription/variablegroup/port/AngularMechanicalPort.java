package com.opensimulationplatform.core.model.modeldescription.variablegroup.port;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.AngularVelocity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AngularMechanicalPort extends VariableGroup {
  private Torque torque;
  private AngularVelocity angularVelocity;

  public void setTorque(Torque torque) {
    this.torque = torque;
  }

  public void setAngularVelocity(AngularVelocity angularVelocity) {
    this.angularVelocity = angularVelocity;
  }

  public Torque getTorque() {
    return torque;
  }

  public AngularVelocity getAngularVelocity() {
    return angularVelocity;
  }

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(torque, angularVelocity);
  }
}
