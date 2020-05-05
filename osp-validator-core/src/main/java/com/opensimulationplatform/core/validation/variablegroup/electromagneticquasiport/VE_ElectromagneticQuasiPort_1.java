package com.opensimulationplatform.core.validation.variablegroup.electromagneticquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_ElectromagneticQuasiPort_1;

public class VE_ElectromagneticQuasiPort_1 extends ValidationError<ElectromagneticQuasiPort> {
  @Override
  protected List<ElectromagneticQuasiPort> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_ElectromagneticQuasiPort_1, context.owl.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (ElectromagneticQuasiPort) context.variableGroups.get(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(ElectromagneticQuasiPort electromagneticQuasiPort) {
     return "ElectromagneticQuasiPort '" + electromagneticQuasiPort.getName().getId().get() + "' contains incompatible Voltage(" +
             electromagneticQuasiPort.getVoltage().getName().getId().get() + ") and Charge(" +
             electromagneticQuasiPort.getCharge().getName().getId().get() + "). " +
             "The variables in the Voltage variable group must have opposite causality of the variables in the Charge variable group";
  }
}
