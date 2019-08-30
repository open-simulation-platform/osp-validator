package com.opensimulationplatform.modeldescription.json.validator;

import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.modeldescription.json.validator.validations.*;

import java.util.ArrayList;
import java.util.List;


public class OspModelDescriptionValidator {
  private static final List<Validation> validations = new ArrayList<>();
  
  static {
    validations.add(new AllPlugTypesDefinedInOntology());
    validations.add(new AllSocketTypesDefinedInOntology());
    validations.add(new AllPlugsAndSocketsInBondsAreDefinedInOspModelDescription());
    validations.add(new AllPlugsHaveUniqueNames());
    validations.add(new AllSocketsHaveUniqueNames());
    validations.add(new AllBondsHaveUniqueNames());
    validations.add(new AllVariablesInAllPlugsExistInFmiModelDescription());
    validations.add(new AllVariablesInAllSocketsExistInFmiModelDescription());
  }
  
  public static Result validate(OspModelDescription modelDescription) {
    List<String> messages = new ArrayList<>();
    boolean isValid = true;
    for (Validation validation : validations) {
      Validation.Result validationResult = validation.validate(modelDescription);
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
