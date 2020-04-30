package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import java.util.List;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VariableGroupConnection;
import static com.opensimulationplatform.gen.owl.model.OntologyObjectProperties.op_has_lhs;
import static com.opensimulationplatform.gen.owl.model.OntologyObjectProperties.op_has_rhs;
import static java.lang.Math.min;

public class VariableGroupConnectionOwlBuilder extends OspOwlBuilder<VariableGroupConnection> {

  @Override
  public OWLNamedIndividual build(VariableGroupConnection variableGroupConnection) {
    OWLNamedIndividual individual = context.owl.dataFactory.getOWLNamedIndividual(variableGroupConnection.getId().get(), context.owl.prefixManager);
    context.individuals.add(individual);
    context.variableGroupConnections.put(individual, variableGroupConnection);

    setClass(individual);
    setVariableGroupA(variableGroupConnection, individual);
    setVariableGroupB(variableGroupConnection, individual);
    makeNestedVariableConnections(variableGroupConnection);
    makeNestedVariableGroupConnections(variableGroupConnection);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = context.owl.dataFactory.getOWLClass(VariableGroupConnection, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    context.axioms.add(axiom);
  }

  private void setVariableGroupA(VariableGroupConnection variableGroupConnection, OWLNamedIndividual individual) {
    VariableGroupOwlBuilder variableGroupOwlBuilder = new VariableGroupOwlBuilder();
    variableGroupOwlBuilder.setContext(context);
    OWLObjectProperty hasLeft = context.owl.dataFactory.getOWLObjectProperty(op_has_lhs, context.owl.prefixManager);
    OWLNamedIndividual variableGroupAIndividual = variableGroupOwlBuilder.build(variableGroupConnection.getVariableGroupA());
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasLeft, individual, variableGroupAIndividual);
    context.axioms.add(axiom);
  }

  private void setVariableGroupB(VariableGroupConnection variableGroupConnection, OWLNamedIndividual individual) {
    VariableGroupOwlBuilder variableGroupOwlBuilder = new VariableGroupOwlBuilder();
    variableGroupOwlBuilder.setContext(context);
    OWLObjectProperty hasRight = context.owl.dataFactory.getOWLObjectProperty(op_has_rhs, context.owl.prefixManager);
    OWLNamedIndividual variableGroupBIndividual = variableGroupOwlBuilder.build(variableGroupConnection.getVariableGroupB());
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasRight, individual, variableGroupBIndividual);
    context.axioms.add(axiom);
  }

  private void makeNestedVariableConnections(VariableGroupConnection variableGroupConnection) {
    VariableConnectionOwlBuilder variableConnectionOwlBuilder = new VariableConnectionOwlBuilder();
    variableConnectionOwlBuilder.setContext(context);
    List<Variable> variablesA = variableGroupConnection.getVariableGroupA().getVariables();
    List<Variable> variablesB = variableGroupConnection.getVariableGroupB().getVariables();
    for (int i = 0; i < min(variablesA.size(), variablesB.size()); i++) {
      VariableConnection connection = new VariableConnection();
      connection.setSimulatorA(variableGroupConnection.getSimulatorA());
      connection.setSimulatorB(variableGroupConnection.getSimulatorB());
      connection.setVariableA(variablesA.get(i));
      connection.setVariableB(variablesB.get(i));
      variableConnectionOwlBuilder.build(connection);
    }
  }

  private void makeNestedVariableGroupConnections(VariableGroupConnection variableGroupConnection) {
    List<VariableGroup> variableGroupsA = variableGroupConnection.getVariableGroupA().getVariableGroups();
    List<VariableGroup> variableGroupsB = variableGroupConnection.getVariableGroupB().getVariableGroups();
    for (int i = 0; i < min(variableGroupsA.size(), variableGroupsB.size()); i++) {
      VariableGroupConnection connection = new VariableGroupConnection();
      connection.setSimulatorA(connection.getSimulatorA());
      connection.setSimulatorB(connection.getSimulatorB());
      connection.setVariableGroupA(variableGroupsA.get(i));
      connection.setVariableGroupB(variableGroupsB.get(i));
      this.build(connection);
    }
  }
}
