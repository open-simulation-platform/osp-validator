package owlconverter;

import datamodel.Plug;
import datamodel.PlugSocketConnection;
import datamodel.Socket;
import org.semanticweb.owlapi.model.*;
import ospontologydatamodel.OspOntologyObjectProperties;

public class PlugSocketConnectionConverter {
  public static void convert(PlugSocketConnection connection, OWLOntology ontology, PrefixManager prefixManager) {
    convert(connection.getPlug(), connection.getSocket(), ontology, prefixManager);
  }
  
  public static void convert(Plug plug, Socket socket, OWLOntology ontology, PrefixManager prefixManager) {
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = manager.getOWLDataFactory();
    
    OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, ontology, prefixManager);
    OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, ontology, prefixManager);
    
    OWLObjectProperty hasPlugMate = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_PLUG_MATE, prefixManager);
    OWLObjectProperty hasSocketMate = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_SOCKET_MATE, prefixManager);
    
    manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasSocketMate, plugIndividual, socketIndividual));
    manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasPlugMate, socketIndividual, plugIndividual));
  }
}
