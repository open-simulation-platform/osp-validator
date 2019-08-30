package com.opensimulationplatform.modeldescription.json.validator.validations;

import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.owl.util.ontologycontent.OntologyContent;
import com.opensimulationplatform.core.owl.util.ontologyparser.OntologyParser;
import com.opensimulationplatform.core.util.resource.Resources;

public class AllSocketTypesDefinedInOntology implements Validation {
  @Override
  public Result validate(OspModelDescription ospModelDescription) {
    OntologyContent ontologyContent = OntologyParser.parse(Resources.OSP_OWL.toFile());
    return new Result(ospModelDescription.getOspSockets().stream().allMatch(socket -> ontologyContent.getClasses().containsKey(socket.getType())));
  }
  
  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
}
