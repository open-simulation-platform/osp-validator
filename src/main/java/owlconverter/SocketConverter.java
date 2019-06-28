package owlconverter;

import datamodel.Socket;
import org.semanticweb.owlapi.model.*;
import ospontologydatamodel.OspOntologyClasses;
import ospontologydatamodel.OspOntologyObjectProperties;

public class SocketConverter {
  public static OWLNamedIndividual convert(Socket socket, OWLOntology ontology, PrefixManager prefixManager) {
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = manager.getOWLDataFactory();
    
    OWLNamedIndividual socketIndividual = dataFactory.getOWLNamedIndividual(socket.getId(), prefixManager);
    
    OWLClass socketClass = dataFactory.getOWLClass(OspOntologyClasses.SOCKET, prefixManager);
    manager.addAxiom(ontology, dataFactory.getOWLClassAssertionAxiom(socketClass, socketIndividual));
    
    OWLClass typeClass = dataFactory.getOWLClass(socket.getType(), prefixManager);
    manager.addAxiom(ontology, dataFactory.getOWLClassAssertionAxiom(typeClass, socketIndividual));
    
    socket.getVariables().forEach((variableName, variable) -> {
      OWLNamedIndividual variableIndividual = VariableConverter.convert(variable, ontology, prefixManager);
      OWLObjectProperty hasVariable = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_VARIABLE, prefixManager);
      manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariable, socketIndividual, variableIndividual));
    });
    
    return socketIndividual;
  }
}
