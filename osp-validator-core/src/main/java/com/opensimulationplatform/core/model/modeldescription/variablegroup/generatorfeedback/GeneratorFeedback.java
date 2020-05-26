package com.opensimulationplatform.core.model.modeldescription.variablegroup.generatorfeedback;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electricpower.ElectricPower;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedsizevariablegroup.FixedSizeVariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.frequency.Frequency;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;

import java.util.Arrays;
import java.util.List;

public class GeneratorFeedback extends FixedSizeVariableGroup {

  private ElectricPower electricPower;
  private Voltage voltage;
  private Frequency frequency;

  @Override
  protected int size() {
    return 0;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    return Arrays.asList(electricPower, voltage, frequency);
  }

  public ElectricPower getElectricPower() {
    return electricPower;
  }

  public void setElectricPower(ElectricPower electricPower) {
    this.electricPower = electricPower;
  }

  public Voltage getVoltage() {
    return voltage;
  }

  public void setVoltage(Voltage voltage) {
    this.voltage = voltage;
  }

  public Frequency getFrequency() {
    return frequency;
  }

  public void setFrequency(Frequency frequency) {
    this.frequency = frequency;
  }
}
