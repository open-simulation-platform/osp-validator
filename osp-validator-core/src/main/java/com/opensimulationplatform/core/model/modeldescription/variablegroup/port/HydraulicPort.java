package com.opensimulationplatform.core.model.modeldescription.variablegroup.port;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.VolumeFlowRate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HydraulicPort extends VariableGroup {

  private Pressure pressure;
  private VolumeFlowRate volumeFlowRate;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(pressure, volumeFlowRate);
  }

  public void setPressure(Pressure pressure) {
    this.pressure = pressure;
  }

  public void setVolumeFlowRate(VolumeFlowRate volumeFlowRate) {
    this.volumeFlowRate = volumeFlowRate;
  }

  public Pressure getPressure() {
    return pressure;
  }

  public VolumeFlowRate getVolumeFlowRate() {
    return volumeFlowRate;
  }
}
