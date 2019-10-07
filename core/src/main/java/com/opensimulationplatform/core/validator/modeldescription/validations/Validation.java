package com.opensimulationplatform.core.validator.modeldescription.validations;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;

import java.io.File;
import java.util.Collections;
import java.util.List;

public interface Validation {
  Result validate(ModelDescription modelDescription, File ospOwlFile);
  String getName();
  
  class Result {
    private final List<String> messages;
    private final boolean valid;
  
    public Result(boolean valid) {
      this.messages = Collections.emptyList();
      this.valid = valid;
    }
  
    public Result(boolean valid, List<String> messages) {
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
