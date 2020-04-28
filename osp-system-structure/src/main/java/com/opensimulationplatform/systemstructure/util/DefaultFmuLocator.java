package com.opensimulationplatform.systemstructure.util;

import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.net.URI;
import java.net.URISyntaxException;

public class DefaultFmuLocator implements FmuLocator {
  @Override
  public URI get(Simulators.Simulator simulator) {
    try {
      return new URI(simulator.getSource());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
