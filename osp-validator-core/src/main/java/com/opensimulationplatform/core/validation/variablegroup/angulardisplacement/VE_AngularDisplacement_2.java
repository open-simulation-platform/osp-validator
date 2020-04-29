package com.opensimulationplatform.core.validation.variablegroup.angulardisplacement;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_AngularDisplacement_2;

public class VE_AngularDisplacement_2 extends ValidationError<AngularDisplacement> {
  @Override
  protected List<AngularDisplacement> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_AngularDisplacement_2, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (AngularDisplacement) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(AngularDisplacement angularDisplacement) {
    return "AngularDisplacement variable group '" + angularDisplacement.getName().get() + "' contains variables with mixed data types";
  }
}
