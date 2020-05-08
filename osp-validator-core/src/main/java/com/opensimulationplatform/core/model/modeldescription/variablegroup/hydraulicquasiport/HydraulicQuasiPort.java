package com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicquasiport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup.NDimensionalVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volume.Volume;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HydraulicQuasiPort extends NDimensionalVariableGroup {

  private Pressure pressure;
  private Volume volume;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(pressure, volume));
  }

  public Pressure getPressure() {
    return pressure;
  }

  public void setPressure(Pressure pressure) {
    this.pressure = pressure;
  }

  public Volume getVolume() {
    return volume;
  }

  public void setVolume(Volume volume) {
    this.volume = volume;
  }

  @Override
  protected int numberOfDimensions() {
    return 0;
  }
}
