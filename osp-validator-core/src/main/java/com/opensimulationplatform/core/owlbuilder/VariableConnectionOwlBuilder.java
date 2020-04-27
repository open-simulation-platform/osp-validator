package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import static com.opensimulationplatform.gen.model.OntologyClasses.VariableConnection;
import static com.opensimulationplatform.gen.model.OntologyObjectProperties.op_has_lhs;
import static com.opensimulationplatform.gen.model.OntologyObjectProperties.op_has_rhs;

public class VariableConnectionOwlBuilder extends OspOwlBuilder<VariableConnection> {
  public VariableConnectionOwlBuilder(OWLConfig config) {
    super(config);
  }

  @Override
  public OWLNamedIndividual build(VariableConnection variableConnection) {
    OWLNamedIndividual individual = dataFactory.getOWLNamedIndividual(variableConnection.getId().get(), prefixManager);

    setClass(individual);
    setVariableA(variableConnection, individual);
    setVariableB(variableConnection, individual);
    setDisjointAxioms(individual);

    config.addVariableConnection(individual, variableConnection);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = dataFactory.getOWLClass(VariableConnection, prefixManager);
    config.addClassAssertionAxiom(clazz, individual);
  }

  private void setVariableA(VariableConnection variableConnection, OWLNamedIndividual individual) {
    VariableOwlBuilder variableOwlBuilder = new VariableOwlBuilder(config);
    OWLObjectProperty hasLeft = dataFactory.getOWLObjectProperty(op_has_lhs, prefixManager);
    OWLNamedIndividual variableAIndividual = variableOwlBuilder.build(variableConnection.getVariableA());
    config.addObjectPropertyAssertionAxiom(individual, hasLeft, variableAIndividual);
  }

  private void setVariableB(VariableConnection variableConnection, OWLNamedIndividual individual) {
    VariableOwlBuilder variableOwlBuilder = new VariableOwlBuilder(config);
    OWLObjectProperty hasRight = dataFactory.getOWLObjectProperty(op_has_rhs, prefixManager);
    OWLNamedIndividual variable = variableOwlBuilder.build(variableConnection.getVariableB());
    config.addObjectPropertyAssertionAxiom(individual, hasRight, variable);
  }

  private void setDisjointAxioms(OWLNamedIndividual individual) {
    config.makeDisjointWithAllIndividualsOfClass(individual, dataFactory.getOWLClass(VariableConnection, prefixManager));
  }
}
