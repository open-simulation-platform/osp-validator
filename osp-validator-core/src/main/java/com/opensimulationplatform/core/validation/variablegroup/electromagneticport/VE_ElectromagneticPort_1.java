package com.opensimulationplatform.core.validation.variablegroup.electromagneticport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticport.ElectromagneticPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_ElectromagneticPort_1;

public class VE_ElectromagneticPort_1 extends ValidationError<ElectromagneticPort> {
  @Override
  protected List<ElectromagneticPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_ElectromagneticPort_1, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(individual -> (ElectromagneticPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(ElectromagneticPort electromagneticPort) {
     return "ElectromagneticPort '" + electromagneticPort.getName().getId().get() + "' contains incompatible Voltage(" +
             electromagneticPort.getVoltage().getName().getId().get() + ") and Current(" +
             electromagneticPort.getCurrent().getName().getId().get() + "). " +
             "The variables in the Voltage variable group must have opposite causality of the variables in the Current variable group";
  }
}
