package owlconverter;

import datamodel.Plug;
import datamodel.PlugSocketConnection;
import datamodel.Socket;
import org.semanticweb.owlapi.model.*;
import owlhelper.OwlHelper;

import static ospontologydatamodel.OspOntologyObjectProperties.HAS_PLUG_MATE;
import static ospontologydatamodel.OspOntologyObjectProperties.HAS_SOCKET_MATE;

public class PlugSocketConnectionConverter {
  public static void convert(PlugSocketConnection connection, OWLOntology ontology, PrefixManager prefixManager) {
    convert(connection.getPlug(), connection.getSocket(), ontology, prefixManager);
  }
  
  public static void convert(Plug plug, Socket socket, OWLOntology ontology, PrefixManager prefixManager) {
    OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, ontology, prefixManager);
    OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, ontology, prefixManager);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, HAS_SOCKET_MATE, socketIndividual, prefixManager);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, HAS_PLUG_MATE, plugIndividual, prefixManager);
  }
}
