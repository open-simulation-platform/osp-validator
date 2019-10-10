package com.opensimulationplatform.core.validator.modeldescription.validations;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.owl.util.ontologycontent.OntologyContent;
import com.opensimulationplatform.core.owl.util.ontologyparser.OntologyParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AllSocketTypesDefinedInOntology implements Validation {
  @Override
  public Result validate(ModelDescription modelDescription, File ospOwlFile) {
    OntologyContent ontologyContent = OntologyParser.parse(ospOwlFile);
    List<String> messages = new ArrayList<>();
    boolean valid = modelDescription.getOspSockets().values().stream().allMatch(socket -> {
      if (ontologyContent.getClasses().containsKey(socket.getType())) {
        return true;
      } else {
        messages.add("Socket '" + socket.getName() + "' of type '" + socket.getType() + "' does not exist in the ontology");
        return false;
      }
    });
    
    return new Result(valid, messages);
  }
  
  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
}
