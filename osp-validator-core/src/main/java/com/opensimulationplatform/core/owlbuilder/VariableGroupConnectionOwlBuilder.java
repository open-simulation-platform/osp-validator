package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import java.util.List;

import static com.opensimulationplatform.gen.model.OntologyClasses.VariableGroupConnection;
import static com.opensimulationplatform.gen.model.OntologyObjectProperties.op_has_lhs;
import static com.opensimulationplatform.gen.model.OntologyObjectProperties.op_has_rhs;
import static java.lang.Math.min;

public class VariableGroupConnectionOwlBuilder extends OspOwlBuilder<VariableGroupConnection> {
  public VariableGroupConnectionOwlBuilder(OWLConfig config) {
    super(config);
  }

  @Override
  public OWLNamedIndividual build(VariableGroupConnection variableGroupConnection) {
    OWLNamedIndividual individual = dataFactory.getOWLNamedIndividual(variableGroupConnection.getId().get(), prefixManager);

    setClass(individual);
    setVariableGroupA(variableGroupConnection, individual);
    setVariableGroupB(variableGroupConnection, individual);
    makeNestedVariableConnections(variableGroupConnection);
    makeNestedVariableGroupConnections(variableGroupConnection);
    setDisjointAxioms(individual);

    config.addVariableGroupConnection(individual, variableGroupConnection);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = dataFactory.getOWLClass(VariableGroupConnection, prefixManager);
    config.addClassAssertionAxiom(clazz, individual);
  }

  private void setVariableGroupA(VariableGroupConnection variableGroupConnection, OWLNamedIndividual individual) {
    VariableGroupOwlBuilder variableGroupOwlBuilder = new VariableGroupOwlBuilder(config);
    OWLObjectProperty hasLeft = dataFactory.getOWLObjectProperty(op_has_lhs, prefixManager);
    OWLNamedIndividual variableAIndividual = variableGroupOwlBuilder.build(variableGroupConnection.getVariableGroupA());
    config.addObjectPropertyAssertionAxiom(individual, hasLeft, variableAIndividual);
  }

  private void setVariableGroupB(VariableGroupConnection variableGroupConnection, OWLNamedIndividual individual) {
    VariableGroupOwlBuilder variableGroupOwlBuilder = new VariableGroupOwlBuilder(config);
    OWLObjectProperty hasRight = dataFactory.getOWLObjectProperty(op_has_rhs, prefixManager);
    OWLNamedIndividual variableBIndividual = variableGroupOwlBuilder.build(variableGroupConnection.getVariableGroupB());
    config.addObjectPropertyAssertionAxiom(individual, hasRight, variableBIndividual);
  }

  private void makeNestedVariableConnections(VariableGroupConnection variableGroupConnection) {
    VariableConnectionOwlBuilder variableConnectionOwlBuilder = new VariableConnectionOwlBuilder(config);
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

  private void setDisjointAxioms(OWLNamedIndividual individual) {
    config.makeDisjointWithAllIndividualsOfClass(individual, dataFactory.getOWLClass(VariableGroupConnection, prefixManager));
  }
}
