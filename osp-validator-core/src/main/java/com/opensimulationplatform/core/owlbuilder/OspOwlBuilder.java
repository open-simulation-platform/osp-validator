package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.util.OWLEntityRemover;

import java.util.*;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.ValidationError;

public abstract class OspOwlBuilder<T> {

  protected OwlBuilderContext context;

  public abstract OWLNamedIndividual build(T ospObject);

  public void setContext(OwlBuilderContext context) {
    this.context = context;
  }

  public void complete() {
    context.owl.manager.addAxioms(context.owl.ontology, context.axioms);

    if (context.owl.removeNakedVariables) {
      removeNakedVariablesFromOntology();
    }

    context.owl.manager.addAxiom(context.owl.ontology, context.owl.dataFactory.getOWLDifferentIndividualsAxiom(context.individuals));

    context.owl.reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);

    OWLClass validationErrorClass = context.owl.dataFactory.getOWLClass(ValidationError, context.owl.prefixManager);
    Set<OWLClass> validationErrorClasses = context.owl.reasoner.getSubClasses(validationErrorClass, false).getFlattened();
    for (OWLClass errorClass : validationErrorClasses) {
      Set<OWLNamedIndividual> invalidIndividuals = context.owl.reasoner.getInstances(errorClass, false).getFlattened();
      context.invalidIndividuals.put(errorClass, invalidIndividuals);
    }
  }

  private void removeNakedVariablesFromOntology() {
    for (Map.Entry<OWLIndividual, Variable> entries : context.variables.entrySet()) {
      OWLIndividual variableIndividual = entries.getKey();
      Variable variable = entries.getValue();

      Optional<Map.Entry<OWLIndividual, Name>> nameOptional = context.names.entrySet().stream().filter(e -> e.getValue().equals(variable.getName())).findFirst();

      if (!variableExistsInVariableGroup(variable) && !variableExistsInConnection(variable)) {
        OWLEntityRemover remover = new OWLEntityRemover(context.owl.manager, Collections.singleton(context.owl.ontology));
        remover.visit((OWLNamedIndividual) variableIndividual);
        if (nameOptional.isPresent()) {
          OWLIndividual nameIndividual = nameOptional.get().getKey();
          remover.visit((OWLNamedIndividual) nameIndividual);
        }

        context.owl.manager.applyChanges(remover.getChanges());

        context.individuals.remove(variableIndividual);
        if (nameOptional.isPresent()) {
          OWLIndividual nameIndividual = nameOptional.get().getKey();
          context.individuals.remove(nameIndividual);
        }
      }
    }
  }

  private boolean variableExistsInConnection(Variable variable) {
    Collection<VariableConnection> variableConnections = context.variableConnections.values();
    for (VariableConnection variableConnection : variableConnections) {
      Variable variableA = variableConnection.getVariableA();
      Variable variableB = variableConnection.getVariableB();
      if (variableA.equals(variable) || variableB.equals(variable)) {
        return true;
      }
    }
    return false;
  }

  private boolean variableExistsInVariableGroup(Variable variable) {
    Collection<VariableGroup> variableGroups = context.variableGroups.values();
    for (VariableGroup variableGroup : variableGroups) {
      if (variableGroup.getVariables().contains(variable)) {
        return true;
      }
    }
    return false;
  }
}
