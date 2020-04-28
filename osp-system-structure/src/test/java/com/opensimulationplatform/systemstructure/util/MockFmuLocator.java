package com.opensimulationplatform.systemstructure.util;

import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class MockFmuLocator implements FmuLocator {
  @Override
  public URI get(Simulators.Simulator simulator) {
    try {
      URI uri = new URI(simulator.getSource());
      if (uri.getScheme() == null) {
        return new File(uri.getPath()).getAbsoluteFile().toURI();
      } else {
        return uri;
      }
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
