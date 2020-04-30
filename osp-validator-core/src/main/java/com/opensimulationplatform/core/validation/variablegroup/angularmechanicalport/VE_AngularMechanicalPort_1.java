package com.opensimulationplatform.core.validation.variablegroup.angularmechanicalport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalport.AngularMechanicalPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_AngularMechanicalPort_1;

public class VE_AngularMechanicalPort_1 extends ValidationError<AngularMechanicalPort> {
  @Override
  protected List<AngularMechanicalPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_AngularMechanicalPort_1, context.owl.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (AngularMechanicalPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(AngularMechanicalPort angularMechanicalPort) {
     return "AngularMechanicalPort '" + angularMechanicalPort.getName().get() + "' contains incompatible Torque(" +
             angularMechanicalPort.getTorque().getName().get() + ") and AngularVelocity(" +
             angularMechanicalPort.getAngularVelocity().getName().get() + "). " +
             "The variables in the Torque variable group must have opposite causality of the variables in the AngularVelocity variable group";
  }
}
