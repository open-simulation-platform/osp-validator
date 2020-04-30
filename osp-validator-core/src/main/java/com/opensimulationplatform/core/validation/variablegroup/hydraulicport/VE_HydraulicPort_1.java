package com.opensimulationplatform.core.validation.variablegroup.hydraulicport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
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
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_HydraulicPort_1, context.owl.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (HydraulicPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(HydraulicPort hydraulicPort) {
     return "HydraulicPort '" + hydraulicPort.getName().get() + "' contains incompatible Pressure(" +
             hydraulicPort.getPressure().getName().get() + ") and VolumeFlowRate(" +
             hydraulicPort.getVolumeFlowRate().getName().get() + "). " +
             "The variables in the Pressure variable group must have opposite causality of the variables in the VolumeFlowRate variable group";
  }
}
