package com.opensimulationplatform.core.validation.variablegroup.angularmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_AngularMechanicalQuasiPort_1;

public class VE_AngularMechanicalQuasiPort_1 extends ValidationError<AngularMechanicalQuasiPort> {
  @Override
  protected List<AngularMechanicalQuasiPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_AngularMechanicalQuasiPort_1, context.owl.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (AngularMechanicalQuasiPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(AngularMechanicalQuasiPort angularMechanicalQuasiPort) {
     return "AngularMechanicalQuasiPort '" + angularMechanicalQuasiPort.getName().get() + "' contains incompatible Torque(" +
             angularMechanicalQuasiPort.getTorque().getName().get() + ") and AngularDisplacement(" +
             angularMechanicalQuasiPort.getAngularDisplacement().getName().get() + "). " +
             "The variables in the Torque variable group must have opposite causality of the variables in the AngularDisplacement variable group";
  }
}
