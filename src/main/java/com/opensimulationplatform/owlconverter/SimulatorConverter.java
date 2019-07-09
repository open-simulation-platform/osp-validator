package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.owlhelper.OwlHelper;
import com.opensimulationplatform.datamodel.Simulator;
import org.semanticweb.owlapi.model.*;

import static com.opensimulationplatform.ospontologydatamodel.OspOntologyClasses.*;
import static com.opensimulationplatform.ospontologydatamodel.OspOntologyObjectProperties.*;

public class SimulatorConverter {
  public static void convert(Simulator simulator, OWLOntology ontology) {
    OWLNamedIndividual simulatorIndividual = OwlHelper.getNamedIndividual(ontology, simulator.getId());
    
    OwlHelper.addClassAssertionAxiom(ontology, simulatorIndividual, MODEL);
    
    simulator.getPlugs().forEach((plugName, plug) -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, ontology);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, HAS_SIGNAL_CONNECTOR, plugIndividual);
    });
    
    simulator.getSockets().forEach((socketName, socket) -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, ontology);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, HAS_SIGNAL_CONNECTOR, socketIndividual);
    });
    
    simulator.getBonds().forEach((bondName, bond) -> {
      OWLNamedIndividual bondIndividual = BondConverter.convert(bond, ontology);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, HAS_BOND_CONNECTOR, bondIndividual);
    });
  }
}
