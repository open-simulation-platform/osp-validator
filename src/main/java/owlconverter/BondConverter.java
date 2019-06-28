package owlconverter;

import datamodel.Bond;
import org.semanticweb.owlapi.model.*;
import ospontologydatamodel.OspOntologyClasses;
import ospontologydatamodel.OspOntologyObjectProperties;

public class BondConverter {
  public static OWLNamedIndividual convert(Bond bond, OWLOntology ontology, PrefixManager prefixManager) {
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = manager.getOWLDataFactory();
    
    OWLNamedIndividual bondIndividual = dataFactory.getOWLNamedIndividual(bond.getId(), prefixManager);
    OWLClass bondClass = dataFactory.getOWLClass(OspOntologyClasses.BOND, prefixManager);
    manager.addAxiom(ontology, dataFactory.getOWLClassAssertionAxiom(bondClass, bondIndividual));
  
    bond.getPlugs().forEach(plug -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, ontology, prefixManager);
      OWLObjectProperty hasSignalConnector = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_SIGNAL_CONNECTOR, prefixManager);
      manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasSignalConnector, bondIndividual, plugIndividual));
    });
  
    bond.getSockets().forEach(socket -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, ontology, prefixManager);
      OWLObjectProperty hasSignalConnector = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_SIGNAL_CONNECTOR, prefixManager);
      manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasSignalConnector, bondIndividual, socketIndividual));
    });
  
    return bondIndividual;
  }
}
