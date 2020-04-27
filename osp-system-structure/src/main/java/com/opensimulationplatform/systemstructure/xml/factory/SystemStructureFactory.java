package com.opensimulationplatform.systemstructure.xml.factory;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.systemstructure.xml.converter.OspSystemStructureConverter;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.parser.OspSystemStructureParser;

import java.io.File;

public class SystemStructureFactory {
  public static SystemStructure create(File ospSystemStructureFile) {
    OspSystemStructure ospSystemStructure = OspSystemStructureParser.parse(ospSystemStructureFile);
    OspSystemStructureConverter ospSystemStructureConverter = new OspSystemStructureConverter();
    return ospSystemStructureConverter.convert(ospSystemStructure);
  }
}
