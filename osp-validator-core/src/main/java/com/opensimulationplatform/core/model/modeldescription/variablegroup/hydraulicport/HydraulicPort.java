/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.ndimensionsalvariablegroup.NDimensionalVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HydraulicPort extends NDimensionalVariableGroup {

  private Pressure pressure;
  private VolumeFlowRate volumeFlowRate;

  @Override
  public List<Variable> getVariables() {
    return Collections.emptyList();
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(Arrays.asList(pressure, volumeFlowRate));
  }

  public Pressure getPressure() {
    return pressure;
  }

  public void setPressure(Pressure pressure) {
    this.pressure = pressure;
  }

  public VolumeFlowRate getVolumeFlowRate() {
    return volumeFlowRate;
  }

  public void setVolumeFlowRate(VolumeFlowRate volumeFlowRate) {
    this.volumeFlowRate = volumeFlowRate;
  }

  @Override
  protected int numberOfDimensions() {
    return 0;
  }
}
