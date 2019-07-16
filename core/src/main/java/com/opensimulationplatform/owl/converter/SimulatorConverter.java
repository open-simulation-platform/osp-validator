package com.opensimulationplatform.owl.converter;

import com.opensimulationplatform.msmivalidator.model.modeldefinition.Simulator;
import com.opensimulationplatform.owl.helper.OwlHelper;
import com.opensimulationplatform.owl.model.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import static com.opensimulationplatform.osp.model.OspOntologyClasses.MODEL;
import static com.opensimulationplatform.osp.model.OspOntologyObjectProperties.MODEL_HAS_TYPED_BOND_CONNECTOR;
import static com.opensimulationplatform.osp.model.OspOntologyObjectProperties.MODEL_HAS_TYPED_SIGNAL_CONNECTOR;
import static com.opensimulationplatform.owl.converter.NameConverter.getIndividualName;

class SimulatorConverter {
  public static void convert(Simulator simulator, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual simulatorIndividual = OwlHelper.getNamedIndividual(ontology, getIndividualName(simulator));
    OwlHelper.addClassAssertionAxiom(ontology, simulatorIndividual, MODEL);
    owlConfiguration.addSimulator(simulatorIndividual, simulator);
    
    simulator.getPlugs().forEach((plugName, plug) -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, MODEL_HAS_TYPED_SIGNAL_CONNECTOR, plugIndividual);
    });
    
    simulator.getSockets().forEach((socketName, socket) -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, MODEL_HAS_TYPED_SIGNAL_CONNECTOR, socketIndividual);
    });
    
    simulator.getBonds().forEach((bondName, bond) -> {
      OWLNamedIndividual bondIndividual = BondConverter.convert(bond, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, MODEL_HAS_TYPED_BOND_CONNECTOR, bondIndividual);
    });
  }
}
