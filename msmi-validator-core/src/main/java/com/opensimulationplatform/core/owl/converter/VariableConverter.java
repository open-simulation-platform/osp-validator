package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.modeldescription.OspVariable;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlSystemStructure;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.core.owl.converter.IndividualNameGenerator.getIndividualName;

class VariableConverter {
  public static OWLNamedIndividual convert(OspVariable ospVariable, OwlSystemStructure owlSystemStructure) {
    OWLOntology ontology = owlSystemStructure.getOntology();
    
    OWLNamedIndividual variableIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(ospVariable));
    OwlHelper.addClassAssertionAxiom(ontology, variableIndividual, OntologyClasses.VARIABLE);
    owlSystemStructure.addVariable(variableIndividual, ospVariable);
    
    return variableIndividual;
  }
}
