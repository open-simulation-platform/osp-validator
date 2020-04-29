package com.opensimulationplatform.core.validation.variablegroup.volumeflowrate;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_VolumeFlowRate_3;

public class VE_VolumeFlowRate_3 extends ValidationError<VolumeFlowRate> {
  @Override
  protected List<VolumeFlowRate> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_VolumeFlowRate_3, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (VolumeFlowRate) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(VolumeFlowRate volumeFlowRate) {
    return "VolumeFlowRate variable group '" + volumeFlowRate.getName().get() + "' contains variables with incompatible units";
  }
}
