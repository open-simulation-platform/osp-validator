/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Name;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import java.util.List;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.Simulator;
import static com.opensimulationplatform.gen.owl.model.OntologyObjectProperties.*;

public class SimulatorOwlBuilder extends OspOwlBuilder<Simulator> {

  @Override
  public OWLNamedIndividual build(Simulator simulator) {
    OWLNamedIndividual individual = context.owl.dataFactory.getOWLNamedIndividual(simulator.getId().toString(), context.owl.prefixManager);
    context.individuals.add(individual);
    context.simulators.put(individual, simulator);

    setClass(individual);
    setName(individual, simulator.getName());
    setVariables(simulator, individual);
    setVariableGroups(simulator, individual);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = context.owl.dataFactory.getOWLClass(Simulator, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    context.axioms.add(axiom);
  }

  private void setVariables(Simulator simulator, OWLNamedIndividual simulatorIndividual) {
    List<Variable> variables = simulator.getModelDescription().getVariables();
    VariableOwlBuilder variableOwlBuilder = new VariableOwlBuilder();
    variableOwlBuilder.setContext(context);
    variableOwlBuilder.setContext(context);
    variables.forEach(variable -> {
      if ("".equals(simulator.getName().get())) {
        variable.getName().setId(variable.getName().get());
      } else {
        variable.getName().setId(simulator.getName().get() + "." + variable.getName().get());
      }
      OWLNamedIndividual variableIndividual = variableOwlBuilder.build(variable);

      OWLObjectProperty hasVariable = context.owl.dataFactory.getOWLObjectProperty(op_has_variable, context.owl.prefixManager);
      OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariable, simulatorIndividual, variableIndividual);
      context.axioms.add(axiom);
    });
  }

  private void setVariableGroups(Simulator simulator, OWLNamedIndividual simulatorIndividual) {
    List<VariableGroup> variableGroups = simulator.getModelDescription().getVariableGroups();
    VariableGroupOwlBuilder variableGroupOwlBuilder = new VariableGroupOwlBuilder();
    variableGroupOwlBuilder.setContext(context);
    variableGroups.forEach(variableGroup -> {
      if ("".equals(simulator.getName().get())) {
        variableGroup.getName().setId(variableGroup.getName().get());
      } else {
        variableGroup.getName().setId(simulator.getName().get() + "." + variableGroup.getName().get());
      }
      OWLNamedIndividual variableGroupIndividual = variableGroupOwlBuilder.build(variableGroup);

      OWLObjectProperty hasVariableGroup = context.owl.dataFactory.getOWLObjectProperty(op_has_variable_group, context.owl.prefixManager);
      OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariableGroup, simulatorIndividual, variableGroupIndividual);
      context.axioms.add(axiom);
    });
  }

  private void setName(OWLNamedIndividual individual, Name name) {
    NameOwlBuilder nameOwlBuilder = new NameOwlBuilder();
    nameOwlBuilder.setContext(context);
    OWLNamedIndividual nameIndividual = nameOwlBuilder.build(name);
    OWLObjectProperty hasName = context.owl.dataFactory.getOWLObjectProperty(op_has_name, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasName, individual, nameIndividual);
    context.axioms.add(axiom);
  }
}
