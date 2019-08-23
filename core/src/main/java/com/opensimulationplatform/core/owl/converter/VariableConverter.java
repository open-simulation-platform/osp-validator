package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.osp.model.OspOntologyClasses;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.Variable;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.core.owl.converter.NameConverter.getIndividualName;

class VariableConverter {
  public static OWLNamedIndividual convert(Variable variable, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual variableIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(variable));
    OwlHelper.addClassAssertionAxiom(ontology, variableIndividual, OspOntologyClasses.VARIABLE);
    owlConfiguration.addVariable(variableIndividual, variable);
    
    return variableIndividual;
  }
}
