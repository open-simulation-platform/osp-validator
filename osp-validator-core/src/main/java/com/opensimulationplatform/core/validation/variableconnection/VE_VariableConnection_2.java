package com.opensimulationplatform.core.validation.variableconnection;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import com.opensimulationplatform.core.validation.ValidationError;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VE_VariableConnection_2;

public class VE_VariableConnection_2 extends ValidationError<VariableConnection> {
  @Override
  protected List<VariableConnection> getInvalidObjects() {
    OWLConfig config = this.context.getOwlConfig();
    OWLClass validationErrorClass = config.dataFactory.getOWLClass(VE_VariableConnection_2, config.prefixManager);
    Stream<OWLNamedIndividual> invalidIndividuals = config.reasoner.getInstances(validationErrorClass, false).getFlattened().stream();
    return invalidIndividuals.map(config::getVariableConnection).collect(Collectors.toList());
  }

  @Override
  protected String getErrorMessage(VariableConnection variableConnection) {
    String variableA = variableConnection.getVariableA().getName().get();
    Variable.Causality causalityA = variableConnection.getVariableA().getCausality();
    String variableB = variableConnection.getVariableB().getName().get();
    Variable.Causality causalityB = variableConnection.getVariableA().getCausality();
    return "Variable connection [" + variableA + ", " + variableB + "] is invalid because they have the same " +
        "causality [" + causalityA + ", " + causalityB + "]";
  }
}
