package com.opensimulationplatform.core.validation.variablegroup.linearmechanicalport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalport.LinearMechanicalPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_LinearMechanicalPort_1;

public class VE_LinearMechanicalPort_1 extends ValidationError<LinearMechanicalPort> {
  @Override
  protected List<LinearMechanicalPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_LinearMechanicalPort_1, context.owl.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (LinearMechanicalPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(LinearMechanicalPort linearMechanicalPort) {
     return "LinearMechanicalPort '" + linearMechanicalPort.getName().getId().get() + "' contains incompatible Force(" +
             linearMechanicalPort.getForce().getName().getId().get() + ") and LinearVelocity(" +
             linearMechanicalPort.getLinearVelocity().getName().getId().get() + "). " +
             "The variables in the Force variable group must have opposite causality of the variables in the LinearVelocity variable group";
  }
}
