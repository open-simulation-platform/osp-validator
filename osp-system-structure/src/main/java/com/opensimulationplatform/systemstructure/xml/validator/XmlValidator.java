package com.opensimulationplatform.systemstructure.xml.validator;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.validator.systemstructure.SystemStructureValidator;
import com.opensimulationplatform.modeldescription.xml.converter.OspModelDescriptionConverter;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.xml.parser.OspModelDescriptionParser;
import com.opensimulationplatform.systemstructure.xml.converter.SystemStructureConverter;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;
import com.opensimulationplatform.systemstructure.xml.parser.OspSystemStructureParser;

import java.io.File;
import java.util.HashMap;

public class XmlValidator {
  public static SystemStructureValidator.Result validate(File ospSystemStructureFile, File ospOwlFile) {
    SystemStructure systemStructure = getSystemStructure(ospSystemStructureFile);
    
    return SystemStructureValidator.validate(systemStructure, ospOwlFile);
  }
  
  public static SystemStructureValidator.Result validate(File ospSystemStructureFile) {
    SystemStructure systemStructure = getSystemStructure(ospSystemStructureFile);
    
    return SystemStructureValidator.validate(systemStructure);
  }
  
  private static SystemStructure getSystemStructure(File ospSystemStructureFile) {
    OspSystemStructure ospSystemStructure = OspSystemStructureParser.parse(ospSystemStructureFile);
    HashMap<Simulators.Simulator, ModelDescription> modelDescriptions = getSimulatorModelDescriptionMap(ospSystemStructure);
  
    return SystemStructureConverter.convert(ospSystemStructure, modelDescriptions);
  }
  
  private static HashMap<Simulators.Simulator, ModelDescription> getSimulatorModelDescriptionMap(OspSystemStructure ospSystemStructure) {
    HashMap<Simulators.Simulator, ModelDescription> modelDescriptions = new HashMap<>();
    ospSystemStructure.getSimulators().getSimulator().forEach(s -> {
      File fmuFile = new File(s.getSource());
      String fmuName = fmuFile.getName().replace(".fmu", "");
      File ospModelDescriptionFile = new File(fmuFile.getParent(), fmuName + "_OspModelDescription.xml");
      OspModelDescription ospModelDescription = OspModelDescriptionParser.parse(ospModelDescriptionFile);
      ModelDescription modelDescription = OspModelDescriptionConverter.convert(ospModelDescription);
      modelDescriptions.put(s, modelDescription);
    });
    return modelDescriptions;
  }
}
