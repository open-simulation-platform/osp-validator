package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.modeldescription.xml.factory.ModelDescriptionFactory;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class SimulatorConverter extends Converter<Simulators.Simulator, Simulator> {

  public SimulatorConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Simulator convert(Simulators.Simulator simulatorElement) {
    Simulator simulator = new Simulator();

    simulator.setName(simulatorElement.getName());
    simulator.setModelDescription(getModelDescription(simulatorElement));

    return simulator;
  }

  private ModelDescription getModelDescription(Simulators.Simulator simulatorElement) {
    File ospModelDescriptionFile = context.ospModelDescriptionLocator.get(simulatorElement);
    URI fmu = context.fmuLocator.get(simulatorElement);
    ModelDescriptionFactory factory = new ModelDescriptionFactory();
    return factory.create(ospModelDescriptionFile, fmu);
  }

  @Override
  public List<Simulator> convert(List<Simulators.Simulator> simulators) {
    return simulators.stream().map(this::convert).collect(Collectors.toList());
  }
}