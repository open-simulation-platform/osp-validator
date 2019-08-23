package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.validator.model.configuration.OspBondConnection;
import com.opensimulationplatform.core.validator.model.modeldescription.OspBond;
import com.opensimulationplatform.core.validator.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.validator.model.modeldescription.OspSocket;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.List;

class OwlBondConnectionConverter {
  public static void convert(OspBondConnection connection, OwlConfiguration owlConfiguration) {
    convert(connection.getOspBondA(), connection.getOspBondB(), owlConfiguration);
  }
  
  private static void convert(OspBond ospBondA, OspBond ospBondB, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
  
    OWLNamedIndividual bondAIndividual = OwlBondConverter.convert(ospBondA, owlConfiguration);
    OWLNamedIndividual bondBIndividual = OwlBondConverter.convert(ospBondB, owlConfiguration);
    
    OwlHelper.addClassAssertionAxiom(ontology, bondAIndividual, OntologyClasses.BOND_CONNECTOR_A);
    OwlHelper.addClassAssertionAxiom(ontology, bondBIndividual, OntologyClasses.BOND_CONNECTOR_B);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondAIndividual, OntologyObjectProperties.HAS_BOND_MATE_B, bondBIndividual);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondBIndividual, OntologyObjectProperties.HAS_BOND_MATE_A, bondAIndividual);
    
    List<OspPlug> aOspPlugs = ospBondA.getOspPlugs();
    List<OspSocket> bOspSockets = ospBondB.getOspSockets();
    for (int i = 0; i < aOspPlugs.size(); i++) {
      OwlPlugSocketConnectionConverter.convert(aOspPlugs.get(i), bOspSockets.get(i), owlConfiguration);
    }
    
    List<OspSocket> aOspSockets = ospBondA.getOspSockets();
    List<OspPlug> bOspPlugs = ospBondB.getOspPlugs();
    for (int i = 0; i < aOspSockets.size(); i++) {
      OwlPlugSocketConnectionConverter.convert(bOspPlugs.get(i), aOspSockets.get(i), owlConfiguration);
    }
  }
}
