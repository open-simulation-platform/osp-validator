package owlconverter;

import datamodel.Simulator;
import org.semanticweb.owlapi.model.*;
import ospontologydatamodel.OspOntologyClasses;
import ospontologydatamodel.OspOntologyObjectProperties;

public class SimulatorConverter {
  public static void convert(Simulator simulator, OWLOntology ontology, PrefixManager prefixManager) {
    OWLOntologyManager manager = ontology.getOWLOntologyManager();
    OWLDataFactory dataFactory = manager.getOWLDataFactory();
    
    OWLNamedIndividual simulatorIndividual = dataFactory.getOWLNamedIndividual(simulator.getId(), prefixManager);
    OWLClass modelClass = dataFactory.getOWLClass(OspOntologyClasses.MODEL, prefixManager);
    manager.addAxiom(ontology, dataFactory.getOWLClassAssertionAxiom(modelClass, simulatorIndividual));
    
    simulator.getPlugs().forEach((plugName, plug) -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, ontology, prefixManager);
      OWLObjectProperty hasSignalConnector = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_SIGNAL_CONNECTOR, prefixManager);
      manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasSignalConnector, simulatorIndividual, plugIndividual));
    });
    
    simulator.getSockets().forEach((socketName, socket) -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, ontology, prefixManager);
      OWLObjectProperty hasSignalConnector = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_SIGNAL_CONNECTOR, prefixManager);
      manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasSignalConnector, simulatorIndividual, socketIndividual));
    });
    
    simulator.getBonds().forEach((bondName, bond) -> {
      OWLNamedIndividual bondIndividual = BondConverter.convert(bond, ontology, prefixManager);
      OWLObjectProperty hasBondConnector = dataFactory.getOWLObjectProperty(OspOntologyObjectProperties.HAS_BOND_CONNECTOR, prefixManager);
      manager.addAxiom(ontology, dataFactory.getOWLObjectPropertyAssertionAxiom(hasBondConnector, simulatorIndividual, bondIndividual));
    });
  }
}
