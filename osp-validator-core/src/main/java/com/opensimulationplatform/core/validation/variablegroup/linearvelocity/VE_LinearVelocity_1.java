package com.opensimulationplatform.core.validation.variablegroup.linearvelocity;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_LinearVelocity_1;

public class VE_LinearVelocity_1 extends ValidationError<LinearVelocity> {
  @Override
  protected List<LinearVelocity> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_LinearVelocity_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (LinearVelocity) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(LinearVelocity linearVelocity) {
    return "LinearVelocity variable group '" + linearVelocity.getName().getId().get() + "' contains variables with mixed causalities";
  }
}
