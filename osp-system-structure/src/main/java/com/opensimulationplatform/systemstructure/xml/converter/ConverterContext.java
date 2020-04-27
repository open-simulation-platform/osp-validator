package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;

public class ConverterContext {

  public SystemStructure systemStructure;
  public SimulatorConverter simulatorConverter;
  public VariableConnectionConverter variableConnectionConverter;
  public VariableGroupConnectionConverter variableGroupConnectionConverter;

  public ConverterContext() {
    this.systemStructure = new SystemStructure();

    this.simulatorConverter = new SimulatorConverter(this);
    this.variableConnectionConverter = new VariableConnectionConverter(this);
    this.variableGroupConnectionConverter = new VariableGroupConnectionConverter(this);
  }
}
