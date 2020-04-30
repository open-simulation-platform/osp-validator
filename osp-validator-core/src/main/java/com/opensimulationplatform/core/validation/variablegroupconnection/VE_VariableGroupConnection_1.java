package com.opensimulationplatform.core.validation.variablegroupconnection;

import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
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
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_VariableGroupConnection_1, context.owl.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(context.variableGroupConnections::get).collect(Collectors.toList());
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
