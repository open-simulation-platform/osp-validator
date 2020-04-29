package com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup.NDimensionalVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElectromagneticQuasiPort extends NDimensionalVariableGroup {

  private Voltage voltage;
  private Charge charge;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(voltage, charge));
  }

  public Voltage getVoltage() {
    return voltage;
  }

  public void setVoltage(Voltage voltage) {
    this.voltage = voltage;
  }

  public Charge getCharge() {
    return charge;
  }

  public void setCharge(Charge charge) {
    this.charge = charge;
  }

  @Override
  protected int numberOfDimensions() {
    return 0;
  }
}
