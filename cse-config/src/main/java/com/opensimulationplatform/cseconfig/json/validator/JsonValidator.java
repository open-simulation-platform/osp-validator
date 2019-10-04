package com.opensimulationplatform.cseconfig.json.validator;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.validator.systemstructure.SystemStructureValidator;
import com.opensimulationplatform.cseconfig.json.converter.SystemStructureConverter;
import com.opensimulationplatform.cseconfig.json.model.OspSystemStructure;
import com.opensimulationplatform.cseconfig.json.model.Simulator;
import com.opensimulationplatform.cseconfig.json.parser.OspSystemStructureParser;
import com.opensimulationplatform.modeldescription.json.converter.OspModelDescriptionConverter;
import com.opensimulationplatform.modeldescription.json.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.json.parser.OspModelDescriptionParser;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class JsonValidator {
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
    HashMap<Simulator, ModelDescription> modelDescriptions = getSimulatorModelDescriptionMap(ospSystemStructure);
    return SystemStructureConverter.convert(ospSystemStructure, modelDescriptions);
  }
  
  private static HashMap<Simulator, ModelDescription> getSimulatorModelDescriptionMap(OspSystemStructure ospSystemStructure) {
    List<Simulator> simulators = ospSystemStructure.getSimulators();
    HashMap<Simulator, ModelDescription> modelDescriptions = new HashMap<>();
    for (Simulator simulator : simulators) {
      OspModelDescription ospModelDescription = OspModelDescriptionParser.parse(new File(simulator.getModelDefinition()));
      ModelDescription modelDescription = OspModelDescriptionConverter.convert(ospModelDescription);
      modelDescriptions.put(simulator, modelDescription);
    }
    return modelDescriptions;
  }
}
