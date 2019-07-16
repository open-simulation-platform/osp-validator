package com.opensimulationplatform.owl.converter;

import com.opensimulationplatform.validator.model.configuration.PlugSocketConnection;
import com.opensimulationplatform.validator.model.modeldefinition.Plug;
import com.opensimulationplatform.validator.model.modeldefinition.Socket;
import com.opensimulationplatform.osp.model.OspOntologyObjectProperties;
import com.opensimulationplatform.owl.helper.OwlHelper;
import com.opensimulationplatform.owl.model.OwlConfiguration;
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
