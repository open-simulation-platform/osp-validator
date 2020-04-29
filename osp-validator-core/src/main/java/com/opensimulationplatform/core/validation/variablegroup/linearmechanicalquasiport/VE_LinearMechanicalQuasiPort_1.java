package com.opensimulationplatform.core.validation.variablegroup.linearmechanicalquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
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
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_LinearMechanicalQuasiPort_1, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (LinearMechanicalQuasiPort) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(LinearMechanicalQuasiPort linearMechanicalQuasiPort) {
     return "LinearMechanicalQuasiPort '" + linearMechanicalQuasiPort.getName().get() + "' contains incompatible Force(" +
             linearMechanicalQuasiPort.getForce().getName().get() + ") and LinearDisplacement(" +
             linearMechanicalQuasiPort.getLinearDisplacement().getName().get() + "). " +
             "The variables in the Force variable group must have opposite causality of the variables in the LinearDisplacement variable group";
  }
}
