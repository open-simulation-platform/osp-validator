package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import java.util.List;

import static com.opensimulationplatform.gen.model.OntologyClasses.Simulator;
import static com.opensimulationplatform.gen.model.OntologyObjectProperties.*;

public class SimulatorOwlBuilder extends OspOwlBuilder<Simulator> {
  public SimulatorOwlBuilder(OWLConfig config) {
    super(config);
  }

  @Override
  public OWLNamedIndividual build(Simulator simulator) {
    OWLNamedIndividual individual = dataFactory.getOWLNamedIndividual(simulator.getId().get(), prefixManager);

    setClass(individual);
    setName(individual, simulator.getName());
    setVariables(simulator, individual);
    setVariableGroups(simulator, individual);
    setDisjointAxioms(individual);

    config.addSimulator(individual, simulator);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = dataFactory.getOWLClass(Simulator, prefixManager);
    OWLAxiom axiom = dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    manager.addAxiom(ontology, axiom);
  }

  private void setVariables(Simulator simulator, OWLNamedIndividual simulatorIndividual) {
    List<Variable> variables = simulator.getModelDescription().getVariables();
    VariableOwlBuilder variableOwlBuilder = new VariableOwlBuilder(config);
    variables.forEach(variable -> {
      variable.getName().setId(() -> simulator.getName().get() + "." + variable.getName().get());
      OWLNamedIndividual variableIndividual = variableOwlBuilder.build(variable);

      OWLObjectProperty hasVariable = dataFactory.getOWLObjectProperty(op_has_variable, prefixManager);
      OWLAxiom axiom = dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariable, simulatorIndividual, variableIndividual);
      manager.addAxiom(ontology, axiom);
    });
  }

  private void setVariableGroups(Simulator simulator, OWLNamedIndividual simulatorIndividual) {
    List<VariableGroup> variableGroups = simulator.getModelDescription().getVariableGroups();
    VariableGroupOwlBuilder variableGroupOwlBuilder = new VariableGroupOwlBuilder(config);
    variableGroups.forEach(variableGroup -> {
      variableGroup.getName().setId(() -> simulator.getName().get() + "." + variableGroup.getName().get());
      OWLNamedIndividual variableGroupIndividual = variableGroupOwlBuilder.build(variableGroup);

      OWLObjectProperty hasVariableGroup = dataFactory.getOWLObjectProperty(op_has_variable_group, prefixManager);
      OWLAxiom axiom = dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariableGroup, simulatorIndividual, variableGroupIndividual);
      manager.addAxiom(ontology, axiom);
    });
  }

  private void setName(OWLNamedIndividual individual, Name name) {
    NameOwlBuilder nameOwlBuilder = new NameOwlBuilder(config);
    OWLNamedIndividual nameIndividual = nameOwlBuilder.build(name);
    OWLObjectProperty hasName = dataFactory.getOWLObjectProperty(op_has_name, prefixManager);
    config.addObjectPropertyAssertionAxiom(individual, hasName, nameIndividual);
  }

  private void setDisjointAxioms(OWLNamedIndividual individual) {
    config.makeDisjointWithAllIndividualsOfClass(individual, dataFactory.getOWLClass(Simulator, prefixManager));
  }
}
