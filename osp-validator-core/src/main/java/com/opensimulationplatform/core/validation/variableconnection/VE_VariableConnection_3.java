package com.opensimulationplatform.core.validation.variableconnection;

import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_VariableConnection_3;

public class VE_VariableConnection_3 extends ValidationError<VariableConnection> {
  @Override
  protected List<VariableConnection> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_VariableConnection_3, context.owl.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(context.variableConnections::get).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(VariableConnection variableConnection) {
    String variableA = variableConnection.getVariableA().getName().get();
    String unitA = variableConnection.getVariableA().getUnit().getName().get();
    String variableB = variableConnection.getVariableB().getName().get();
    String unitB = variableConnection.getVariableA().getUnit().getName().get();
    return "Variable connection [" + variableA + ", " + variableB + "] is invalid because they have incompatible " +
        "units [" + unitA + ", " + unitB + "]";
  }
}
