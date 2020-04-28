package com.opensimulationplatform.systemstructure.util;

import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;

public class DefaultOspModelDescriptionLocator implements OspModelDescriptionLocator {

  private final File ospSystemStructureFile;
  private final FmuLocator fmuLocator;

  public DefaultOspModelDescriptionLocator(File ospSystemStructureFile, FmuLocator fmuLocator) {
    this.ospSystemStructureFile = ospSystemStructureFile;
    this.fmuLocator = fmuLocator;
  }

  @Override
  public File get(Simulators.Simulator simulator) {
    try {
      URI uri = fmuLocator.get(simulator);
      if ("fmuproxy".equals(uri.getScheme())) {
        String fmuName = new File(uri.getPath()).getName().replaceAll(".fmu", "");

        File parallelToOspSystemStructure = new File(ospSystemStructureFile.getParent(), fmuName + "_OspModelDescription.xml");
        if (parallelToOspSystemStructure.exists()) {
          return parallelToOspSystemStructure;
        }

        throw new FileNotFoundException("Could not find OspModelDescription.xml. Tried " + parallelToOspSystemStructure.getAbsolutePath());
      } else if ("file".equals(uri.getScheme())) {
        File fmu = new File(uri);
        String fmuName = fmu.getName().replaceAll(".fmu", "");

        File parallelToFmu = new File(fmu.getParent(), fmuName + "_OspModelDescription.xml");
        if (parallelToFmu.exists()) {
          return parallelToFmu;
        }

        File parallelToOspSystemStructure = new File(ospSystemStructureFile.getParent(), fmuName + "_OspModelDescription.xml");
        if (parallelToOspSystemStructure.exists()) {
          return parallelToOspSystemStructure;
        }

        throw new FileNotFoundException("Could not find OspModelDescription.xml. Tried " + parallelToFmu.getAbsolutePath() + " and " + parallelToOspSystemStructure.getAbsolutePath());
      } else {
        throw new RuntimeException("Unsupported URI schema: '" + uri.getScheme() + "' in URI: '" + uri + "' . Supported schemas are: [file, fmuproxy]");
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
