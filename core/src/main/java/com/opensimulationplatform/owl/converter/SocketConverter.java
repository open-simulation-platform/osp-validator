package com.opensimulationplatform.owl.converter;

import com.opensimulationplatform.owl.helper.OwlHelper;
import com.opensimulationplatform.owl.model.OwlConfiguration;
import com.opensimulationplatform.validator.model.modeldefinition.Socket;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.osp.model.OspOntologyClasses.SOCKET;
import static com.opensimulationplatform.osp.model.OspOntologyObjectProperties.HAS_VARIABLE;
import static com.opensimulationplatform.owl.converter.NameConverter.getIndividualName;

class SocketConverter {
  public static OWLNamedIndividual convert(Socket socket, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
  
    OWLNamedIndividual socketIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(socket));
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, SOCKET);
    OwlHelper.addClassAssertionAxiom(ontology, socketIndividual, socket.getType());
    owlConfiguration.addSocket(socketIndividual, socket);
    
    socket.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, HAS_VARIABLE, variableIndividual);
    });
    
    return socketIndividual;
  }
}
