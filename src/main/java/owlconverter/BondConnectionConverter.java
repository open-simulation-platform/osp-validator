package owlconverter;

import datamodel.Bond;
import datamodel.BondConnection;
import datamodel.Plug;
import datamodel.Socket;
import org.semanticweb.owlapi.model.*;
import ospontologydatamodel.OspOntologyObjectProperties;

import java.util.List;

public class BondConnectionConverter {
  public static void convert(BondConnection connection, OWLOntology ontology, PrefixManager prefixManager) {
    convert(connection.getBondA(), connection.getBondB(), ontology, prefixManager);
  }
  
  public static void convert(Bond bondA, Bond bondB, OWLOntology ontology, PrefixManager prefixManager) {
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = manager.getOWLDataFactory();
    
    OWLNamedIndividual bondAIndividual = BondConverter.convert(bondA, ontology, prefixManager);
    
    OWLNamedIndividual bondBIndividual = BondConverter.convert(bondB, ontology, prefixManager);
    
    OWLObjectProperty hasBondMateB = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_BOND_MATE_B, prefixManager);
    manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasBondMateB, bondAIndividual, bondBIndividual));
    
    OWLObjectProperty hasBondMateA = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_BOND_MATE_A, prefixManager);
    manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasBondMateA, bondBIndividual, bondAIndividual));
    
    List<Plug> aPlugs = bondA.getPlugs();
    List<Socket> bSockets = bondB.getSockets();
    for (int i = 0; i < aPlugs.size(); i++) {
      PlugSocketConnectionConverter.convert(aPlugs.get(i), bSockets.get(i), ontology, prefixManager);
    }
    
    List<Socket> aSockets = bondA.getSockets();
    List<Plug> bPlugs = bondB.getPlugs();
    for (int i = 0; i < aSockets.size(); i++) {
      PlugSocketConnectionConverter.convert(bPlugs.get(i), aSockets.get(i), ontology, prefixManager);
    }
  }
}
