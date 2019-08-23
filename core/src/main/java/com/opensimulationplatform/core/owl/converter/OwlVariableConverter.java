package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.validator.model.modeldescription.OspVariable;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.core.owl.converter.IndividualNameGenerator.getIndividualName;

class OwlVariableConverter {
  public static OWLNamedIndividual convert(OspVariable ospVariable, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual variableIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(ospVariable));
    OwlHelper.addClassAssertionAxiom(ontology, variableIndividual, OntologyClasses.VARIABLE);
    owlConfiguration.addVariable(variableIndividual, ospVariable);
    
    return variableIndividual;
  }
}
