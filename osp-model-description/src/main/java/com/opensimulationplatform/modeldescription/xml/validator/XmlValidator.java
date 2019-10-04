package com.opensimulationplatform.modeldescription.xml.validator;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.util.resource.Resources;
import com.opensimulationplatform.core.validator.modeldescription.ModelDescriptionValidator;
import com.opensimulationplatform.modeldescription.util.FmuHelper;
import com.opensimulationplatform.modeldescription.xml.converter.OspModelDescriptionConverter;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescription;
import com.opensimulationplatform.modeldescription.xml.parser.OspModelDescriptionParser;

import java.io.File;

public class XmlValidator {
  public static ModelDescriptionValidator.Result validate(File ospModelDescriptionFile, File fmu, File ospOwlFile) {
    ModelDescription modelDescription = getModelDescription(ospModelDescriptionFile, fmu);
    return ModelDescriptionValidator.validate(modelDescription, ospOwlFile);
    
  }
  
  public static ModelDescriptionValidator.Result validate(File ospModelDescriptionFile, File fmu) {
    ModelDescription modelDescription = getModelDescription(ospModelDescriptionFile, fmu);
    return ModelDescriptionValidator.validate(modelDescription, Resources.OSP_OWL.toFile());
  }
  
  private static ModelDescription getModelDescription(File modelDescriptionFile, File fmu) {
    OspModelDescription ospModelDescription = OspModelDescriptionParser.parse(modelDescriptionFile);
    ModelDescription modelDescription = OspModelDescriptionConverter.convert(ospModelDescription);
    modelDescription.setFmiModelDescription(FmuHelper.getFmiModelDescription(fmu));
    return modelDescription;
  }
}
