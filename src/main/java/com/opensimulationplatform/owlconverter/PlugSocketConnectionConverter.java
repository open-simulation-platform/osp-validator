package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.configuration.PlugSocketConnection;
import com.opensimulationplatform.datamodel.modeldefinition.Plug;
import com.opensimulationplatform.datamodel.modeldefinition.Socket;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties;
import com.opensimulationplatform.owlhelper.OwlHelper;
import com.opensimulationplatform.owlmodel.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

class PlugSocketConnectionConverter {
  public static void convert(PlugSocketConnection connection, OwlConfiguration owlConfiguration) {
    convert(connection.getPlug(), connection.getSocket(), owlConfiguration);
  }
  
  public static void convert(Plug plug, Socket socket, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
  
    OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, owlConfiguration);
    OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, owlConfiguration);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, OspOntologyObjectProperties.HAS_SOCKET_MATE, socketIndividual);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, OspOntologyObjectProperties.HAS_PLUG_MATE, plugIndividual);
  }
}
