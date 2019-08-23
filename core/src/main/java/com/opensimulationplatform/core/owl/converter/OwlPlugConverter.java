package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.validator.model.modeldescription.OspPlug;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.core.owl.converter.IndividualNameGenerator.getIndividualName;

class OwlPlugConverter {
  public static OWLNamedIndividual convert(OspPlug ospPlug, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual plugIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(ospPlug));
    OwlHelper.addClassAssertionAxiom(ontology, plugIndividual, OntologyClasses.PLUG);
    OwlHelper.addClassAssertionAxiom(ontology, plugIndividual, ospPlug.getType());
    owlConfiguration.addPlug(plugIndividual, ospPlug);
  
    ospPlug.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = OwlVariableConverter.convert(variable, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, OntologyObjectProperties.HAS_VARIABLE, variableIndividual);
    });
  
    return plugIndividual;
  }
}
