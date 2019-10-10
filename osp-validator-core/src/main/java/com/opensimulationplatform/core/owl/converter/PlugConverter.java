package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlSystemStructure;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.core.owl.converter.IndividualNameGenerator.getIndividualName;

class PlugConverter {
  public static OWLNamedIndividual convert(OspPlug ospPlug, OwlSystemStructure owlSystemStructure) {
    OWLOntology ontology = owlSystemStructure.getOntology();
    
    OWLNamedIndividual plugIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(ospPlug));
    OwlHelper.addClassAssertionAxiom(ontology, plugIndividual, OntologyClasses.PLUG);
    OwlHelper.addClassAssertionAxiom(ontology, plugIndividual, ospPlug.getType());
    owlSystemStructure.addPlug(plugIndividual, ospPlug);
  
    ospPlug.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, owlSystemStructure);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, OntologyObjectProperties.HAS_VARIABLE, variableIndividual);
    });
  
    return plugIndividual;
  }
}
