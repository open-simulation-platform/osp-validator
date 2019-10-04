package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.validator.modeldescription.ModelDescriptionValidator;
import com.opensimulationplatform.modeldescription.json.converter.OspModelDescriptionConverter;
import com.opensimulationplatform.modeldescription.json.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.json.parser.OspModelDescriptionParser;
import com.opensimulationplatform.modeldescription.util.FmuHelper;

import java.io.File;

public class JsonValidator {
  public static ModelDescriptionValidator.Result validate(File ospModelDescriptionFile, File fmu, File ospOwlFile) {
    ModelDescription modelDescription = getModelDescription(ospModelDescriptionFile, fmu);
    return ModelDescriptionValidator.validate(modelDescription, ospOwlFile);
  }
  
  public static ModelDescriptionValidator.Result validate(File ospModelDescriptionFile, File fmu) {
    ModelDescription modelDescription = getModelDescription(ospModelDescriptionFile, fmu);
    return ModelDescriptionValidator.validate(modelDescription);
  }
  
  private static ModelDescription getModelDescription(File ospModelDescriptionFile, File fmu) {
    OspModelDescription ospModelDescription = OspModelDescriptionParser.parse(ospModelDescriptionFile);
    ModelDescription modelDescription = OspModelDescriptionConverter.convert(ospModelDescription);
    modelDescription.setFmiModelDescription(FmuHelper.getFmiModelDescription(fmu));
    return modelDescription;
  }
}
