package com.opensimulationplatform.core.validation.name;


import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.model.OntologyClasses.VE_Name_3;

public class VE_Name_3 extends ValidationError<Name> {
  @Override
  protected List<Name> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_Name_3, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(config::getName).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(Name name) {
    return "Variable group name '" + name.get() + "' is not unique";
  }
}