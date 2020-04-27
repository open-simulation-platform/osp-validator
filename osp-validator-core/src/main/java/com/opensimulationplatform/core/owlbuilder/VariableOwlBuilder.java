package com.opensimulationplatform.core.owlbuilder;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.Variable.Causality;
import com.opensimulationplatform.core.model.modeldescription.Variable.Type;
import com.opensimulationplatform.core.owlconfig.OWLConfig;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import java.util.HashMap;
import java.util.Map;

import static com.opensimulationplatform.gen.model.OntologyClasses.Variable;
import static com.opensimulationplatform.gen.model.OntologyIndividuals.*;
import static com.opensimulationplatform.gen.model.OntologyObjectProperties.*;

public class VariableOwlBuilder extends OspOwlBuilder<Variable> {

  Map<Type, String> typeMap = new HashMap<>();
  Map<Causality, String> causalityMap = new HashMap<>();

  public VariableOwlBuilder(OWLConfig config) {
    super(config);

    typeMap.put(Type.REAL, ind_datatype_real);
    typeMap.put(Type.INTEGER, ind_datatype_integer);
    typeMap.put(Type.BOOLEAN, ind_datatype_boolean);
    typeMap.put(Type.STRING, ind_datatype_string);
    typeMap.put(Type.ENUM, ind_datatype_enum);
    typeMap.put(Type.UNDEFINED, ind_datatype_undefined);

    causalityMap.put(Causality.INPUT, ind_causality_input);
    causalityMap.put(Causality.OUTPUT, ind_causality_output);
    causalityMap.put(Causality.UNDEFINED, ind_causality_undefined);
  }

  public OWLNamedIndividual build(Variable variable) {
    OWLNamedIndividual individual = dataFactory.getOWLNamedIndividual(variable.getId().get(), prefixManager);

    setClass(individual);
    setName(variable, individual);
    setCausality(variable, individual);
    setDataType(variable, individual);
    setUnit(variable, individual);
    setDisjointAxioms(individual);

    config.addVariable(individual, variable);

    return individual;
  }

  private void setName(Variable variable, OWLNamedIndividual individual) {
    NameOwlBuilder nameOwlBuilder = new NameOwlBuilder(config);
    OWLNamedIndividual nameIndividual = nameOwlBuilder.build(variable.getName());
    OWLObjectProperty hasName = dataFactory.getOWLObjectProperty(op_has_name, prefixManager);
    config.addObjectPropertyAssertionAxiom(individual, hasName, nameIndividual);
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = dataFactory.getOWLClass(Variable, prefixManager);
    config.addClassAssertionAxiom(clazz, individual);
  }

  private void setUnit(Variable variable, OWLNamedIndividual individual) {
    UnitOwlBuilder unitBuilder = new UnitOwlBuilder(config);
    OWLNamedIndividual unitIndividual = unitBuilder.build(variable.getUnit());
    OWLObjectProperty hasUnit = dataFactory.getOWLObjectProperty(op_has_unit, prefixManager);
    config.addObjectPropertyAssertionAxiom(individual, hasUnit, unitIndividual);
  }

  private void setDataType(Variable variable, OWLNamedIndividual individual) {
    OWLObjectProperty hasDataType = dataFactory.getOWLObjectProperty(op_has_datatype, prefixManager);
    OWLNamedIndividual dataType = dataFactory.getOWLNamedIndividual(typeMap.get(variable.getType()), prefixManager);
    config.addObjectPropertyAssertionAxiom(individual, hasDataType, dataType);
  }

  private void setCausality(Variable variable, OWLNamedIndividual individual) {
    OWLObjectProperty hasCausality = dataFactory.getOWLObjectProperty(op_has_causality, prefixManager);
    OWLNamedIndividual causality = dataFactory.getOWLNamedIndividual(causalityMap.get(variable.getCausality()), prefixManager);
    config.addObjectPropertyAssertionAxiom(individual, hasCausality, causality);
  }

  private void setDisjointAxioms(OWLNamedIndividual individual) {
    config.makeDisjointWithAllIndividualsOfClass(individual, dataFactory.getOWLClass(Variable, prefixManager));
  }
}
