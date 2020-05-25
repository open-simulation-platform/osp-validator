package com.opensimulationplatform.core.validation.variablegroup.nmeaggafix;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix.NmeaGgaFix;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_NmeaGgaFix_1;

public class VE_NmeaGgaFix_1 extends ValidationError<NmeaGgaFix> {
  @Override
  protected List<NmeaGgaFix> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_NmeaGgaFix_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (NmeaGgaFix) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(NmeaGgaFix nmeaGgaFix) {
    return "NmeaGgaFix variable group '" + nmeaGgaFix.getName().getId().get() + "' contains variables with mixed causalities";
  }
}
