package owlconverter;

import datamodel.Simulator;
import org.semanticweb.owlapi.model.*;
import owlhelper.OwlHelper;

import static ospontologydatamodel.OspOntologyClasses.*;
import static ospontologydatamodel.OspOntologyObjectProperties.*;

public class SimulatorConverter {
  public static void convert(Simulator simulator, OWLOntology ontology, PrefixManager prefixManager) {
    OWLNamedIndividual simulatorIndividual = OwlHelper.getNamedIndividual(ontology, simulator.getId(), prefixManager);
    
    OwlHelper.addClassAssertionAxiom(ontology, simulatorIndividual, MODEL, prefixManager);
    
    simulator.getPlugs().forEach((plugName, plug) -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, ontology, prefixManager);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, HAS_SIGNAL_CONNECTOR, plugIndividual, prefixManager);
    });
    
    simulator.getSockets().forEach((socketName, socket) -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, ontology, prefixManager);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, HAS_SIGNAL_CONNECTOR, socketIndividual, prefixManager);
    });
    
    simulator.getBonds().forEach((bondName, bond) -> {
      OWLNamedIndividual bondIndividual = BondConverter.convert(bond, ontology, prefixManager);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, HAS_BOND_CONNECTOR, bondIndividual, prefixManager);
    });
  }
}
