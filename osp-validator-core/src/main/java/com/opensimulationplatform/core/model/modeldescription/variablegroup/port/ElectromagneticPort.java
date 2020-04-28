package com.opensimulationplatform.core.model.modeldescription.variablegroup.port;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Voltage;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.Current;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup.NDimensionalVariableGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ElectromagneticPort extends NDimensionalVariableGroup {

  private Voltage voltage;
  private Current current;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(voltage, current));
  }

  public Voltage getVoltage() {
    return voltage;
  }

  public void setVoltage(Voltage voltage) {
    this.voltage = voltage;
  }

  public Current getCurrent() {
    return current;
  }

  public void setCurrent(Current current) {
    this.current = current;
  }

  @Override
  protected int numberOfDimensions() {
    return 0;
  }
}
