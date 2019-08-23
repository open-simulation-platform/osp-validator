package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.osp.model.OspOntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.validator.model.configuration.PlugSocketConnection;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.Plug;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.Socket;
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
