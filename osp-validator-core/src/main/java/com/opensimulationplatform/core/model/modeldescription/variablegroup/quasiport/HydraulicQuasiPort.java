package com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.Volume;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Pressure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HydraulicQuasiPort extends VariableGroup {

  private Pressure pressure;
  private Volume volume;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(pressure, volume);
  }

  public void setPressure(Pressure pressure) {
    this.pressure = pressure;
  }

  public void setVolume(Volume volume) {
    this.volume = volume;
  }

  public Pressure getPressure() {
    return pressure;
  }

  public Volume getVolume() {
    return volume;
  }
}
