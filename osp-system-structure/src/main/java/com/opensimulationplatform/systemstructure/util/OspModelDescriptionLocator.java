package com.opensimulationplatform.systemstructure.util;

import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.io.File;

public interface OspModelDescriptionLocator {
  File get(Simulators.Simulator simulator);
}
