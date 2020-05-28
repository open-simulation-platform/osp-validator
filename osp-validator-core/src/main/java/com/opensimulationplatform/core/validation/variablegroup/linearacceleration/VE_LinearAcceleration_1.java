package com.opensimulationplatform.core.validation.variablegroup.linearacceleration;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearacceleration.LinearAcceleration;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_LinearAcceleration_1;

public class VE_LinearAcceleration_1 extends ValidationError<LinearAcceleration> {
  @Override
  protected List<LinearAcceleration> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_LinearAcceleration_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (LinearAcceleration) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(LinearAcceleration linearAcceleration) {
    return "LinearAcceleration variable group '" + linearAcceleration.getName().getId().get() + "' contains variables with mixed causalities";
  }
}
