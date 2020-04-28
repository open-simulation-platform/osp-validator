package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.systemstructure.util.FmuLocator;
import com.opensimulationplatform.systemstructure.util.OspModelDescriptionLocator;

public class ConverterContext {

  public SystemStructure systemStructure;

  public FmuLocator fmuLocator;
  public OspModelDescriptionLocator ospModelDescriptionLocator;

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
