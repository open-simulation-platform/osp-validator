package com.opensimulationplatform.core.validation.variablegroup.angularmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
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
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_AngularMechanicalQuasiPort_1, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (AngularMechanicalQuasiPort) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(AngularMechanicalQuasiPort angularMechanicalQuasiPort) {
     return "AngularMechanicalQuasiPort '" + angularMechanicalQuasiPort.getName().get() + "' contains incompatible Torque(" +
             angularMechanicalQuasiPort.getTorque().getName().get() + ") and AngularDisplacement(" +
             angularMechanicalQuasiPort.getAngularDisplacement().getName().get() + "). " +
             "The variables in the Torque variable group must have opposite causality of the variables in the AngularDisplacement variable group";
  }
}
