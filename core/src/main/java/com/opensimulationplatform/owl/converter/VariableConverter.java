package com.opensimulationplatform.owl.converter;

import com.opensimulationplatform.osp.model.OspOntologyClasses;
import com.opensimulationplatform.owl.helper.OwlHelper;
import com.opensimulationplatform.owl.model.OwlConfiguration;
import com.opensimulationplatform.validator.model.modeldefinition.Variable;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.owl.converter.NameConverter.getIndividualName;

class VariableConverter {
  public static OWLNamedIndividual convert(Variable variable, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual variableIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(variable));
    OwlHelper.addClassAssertionAxiom(ontology, variableIndividual, OspOntologyClasses.VARIABLE);
    owlConfiguration.addVariable(variableIndividual, variable);
    
    return variableIndividual;
  }
}
