package com.opensimulationplatform.core.validation.variablegroup.volume;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.volume.Volume;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_Volume_2;

public class VE_Volume_2 extends ValidationError<Volume> {
  @Override
  protected List<Volume> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_Volume_2, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (Volume) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(Volume volume) {
    return "Volume variable group '" + volume.getName().get() + "' contains variables with mixed data types";
  }
}
