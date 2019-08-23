package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.osp.model.OspOntologyClasses;
import com.opensimulationplatform.core.osp.model.OspOntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.validator.model.configuration.BondConnection;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.Bond;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.Plug;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.Socket;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import java.util.List;

class BondConnectionConverter {
  
  public static void convert(BondConnection connection, OwlConfiguration owlConfiguration) {
    convert(connection.getBondA(), connection.getBondB(), owlConfiguration);
  }
  
  private static void convert(Bond bondA, Bond bondB, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
  
    OWLNamedIndividual bondAIndividual = BondConverter.convert(bondA, owlConfiguration);
    OWLNamedIndividual bondBIndividual = BondConverter.convert(bondB, owlConfiguration);
    
    OwlHelper.addClassAssertionAxiom(ontology, bondAIndividual, OspOntologyClasses.BOND_CONNECTOR_A);
    OwlHelper.addClassAssertionAxiom(ontology, bondBIndividual, OspOntologyClasses.BOND_CONNECTOR_B);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondAIndividual, OspOntologyObjectProperties.HAS_BOND_MATE_B, bondBIndividual);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondBIndividual, OspOntologyObjectProperties.HAS_BOND_MATE_A, bondAIndividual);
    
    List<Plug> aPlugs = bondA.getPlugs();
    List<Socket> bSockets = bondB.getSockets();
    for (int i = 0; i < aPlugs.size(); i++) {
      PlugSocketConnectionConverter.convert(aPlugs.get(i), bSockets.get(i), owlConfiguration);
    }
    
    List<Socket> aSockets = bondA.getSockets();
    List<Plug> bPlugs = bondB.getPlugs();
    for (int i = 0; i < aSockets.size(); i++) {
      PlugSocketConnectionConverter.convert(bPlugs.get(i), aSockets.get(i), owlConfiguration);
    }
  }
}
