package owlconverter;

import datamodel.Plug;
import datamodel.PlugSocketConnection;
import datamodel.Socket;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import owlhelper.OwlHelper;

import static ospontologydatamodel.OspOntologyObjectProperties.HAS_PLUG_MATE;
import static ospontologydatamodel.OspOntologyObjectProperties.HAS_SOCKET_MATE;

public class PlugSocketConnectionConverter {
  public static void convert(PlugSocketConnection connection, OWLOntology ontology) {
    convert(connection.getPlug(), connection.getSocket(), ontology);
  }
  
  public static void convert(Plug plug, Socket socket, OWLOntology ontology) {
    OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, ontology);
    OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, ontology);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, HAS_SOCKET_MATE, socketIndividual);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, HAS_PLUG_MATE, plugIndividual);
  }
}
