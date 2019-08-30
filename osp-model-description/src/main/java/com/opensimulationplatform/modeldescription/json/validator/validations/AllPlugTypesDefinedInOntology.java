package com.opensimulationplatform.modeldescription.json.validator.validations;

import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.owl.util.ontologycontent.OntologyContent;
import com.opensimulationplatform.core.owl.util.ontologyparser.OntologyParser;
import com.opensimulationplatform.core.util.resource.Resources;

import java.util.ArrayList;
import java.util.List;

public class AllPlugTypesDefinedInOntology implements Validation {
  @Override
  public Result validate(OspModelDescription ospModelDescription) {
    OntologyContent ontologyContent = OntologyParser.parse(Resources.OSP_OWL.toFile());
    List<String> messages = new ArrayList<>();
    boolean valid = ospModelDescription.getOspPlugs().stream().allMatch(plug -> {
      if (ontologyContent.getClasses().containsKey(plug.getType())) {
        return true;
      } else {
        messages.add("Plug '" + plug.getName() + "' of type '" + plug.getType() + "' does not exist in the ontology");
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
