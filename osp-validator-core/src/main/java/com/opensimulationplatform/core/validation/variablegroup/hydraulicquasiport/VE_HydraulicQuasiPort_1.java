package com.opensimulationplatform.core.validation.variablegroup.hydraulicquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicquasiport.HydraulicQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_HydraulicQuasiPort_1;

public class VE_HydraulicQuasiPort_1 extends ValidationError<HydraulicQuasiPort> {
  @Override
  protected List<HydraulicQuasiPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_HydraulicQuasiPort_1, context.owl.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (HydraulicQuasiPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(HydraulicQuasiPort hydraulicQuasiPort) {
     return "HydraulicQuasiPort '" + hydraulicQuasiPort.getName().get() + "' contains incompatible Pressure(" +
             hydraulicQuasiPort.getPressure().getName().get() + ") and Volume(" +
             hydraulicQuasiPort.getVolume().getName().get() + "). " +
             "The variables in the Pressure variable group must have opposite causality of the variables in the Volume variable group";
  }
}
