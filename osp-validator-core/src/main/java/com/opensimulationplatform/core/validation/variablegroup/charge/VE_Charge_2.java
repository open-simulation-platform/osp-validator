package com.opensimulationplatform.core.validation.variablegroup.charge;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_Charge_2;

public class VE_Charge_2 extends ValidationError<Charge> {
  @Override
  protected List<Charge> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_Charge_2, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (Charge) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(Charge charge) {
    return "Charge variable group '" + charge.getName().get() + "' contains variables with mixed data types";
  }
}
