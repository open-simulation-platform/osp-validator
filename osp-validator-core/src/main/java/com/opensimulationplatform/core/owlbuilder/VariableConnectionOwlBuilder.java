package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VariableConnection;
import static com.opensimulationplatform.gen.owl.model.OntologyObjectProperties.op_has_lhs;
import static com.opensimulationplatform.gen.owl.model.OntologyObjectProperties.op_has_rhs;

public class VariableConnectionOwlBuilder extends OspOwlBuilder<VariableConnection> {

  @Override
  public OWLNamedIndividual build(VariableConnection variableConnection) {
    OWLNamedIndividual individual = context.owl.dataFactory.getOWLNamedIndividual(variableConnection.getId().get(), context.owl.prefixManager);
    context.individuals.add(individual);
    context.variableConnections.put(individual, variableConnection);

    setClass(individual);
    setVariableA(variableConnection, individual);
    setVariableB(variableConnection, individual);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = context.owl.dataFactory.getOWLClass(VariableConnection, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    context.axioms.add(axiom);
  }

  private void setVariableA(VariableConnection variableConnection, OWLNamedIndividual individual) {
    VariableOwlBuilder variableOwlBuilder = new VariableOwlBuilder();
    variableOwlBuilder.setContext(context);
    OWLObjectProperty hasLeft = context.owl.dataFactory.getOWLObjectProperty(op_has_lhs, context.owl.prefixManager);
    OWLNamedIndividual variableAIndividual = variableOwlBuilder.build(variableConnection.getVariableA());
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasLeft, individual, variableAIndividual);
    context.axioms.add(axiom);
  }

  private void setVariableB(VariableConnection variableConnection, OWLNamedIndividual individual) {
    VariableOwlBuilder variableOwlBuilder = new VariableOwlBuilder();
    variableOwlBuilder.setContext(context);
    OWLObjectProperty hasRight = context.owl.dataFactory.getOWLObjectProperty(op_has_rhs, context.owl.prefixManager);
    OWLNamedIndividual variableBIndividual = variableOwlBuilder.build(variableConnection.getVariableB());
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasRight, individual, variableBIndividual);
    context.axioms.add(axiom);
  }
}
