package com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup.NDimensionalVariableGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AngularMechanicalQuasiPort extends NDimensionalVariableGroup {

  private Torque torque;
  private AngularDisplacement angularDisplacement;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(torque, angularDisplacement));
  }

  public Torque getTorque() {
    return torque;
  }

  public void setTorque(Torque torque) {
    this.torque = torque;
  }

  public AngularDisplacement getAngularDisplacement() {
    return angularDisplacement;
  }

  public void setAngularDisplacement(AngularDisplacement angularDisplacement) {
    this.angularDisplacement = angularDisplacement;
  }

  @Override
  protected int numberOfDimensions() {
    return 0;
  }
}
