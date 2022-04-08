/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.Variable.Axis;
import com.opensimulationplatform.core.model.modeldescription.Variable.Causality;
import com.opensimulationplatform.core.model.modeldescription.Variable.Type;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import java.util.HashMap;
import java.util.Map;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.Variable;
import static com.opensimulationplatform.gen.owl.model.OntologyIndividuals.*;
import static com.opensimulationplatform.gen.owl.model.OntologyObjectProperties.*;

public class VariableOwlBuilder extends OspOwlBuilder<Variable> {

  Map<Type, String> typeMap = new HashMap<>();
  Map<Causality, String> causalityMap = new HashMap<>();
  Map<Axis, String> axisMap = new HashMap<>();

  public VariableOwlBuilder() {
    typeMap.put(Type.REAL, ind_datatype_real);
    typeMap.put(Type.INTEGER, ind_datatype_integer);
    typeMap.put(Type.BOOLEAN, ind_datatype_boolean);
    typeMap.put(Type.STRING, ind_datatype_string);
    typeMap.put(Type.ENUM, ind_datatype_enum);
    typeMap.put(Type.UNDEFINED, ind_datatype_undefined);

    causalityMap.put(Causality.INPUT, ind_causality_input);
    causalityMap.put(Causality.OUTPUT, ind_causality_output);
    causalityMap.put(Causality.UNDEFINED, ind_causality_undefined);

    axisMap.put(Axis.X, null);
    axisMap.put(Axis.Y, null);
    axisMap.put(Axis.Z, null);
  }

  public OWLNamedIndividual build(Variable variable) {
    OWLNamedIndividual individual = context.owl.dataFactory.getOWLNamedIndividual(variable.getId().toString(), context.owl.prefixManager);
    context.individuals.add(individual);
    context.variables.put(individual, variable);

    setClass(individual);
    setName(variable, individual);
    setCausality(variable, individual);
    setDataType(variable, individual);
    setUnit(variable, individual);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = context.owl.dataFactory.getOWLClass(Variable, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    context.axioms.add(axiom);
  }

  private void setName(Variable variable, OWLNamedIndividual individual) {
    NameOwlBuilder nameOwlBuilder = new NameOwlBuilder();
    nameOwlBuilder.setContext(context);
    OWLNamedIndividual nameIndividual = nameOwlBuilder.build(variable.getName());
    OWLObjectProperty hasName = context.owl.dataFactory.getOWLObjectProperty(op_has_name, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasName, individual, nameIndividual);
    context.axioms.add(axiom);
  }

  private void setUnit(Variable variable, OWLNamedIndividual individual) {
    UnitOwlBuilder unitBuilder = new UnitOwlBuilder();
    unitBuilder.setContext(context);
    OWLNamedIndividual unitIndividual = unitBuilder.build(variable.getUnit());
    OWLObjectProperty hasUnit = context.owl.dataFactory.getOWLObjectProperty(op_has_unit, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasUnit, individual, unitIndividual);
    context.axioms.add(axiom);
  }

  private void setDataType(Variable variable, OWLNamedIndividual individual) {
    OWLObjectProperty hasDataType = context.owl.dataFactory.getOWLObjectProperty(op_has_datatype, context.owl.prefixManager);
    OWLNamedIndividual dataType = context.owl.dataFactory.getOWLNamedIndividual(typeMap.get(variable.getType()), context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasDataType, individual, dataType);
    context.axioms.add(axiom);
  }

  private void setCausality(Variable variable, OWLNamedIndividual individual) {
    OWLObjectProperty hasCausality = context.owl.dataFactory.getOWLObjectProperty(op_has_causality, context.owl.prefixManager);
    OWLNamedIndividual causality = context.owl.dataFactory.getOWLNamedIndividual(causalityMap.get(variable.getCausality()), context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasCausality, individual, causality);
    context.axioms.add(axiom);
  }

//  private void setAxis(Variable variable, OWLNamedIndividual individual) {
//    OWLObjectProperty hasAxis = context.owl.dataFactory.getOWLObjectProperty(op_has_axis, context.owl.prefixManager);
//    OWLNamedIndividual axis = context.owl.dataFactory.getOWLNamedIndividual(axisMap.get(variable.getAxis()), context.owl.prefixManager);
//    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasAxis, individual, axis);
//    context.axioms.add(axiom);
//  }
}
