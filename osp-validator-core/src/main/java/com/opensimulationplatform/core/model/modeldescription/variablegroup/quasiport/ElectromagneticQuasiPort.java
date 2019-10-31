package com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.Charge;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Voltage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElectromagneticQuasiPort extends VariableGroup {

  private Voltage voltage;
  private Charge charge;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(voltage, charge);
  }

  public void setVoltage(Voltage voltage) {
    this.voltage = voltage;
  }

  public void setCharge(Charge charge) {
    this.charge = charge;
  }
}
