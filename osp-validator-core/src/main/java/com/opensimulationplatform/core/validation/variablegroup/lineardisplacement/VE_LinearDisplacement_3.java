package com.opensimulationplatform.core.validation.variablegroup.lineardisplacement;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_LinearDisplacement_3;

public class VE_LinearDisplacement_3 extends ValidationError<LinearDisplacement> {
  @Override
  protected List<LinearDisplacement> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_LinearDisplacement_3, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (LinearDisplacement) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(LinearDisplacement linearDisplacement) {
    return "LinearDisplacement variable group '" + linearDisplacement.getName().get() + "' contains variables with incompatible units";
  }
}
