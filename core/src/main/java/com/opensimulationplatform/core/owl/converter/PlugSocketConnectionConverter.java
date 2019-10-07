package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.systemstructure.OspPlugSocketConnection;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlSystemStructure;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

class PlugSocketConnectionConverter {
  public static void convert(OspPlugSocketConnection connection, OwlSystemStructure owlSystemStructure) {
    convert(connection.getOspPlug(), connection.getOspSocket(), owlSystemStructure);
  }
  
  public static void convert(OspPlug ospPlug, OspSocket ospSocket, OwlSystemStructure owlSystemStructure) {
    OWLOntology ontology = owlSystemStructure.getOntology();
  
    OWLNamedIndividual plugIndividual = PlugConverter.convert(ospPlug, owlSystemStructure);
    OWLNamedIndividual socketIndividual = SocketConverter.convert(ospSocket, owlSystemStructure);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, OntologyObjectProperties.HAS_SOCKET_MATE, socketIndividual);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, OntologyObjectProperties.HAS_PLUG_MATE, plugIndividual);
  }
}
