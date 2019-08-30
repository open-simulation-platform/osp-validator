package com.opensimulationplatform.modeldescription.json.validator.validations;

import com.opensimulationplatform.core.model.modeldescription.OspModelDescription;
import com.opensimulationplatform.core.owl.util.ontologycontent.OntologyContent;
import com.opensimulationplatform.core.owl.util.ontologyparser.OntologyParser;
import com.opensimulationplatform.core.util.resource.Resources;

public class AllPlugTypesDefinedInOntology implements Validation {
  @Override
  public Result validate(OspModelDescription ospModelDescription) {
    OntologyContent ontologyContent = OntologyParser.parse(Resources.OSP_OWL.toFile());
    return new Result(ospModelDescription.getOspPlugs().stream().allMatch(plug -> ontologyContent.getClasses().containsKey(plug.getType())));
  }
  
  @Override
  public String getName() {
    return this.getClass().getSimpleName();
  }
}
