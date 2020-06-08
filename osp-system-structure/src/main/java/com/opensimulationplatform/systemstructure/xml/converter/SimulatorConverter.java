/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.modeldescription.xml.factory.ModelDescriptionFactory;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Optional;
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
    Optional<File> ospModelDescriptionFile = context.ospModelDescriptionLocator.get(simulatorElement);
    URI fmu = context.fmuLocator.get(simulatorElement);
    ModelDescriptionFactory factory = new ModelDescriptionFactory();
    if (ospModelDescriptionFile.isPresent()) {
      return factory.create(ospModelDescriptionFile.get(), fmu);
    } else {
      return factory.create(fmu);
    }
  }

  @Override
  public List<Simulator> convert(List<Simulators.Simulator> simulators) {
    return simulators.stream().map(this::convert).collect(Collectors.toList());
  }
}