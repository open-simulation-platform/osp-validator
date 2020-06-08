/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.systemstructure.util;

import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

public class DefaultFmuLocator implements FmuLocator {
  private final File ospSystemStructureFile;

  public DefaultFmuLocator(File ospSystemStructureFile) {
    this.ospSystemStructureFile = ospSystemStructureFile;
  }

  @Override
  public URI get(Simulators.Simulator simulator) {
    try {
      URI sourceURI = new URI(simulator.getSource());
      if (sourceURI.isAbsolute()) {
        return sourceURI;
      } else {
        URI baseURI = ospSystemStructureFile.toURI();
        return baseURI.resolve(sourceURI);
      }
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
