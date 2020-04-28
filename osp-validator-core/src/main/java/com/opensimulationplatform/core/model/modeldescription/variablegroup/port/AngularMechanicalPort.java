package com.opensimulationplatform.core.model.modeldescription.variablegroup.port;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.AngularVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup.NDimensionalVariableGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AngularMechanicalPort extends NDimensionalVariableGroup {
  private Torque torque;
  private AngularVelocity angularVelocity;

  public Torque getTorque() {
    return torque;
  }

  public void setTorque(Torque torque) {
    this.torque = torque;
  }

  public AngularVelocity getAngularVelocity() {
    return angularVelocity;
  }

  public void setAngularVelocity(AngularVelocity angularVelocity) {
    this.angularVelocity = angularVelocity;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(torque, angularVelocity));
  }

  @Override
  protected int numberOfDimensions() {
    return 0;
  }
}
