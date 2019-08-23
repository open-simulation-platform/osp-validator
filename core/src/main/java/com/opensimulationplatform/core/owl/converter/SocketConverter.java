package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.osp.model.OspOntologyClasses;
import com.opensimulationplatform.core.osp.model.OspOntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.Socket;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

class SocketConverter {
  public static OWLNamedIndividual convert(Socket socket, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
  
    OWLNamedIndividual socketIndividual = OwlHelper.getNamedIndividual(ontology, NameConverter.getIndividualName(socket));
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, OspOntologyClasses.SOCKET);
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, socket.getType());
    owlConfiguration.addSocket(socketIndividual, socket);
    
    socket.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, OspOntologyObjectProperties.HAS_VARIABLE, variableIndividual);
    });
    
    return socketIndividual;
  }
}
