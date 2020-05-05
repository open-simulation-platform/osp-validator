package com.opensimulationplatform.core.validation.variableconnection;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_VariableConnection_3;

public class VE_VariableConnection_3 extends ValidationError<VariableConnection> {
  @Override
  protected List<VariableConnection> getInvalidObjects() {
    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(VE_VariableConnection_3, context.owl.prefixManager);
    Set<OWLNamedIndividual> invalidIndividuals = context.invalidIndividuals.get(validationErrorClass);
    return invalidIndividuals.stream().map(context.variableConnections::get).filter(Objects::nonNull).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(VariableConnection variableConnection) {
    Variable variableA = variableConnection.getVariableA();
    String variableAName = variableA.getName().getId().get();
    Unit unitA = variableA.getUnit();
    String unitAName = unitA.getName().get();

    Variable variableB = variableConnection.getVariableB();
    String variableBName = variableB.getName().getId().get();
    Unit unitB = variableB.getUnit();
    String unitBName = unitB.getName().get();

    return "VariableConnection [" + variableAName + ", " + variableBName + "] is invalid because they have incompatible " +
        "units [" + unitAName + ", " + unitBName + "]";
  }
}
