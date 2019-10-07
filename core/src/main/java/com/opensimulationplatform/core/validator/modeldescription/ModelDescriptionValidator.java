package com.opensimulationplatform.core.validator.modeldescription;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.util.resource.Resources;
import com.opensimulationplatform.core.validator.modeldescription.validations.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ModelDescriptionValidator {
  private static final List<Validation> validations = new ArrayList<>();
  
  static {
    validations.add(new AllPlugTypesDefinedInOntology());
    validations.add(new AllSocketTypesDefinedInOntology());
    validations.add(new AllPlugsAndSocketsInBondsAreDefinedInOspModelDescription());
    validations.add(new AllVariablesInAllPlugsExistInFmiModelDescription());
    validations.add(new AllVariablesInAllSocketsExistInFmiModelDescription());
  }
  
  public static Result validate(ModelDescription modelDescription) {
    return validate(modelDescription, Resources.OSP_OWL.toFile());
  }
  
  public static Result validate(ModelDescription modelDescription, File ospOwlFile) {
    List<String> messages = new ArrayList<>();
    boolean isValid = true;
    for (Validation validation : validations) {
      Validation.Result validationResult = validation.validate(modelDescription, ospOwlFile);
      if (!validationResult.isValid()) {
        messages.add("Validation '" + validation.getName() + "' failed!");
        isValid = false;
      }
      messages.addAll(validationResult.getMessages());
    }
    return new Result(isValid, messages);
  
  }
  
  public static class Result {
    private final List<String> messages;
    private final boolean valid;
    
    Result(boolean valid, List<String> messages) {
      this.messages = messages;
      this.valid = valid;
    }
    
    public List<String> getMessages() {
      return messages;
    }
    
    public boolean isValid() {
      return valid;
    }
  }
}
