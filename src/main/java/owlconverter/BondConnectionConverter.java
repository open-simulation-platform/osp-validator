package owlconverter;

import datamodel.Bond;
import datamodel.BondConnection;
import datamodel.Plug;
import datamodel.Socket;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import owlhelper.OwlHelper;

import java.util.List;

import static ospontologydatamodel.OspOntologyClasses.BOND_CONNECTOR_A;
import static ospontologydatamodel.OspOntologyClasses.BOND_CONNECTOR_B;
import static ospontologydatamodel.OspOntologyObjectProperties.HAS_BOND_MATE_A;
import static ospontologydatamodel.OspOntologyObjectProperties.HAS_BOND_MATE_B;

public class BondConnectionConverter {
  private static final Logger LOG = LoggerFactory.getLogger(BondConnectionConverter.class);
  
  public static void convert(BondConnection connection, OWLOntology ontology) {
    convert(connection.getBondA(), connection.getBondB(), ontology);
  }
  
  public static void convert(Bond bondA, Bond bondB, OWLOntology ontology) {
//    LOG.debug();
    
    OWLNamedIndividual bondAIndividual = BondConverter.convert(bondA, ontology);
    OWLNamedIndividual bondBIndividual = BondConverter.convert(bondB, ontology);
    
    OwlHelper.addClassAssertionAxiom(ontology, bondAIndividual, BOND_CONNECTOR_A);
    OwlHelper.addClassAssertionAxiom(ontology, bondBIndividual, BOND_CONNECTOR_B);
    
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondAIndividual, HAS_BOND_MATE_B, bondBIndividual);
    OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondBIndividual, HAS_BOND_MATE_A, bondAIndividual);
    
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
