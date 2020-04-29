package com.opensimulationplatform.core.validation.variablegroupconnection;

import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_VariableGroupConnection_1;

public class VE_VariableGroupConnection_1 extends ValidationError<VariableGroupConnection> {
  @Override
  protected List<VariableGroupConnection> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_VariableGroupConnection_1, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(config::getVariableGroupConnection).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(VariableGroupConnection variableGroupConnection) {
    String variableGroupA = variableGroupConnection.getVariableGroupA().getName().get();
    String typeA = variableGroupConnection.getVariableGroupA().getClass().getSimpleName();
    String variableGroupB = variableGroupConnection.getVariableGroupB().getName().get();
    String typeB = variableGroupConnection.getVariableGroupB().getClass().getSimpleName();
    return "Variable group connection [" + variableGroupA + ", " + variableGroupB + "] is invalid because they have " +
        "different types [" + typeA + ", " + typeB + "]";
  }
}
