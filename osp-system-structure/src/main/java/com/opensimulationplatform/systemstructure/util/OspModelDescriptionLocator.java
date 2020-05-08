package com.opensimulationplatform.systemstructure.util;

import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.io.File;
import java.util.Optional;

public interface OspModelDescriptionLocator {
  Optional<File> get(Simulators.Simulator simulator);
}
