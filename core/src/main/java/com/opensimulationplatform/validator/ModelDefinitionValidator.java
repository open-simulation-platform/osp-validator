package com.opensimulationplatform.validator;

import com.opensimulationplatform.util.resource.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ModelDefinitionValidator {
  
  private static final Logger LOG = LoggerFactory.getLogger(ModelDefinitionValidator.class);
  
  public static ModelDefinitionValidator.Result validate(File ospOwlFile, File modelDefinitionFile) {
    //ModelDefinition modelDefinition = ModelDefinitionFatory.create(ModelDefinitionJsonFileParer.parse(modelDefinitionFile));
    return null;
  }
  
  public static Result validate(File modelDefinitionFile) {
    File ospOwlFile = ResourceUtil.writeResourceToTempFile("osp.owl");
    return validate(ospOwlFile, modelDefinitionFile);
  }
  
  static class Result {
    public boolean isSuccess() {
      return true;
    }
  }
}
