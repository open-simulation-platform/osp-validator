package com.opensimulationplatform.core.validation.variablegroup.electromagneticquasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
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
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_ElectromagneticQuasiPort_1, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(individual -> (ElectromagneticQuasiPort) config.getVariableGroup(individual)).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(ElectromagneticQuasiPort electromagneticQuasiPort) {
     return "ElectromagneticQuasiPort '" + electromagneticQuasiPort.getName().get() + "' contains incompatible Voltage(" +
             electromagneticQuasiPort.getVoltage().getName().get() + ") and Charge(" +
             electromagneticQuasiPort.getCharge().getName().get() + "). " +
             "The variables in the Voltage variable group must have opposite causality of the variables in the Charge variable group";
  }
}
