package com.opensimulationplatform.systemstructure.util;

import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.net.URI;

public interface FmuLocator {
  URI get(Simulators.Simulator simulator);
}
