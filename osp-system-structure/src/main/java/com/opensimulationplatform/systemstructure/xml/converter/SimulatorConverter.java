package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.modeldescription.xml.factory.ModelDescriptionFactory;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class SimulatorConverter extends Converter<Simulators.Simulator, Simulator> {

  public SimulatorConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Simulator convert(Simulators.Simulator simulatorElement) {
    Simulator simulator = new Simulator();

    String name = simulatorElement.getName();
    String source = simulatorElement.getSource();
    ModelDescription modelDescription = getModelDescription(source);

    simulator.setName(name);
    simulator.setModelDescription(modelDescription);

    return simulator;
  }

  private ModelDescription getModelDescription(String source) {
    File fmu = getFmu(source);
    File ospModelDescriptionFile = getOspModelDescriptionFile(fmu);
    return ModelDescriptionFactory.create(ospModelDescriptionFile, fmu);
  }

  private File getFmu(String source) {
    return new File(source);
  }

  private File getOspModelDescriptionFile(File fmu) {
    return new File(fmu.getParent(), fmu.getName().replaceAll(".fmu", "") + "_OspModelDescription.xml");
  }

  @Override
  public List<Simulator> convert(List<Simulators.Simulator> simulators) {
    return simulators.stream().map(this::convert).collect(Collectors.toList());
  }
}
