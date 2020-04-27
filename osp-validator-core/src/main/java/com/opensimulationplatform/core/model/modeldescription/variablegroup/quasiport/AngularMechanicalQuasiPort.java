package com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Torque;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AngularMechanicalQuasiPort extends VariableGroup {

  private Torque torque;
  private AngularDisplacement angularDisplacement;

  public void setTorque(Torque torque) {
    this.torque = torque;
  }

  public void setAngularDisplacement(AngularDisplacement angularDisplacement) {
    this.angularDisplacement = angularDisplacement;
  }

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(torque, angularDisplacement);
  }
}
