package owlconverter;

import datamodel.Bond;
import datamodel.BondConnection;
import datamodel.Plug;
import datamodel.Socket;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.PrefixManager;
import owlhelper.OwlHelper;

import java.util.List;

import static ospontologydatamodel.OspOntologyClasses.BOND_CONNECTOR_A;
import static ospontologydatamodel.OspOntologyClasses.BOND_CONNECTOR_B;
import static ospontologydatamodel.OspOntologyObjectProperties.HAS_BOND_MATE_A;
import static ospontologydatamodel.OspOntologyObjectProperties.HAS_BOND_MATE_B;

public class BondConnectionConverter {
  public static void convert(BondConnection connection, OWLOntology ontology, PrefixManager prefixManager) {
    convert(connection.getBondA(), connection.getBondB(), ontology, prefixManager);
  }
  
  public static void convert(Bond bondA, Bond bondB, OWLOntology ontology, PrefixManager prefixManager) {
    OWLNamedIndividual bondAIndividual = BondConverter.convert(bondA, ontology, prefixManager);
    OWLNamedIndividual bondBIndividual = BondConverter.convert(bondB, ontology, prefixManager);
    
    OwlHelper.addClassAssertionAxiom(ontology, bondAIndividual, BOND_CONNECTOR_A, prefixManager);
    OwlHelper.addClassAssertionAxiom(ontology, bondBIndividual, BOND_CONNECTOR_B, prefixManager);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondAIndividual, HAS_BOND_MATE_B, bondBIndividual, prefixManager);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondBIndividual, HAS_BOND_MATE_A, bondAIndividual, prefixManager);
    
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
