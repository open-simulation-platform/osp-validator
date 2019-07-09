package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.Plug;
import com.opensimulationplatform.datamodel.PlugSocketConnection;
import com.opensimulationplatform.datamodel.Socket;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties;
import com.opensimulationplatform.owlhelper.OwlHelper;
import org.semanticweb.owlapi.model.*;

public class PlugSocketConnectionConverter {
  public static void convert(PlugSocketConnection connection, OWLOntology ontology) {
    convert(connection.getPlug(), connection.getSocket(), ontology);
  }
  
  public static void convert(Plug plug, Socket socket, OWLOntology ontology) {
    OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, ontology);
    OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, ontology);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, OspOntologyObjectProperties.HAS_SOCKET_MATE, socketIndividual);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, OspOntologyObjectProperties.HAS_PLUG_MATE, plugIndividual);
  }
}
