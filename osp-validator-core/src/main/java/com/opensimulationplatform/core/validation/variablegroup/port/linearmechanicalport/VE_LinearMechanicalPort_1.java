package com.opensimulationplatform.core.validation.variablegroup.port.linearmechanicalport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.LinearMechanicalPort;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.model.OntologyClasses.VE_LinearMechanicalPort_1;

public class VE_LinearMechanicalPort_1 extends ValidationError<LinearMechanicalPort> {
  @Override
  protected List<LinearMechanicalPort> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_LinearMechanicalPort_1, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (LinearMechanicalPort) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(LinearMechanicalPort linearMechanicalPort) {
    return "Linear mechanical port '" + linearMechanicalPort.getName().get() + "' contains incompatible Force(" +
        linearMechanicalPort.getForce().getName().get() + ") and Linear velocity(" +
        linearMechanicalPort.getLinearVelocity().getName().get() + "). " +
        "The variables in the Force variable group must have opposite causality of the variables in the Linear velocity group";
  }
}
