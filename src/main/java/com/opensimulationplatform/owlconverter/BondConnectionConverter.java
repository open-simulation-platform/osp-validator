package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.Bond;
import com.opensimulationplatform.datamodel.BondConnection;
import com.opensimulationplatform.datamodel.Plug;
import com.opensimulationplatform.datamodel.Socket;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyClasses;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties;
import com.opensimulationplatform.owlhelper.OwlHelper;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BondConnectionConverter {
  private static final Logger LOG = LoggerFactory.getLogger(BondConnectionConverter.class);
  
  public static void convert(BondConnection connection, OWLOntology ontology) {
    convert(connection.getBondA(), connection.getBondB(), ontology);
  }
  
  public static void convert(Bond bondA, Bond bondB, OWLOntology ontology) {
//    LOG.debug();
    
    OWLNamedIndividual bondAIndividual = BondConverter.convert(bondA, ontology);
    OWLNamedIndividual bondBIndividual = BondConverter.convert(bondB, ontology);
    
    OwlHelper.addClassAssertionAxiom(ontology, bondAIndividual, OspOntologyClasses.BOND_CONNECTOR_A);
    OwlHelper.addClassAssertionAxiom(ontology, bondBIndividual, OspOntologyClasses.BOND_CONNECTOR_B);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondAIndividual, OspOntologyObjectProperties.HAS_BOND_MATE_B, bondBIndividual);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondBIndividual, OspOntologyObjectProperties.HAS_BOND_MATE_A, bondAIndividual);
    
    List<Plug> aPlugs = bondA.getPlugs();
    List<Socket> bSockets = bondB.getSockets();
    for (int i = 0; i < aPlugs.size(); i++) {
      PlugSocketConnectionConverter.convert(aPlugs.get(i), bSockets.get(i), ontology);
    }
    
    List<Socket> aSockets = bondA.getSockets();
    List<Plug> bPlugs = bondB.getPlugs();
    for (int i = 0; i < aSockets.size(); i++) {
      PlugSocketConnectionConverter.convert(bPlugs.get(i), aSockets.get(i), ontology);
    }
  }
}
