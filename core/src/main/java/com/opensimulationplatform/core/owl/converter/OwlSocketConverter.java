package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

class OwlSocketConverter {
  public static OWLNamedIndividual convert(OspSocket ospSocket, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
  
    OWLNamedIndividual socketIndividual = OwlHelper.getNamedIndividual(ontology, IndividualNameGenerator.getIndividualName(ospSocket));
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, OntologyClasses.SOCKET);
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, ospSocket.getType());
    owlConfiguration.addSocket(socketIndividual, ospSocket);
    
    ospSocket.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = OwlVariableConverter.convert(variable, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, OntologyObjectProperties.HAS_VARIABLE, variableIndividual);
    });
    
    return socketIndividual;
  }
}
