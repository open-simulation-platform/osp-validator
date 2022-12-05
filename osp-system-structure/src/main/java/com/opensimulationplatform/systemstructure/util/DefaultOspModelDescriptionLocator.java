/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.systemstructure.util;

import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.io.File;
import java.net.URI;
import java.util.Optional;

public class DefaultOspModelDescriptionLocator implements OspModelDescriptionLocator {

  private final File ospSystemStructureFile;
  private final FmuLocator fmuLocator;

  public DefaultOspModelDescriptionLocator(File ospSystemStructureFile, FmuLocator fmuLocator) {
    this.ospSystemStructureFile = ospSystemStructureFile;
    this.fmuLocator = fmuLocator;
  }

  @Override
  public Optional<File> get(Simulators.Simulator simulator) {
    try {
      URI uri = fmuLocator.get(simulator);
        if ("file".equals(uri.getScheme())) {
        File fmu = new File(uri);
        String fmuName = fmu.getName().replaceAll(".fmu", "");

        File parallelToFmu = new File(fmu.getParent(), fmuName + "_OspModelDescription.xml");
        if (parallelToFmu.exists()) {
          return Optional.of(parallelToFmu);
        }

        File parallelToOspSystemStructure = new File(ospSystemStructureFile.getParent(), fmuName + "_OspModelDescription.xml");
        if (parallelToOspSystemStructure.exists()) {
          return Optional.of(parallelToOspSystemStructure);
        }

        return Optional.empty();
      } else {
        throw new RuntimeException("Unsupported URI schema: '" + uri.getScheme() + "' in URI: '" + uri + "' . Supported schemas are: [file, proxyfmu]");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
