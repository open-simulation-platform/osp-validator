package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.osp.model.OspOntologyClasses;
import com.opensimulationplatform.core.osp.model.OspOntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.validator.model.ospmodeldescription.Simulator;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.core.owl.converter.NameConverter.getIndividualName;

class SimulatorConverter {
  public static void convert(Simulator simulator, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual simulatorIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(simulator));
    OwlHelper.addClassAssertionAxiom(ontology, simulatorIndividual, OspOntologyClasses.MODEL);
    owlConfiguration.addSimulator(simulatorIndividual, simulator);
    
    simulator.getPlugs().forEach((plugName, plug) -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, OspOntologyObjectProperties.MODEL_HAS_TYPED_SIGNAL_CONNECTOR, plugIndividual);
    });
    
    simulator.getSockets().forEach((socketName, socket) -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, OspOntologyObjectProperties.MODEL_HAS_TYPED_SIGNAL_CONNECTOR, socketIndividual);
    });
    
    simulator.getBonds().forEach((bondName, bond) -> {
      OWLNamedIndividual bondIndividual = BondConverter.convert(bond, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, OspOntologyObjectProperties.MODEL_HAS_TYPED_BOND_CONNECTOR, bondIndividual);
    });
  }
}
