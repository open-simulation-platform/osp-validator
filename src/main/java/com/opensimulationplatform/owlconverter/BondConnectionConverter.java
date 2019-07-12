package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.configuration.BondConnection;
import com.opensimulationplatform.datamodel.modeldefinition.Bond;
import com.opensimulationplatform.datamodel.modeldefinition.Plug;
import com.opensimulationplatform.datamodel.modeldefinition.Socket;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyClasses;
import com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties;
import com.opensimulationplatform.owlhelper.OwlHelper;
import com.opensimulationplatform.owlmodel.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BondConnectionConverter {
  
  public static void convert(BondConnection connection, OwlConfiguration owlConfiguration) {
    convert(connection.getBondA(), connection.getBondB(), owlConfiguration);
  }
  
  public static void convert(Bond bondA, Bond bondB, OwlConfiguration owlConfiguration) {
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
