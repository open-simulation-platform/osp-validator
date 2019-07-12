package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.modeldefinition.Socket;
import com.opensimulationplatform.owlhelper.OwlHelper;
import com.opensimulationplatform.owlmodel.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.ospontologydatamodel.OspOntologyClasses.SOCKET;
import static com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties.HAS_VARIABLE;
import static com.opensimulationplatform.owlconverter.OwlConverterUtil.getIndividualName;

public class SocketConverter {
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
