package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.modeldescription.OspBond;
import com.opensimulationplatform.core.model.modeldescription.OspPlug;
import com.opensimulationplatform.core.model.modeldescription.OspSocket;
import com.opensimulationplatform.core.model.systemstructure.OspBondConnection;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlSystemStructure;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.List;

class BondConnectionConverter {
  public static void convert(OspBondConnection connection, OwlSystemStructure owlSystemStructure) {
    convert(connection.getOspBondA(), connection.getOspBondB(), owlSystemStructure);
  }
  
  private static void convert(OspBond ospBondA, OspBond ospBondB, OwlSystemStructure owlSystemStructure) {
    OWLOntology ontology = owlSystemStructure.getOntology();
  
    OWLNamedIndividual bondAIndividual = BondConverter.convert(ospBondA, owlSystemStructure);
    OWLNamedIndividual bondBIndividual = BondConverter.convert(ospBondB, owlSystemStructure);
    
    OwlHelper.addClassAssertionAxiom(ontology, bondAIndividual, OntologyClasses.BOND_CONNECTOR_A);
    OwlHelper.addClassAssertionAxiom(ontology, bondBIndividual, OntologyClasses.BOND_CONNECTOR_B);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondAIndividual, OntologyObjectProperties.HAS_BOND_MATE_B, bondBIndividual);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondBIndividual, OntologyObjectProperties.HAS_BOND_MATE_A, bondAIndividual);
    
    List<OspPlug> aOspPlugs = ospBondA.getOspPlugs();
    List<OspSocket> bOspSockets = ospBondB.getOspSockets();
    for (int i = 0; i < aOspPlugs.size(); i++) {
      PlugSocketConnectionConverter.convert(aOspPlugs.get(i), bOspSockets.get(i), owlSystemStructure);
    }
    
    List<OspSocket> aOspSockets = ospBondA.getOspSockets();
    List<OspPlug> bOspPlugs = ospBondB.getOspPlugs();
    for (int i = 0; i < aOspSockets.size(); i++) {
      PlugSocketConnectionConverter.convert(bOspPlugs.get(i), aOspSockets.get(i), owlSystemStructure);
    }
  }
}
