package com.opensimulationplatform.core.validation.variablegroup.linearmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_LinearMechanicalQuasiPort_1;

public class VE_LinearMechanicalQuasiPort_1 extends ValidationError<LinearMechanicalQuasiPort> {
  @Override
  protected List<LinearMechanicalQuasiPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_LinearMechanicalQuasiPort_1, context.owl.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (LinearMechanicalQuasiPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(LinearMechanicalQuasiPort linearMechanicalQuasiPort) {
     return "LinearMechanicalQuasiPort '" + linearMechanicalQuasiPort.getName().getId().get() + "' contains incompatible Force(" +
             linearMechanicalQuasiPort.getForce().getName().getId().get() + ") and LinearDisplacement(" +
             linearMechanicalQuasiPort.getLinearDisplacement().getName().getId().get() + "). " +
             "The variables in the Force variable group must have opposite causality of the variables in the LinearDisplacement variable group";
  }
}
