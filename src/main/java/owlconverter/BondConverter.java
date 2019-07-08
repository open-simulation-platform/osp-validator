package owlconverter;

import datamodel.Bond;
import org.semanticweb.owlapi.model.*;
import owlhelper.OwlHelper;

import static ospontologydatamodel.OspOntologyClasses.BOND_CONNECTOR;
import static ospontologydatamodel.OspOntologyObjectProperties.HAS_SIGNAL_CONNECTOR;

public class BondConverter {
  public static OWLNamedIndividual convert(Bond bond, OWLOntology ontology, PrefixManager prefixManager) {
    OWLNamedIndividual bondIndividual = OwlHelper.getNamedIndividual(ontology, bond.getId(), prefixManager);
    
    OwlHelper.addClassAssertionAxiom(ontology, bondIndividual, BOND_CONNECTOR, prefixManager);
  
    bond.getPlugs().forEach(plug -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, ontology, prefixManager);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondIndividual, HAS_SIGNAL_CONNECTOR, plugIndividual, prefixManager);
    });
  
    bond.getSockets().forEach(socket -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, ontology, prefixManager);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, bondIndividual, HAS_SIGNAL_CONNECTOR, socketIndividual, prefixManager);
    });
  
    return bondIndividual;
  }
}
