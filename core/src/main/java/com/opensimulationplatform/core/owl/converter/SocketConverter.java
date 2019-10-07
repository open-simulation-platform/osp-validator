package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlSystemStructure;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

class SocketConverter {
  public static OWLNamedIndividual convert(OspSocket ospSocket, OwlSystemStructure owlSystemStructure) {
    OWLOntology ontology = owlSystemStructure.getOntology();
  
    OWLNamedIndividual socketIndividual = OwlHelper.getNamedIndividual(ontology, IndividualNameGenerator.getIndividualName(ospSocket));
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, OntologyClasses.SOCKET);
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, ospSocket.getType());
    owlSystemStructure.addSocket(socketIndividual, ospSocket);
    
    ospSocket.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, owlSystemStructure);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, OntologyObjectProperties.HAS_VARIABLE, variableIndividual);
    });
    
    return socketIndividual;
  }
}
