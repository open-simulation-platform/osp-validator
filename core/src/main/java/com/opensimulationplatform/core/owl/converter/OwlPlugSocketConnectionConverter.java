package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.systemstructure.OspPlugSocketConnection;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

class OwlPlugSocketConnectionConverter {
  public static void convert(OspPlugSocketConnection connection, OwlConfiguration owlConfiguration) {
    convert(connection.getOspPlug(), connection.getOspSocket(), owlConfiguration);
  }
  
  public static void convert(OspPlug ospPlug, OspSocket ospSocket, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
  
    OWLNamedIndividual plugIndividual = OwlPlugConverter.convert(ospPlug, owlConfiguration);
    OWLNamedIndividual socketIndividual = OwlSocketConverter.convert(ospSocket, owlConfiguration);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, plugIndividual, OntologyObjectProperties.HAS_SOCKET_MATE, socketIndividual);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, socketIndividual, OntologyObjectProperties.HAS_PLUG_MATE, plugIndividual);
  }
}
