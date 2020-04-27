package com.opensimulationplatform.core.validation.variablegroup.flow.linearvelocity;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.model.OntologyClasses.VE_LinearVelocity_2;

public class VE_LinearVelocity_2 extends ValidationError<LinearVelocity> {
  @Override
  protected List<LinearVelocity> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_LinearVelocity_2, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (LinearVelocity) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(LinearVelocity linearVelocity) {
    return "LinearVelocity variable group '" + linearVelocity.getName().get() + "' contains variables with mixed data types";
  }
}
