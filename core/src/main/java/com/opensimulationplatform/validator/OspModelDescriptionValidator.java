package com.opensimulationplatform.validator;

import com.opensimulationplatform.json.model.parsing.OspModelDescriptionJsonFileParser;
import com.opensimulationplatform.util.resource.Resources;
import com.opensimulationplatform.validator.model.ospmodeldescription.OspModelDescription;
import com.opensimulationplatform.validator.model.ospmodeldescription.OspModelDescriptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class OspModelDescriptionValidator {
  
  private static final Logger LOG = LoggerFactory.getLogger(OspModelDescriptionValidator.class);
  
  public static OspModelDescriptionValidator.Result validate(File ospOwlFile, File modelDefinitionFile) {
    OspModelDescription ospModelDescription = OspModelDescriptionFactory.create(OspModelDescriptionJsonFileParser.parse(modelDefinitionFile));
    
    return new Result();
  }
  
  public static Result validate(File modelDefinitionFile) {
    File ospOwlFile = Resources.OSP_OWL.toFile();
    return validate(ospOwlFile, modelDefinitionFile);
  }
  
  static class Result {
    public boolean isSuccess() {
      return false;
    }
  }
}
