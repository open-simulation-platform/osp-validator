package com.opensimulationplatform.core.validation.variablegroup.hydraulicport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_HydraulicPort_1;

public class VE_HydraulicPort_1 extends ValidationError<HydraulicPort> {
  @Override
  protected List<HydraulicPort> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_HydraulicPort_1, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (HydraulicPort) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(HydraulicPort hydraulicPort) {
     return "HydraulicPort '" + hydraulicPort.getName().get() + "' contains incompatible Pressure(" +
             hydraulicPort.getPressure().getName().get() + ") and VolumeFlowRate(" +
             hydraulicPort.getVolumeFlowRate().getName().get() + "). " +
             "The variables in the Pressure variable group must have opposite causality of the variables in the VolumeFlowRate variable group";
  }
}
