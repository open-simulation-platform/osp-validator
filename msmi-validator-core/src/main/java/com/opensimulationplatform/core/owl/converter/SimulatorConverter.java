package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlSystemStructure;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

class SimulatorConverter {
  public static void convert(OspSimulator ospSimulator, OwlSystemStructure owlSystemStructure) {
    OWLOntology ontology = owlSystemStructure.getOntology();
    
    OWLNamedIndividual simulatorIndividual = OwlHelper.getNamedIndividual(ontology, IndividualNameGenerator.getIndividualName(ospSimulator));
    OwlHelper.addClassAssertionAxiom(ontology, simulatorIndividual, OntologyClasses.MODEL);
    owlSystemStructure.addSimulator(simulatorIndividual, ospSimulator);
    
    ospSimulator.getPlugs().forEach((plugName, plug) -> {
      OWLNamedIndividual plugIndividual = PlugConverter.convert(plug, owlSystemStructure);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, OntologyObjectProperties.MODEL_HAS_TYPED_SIGNAL_CONNECTOR, plugIndividual);
    });
    
    ospSimulator.getSockets().forEach((socketName, socket) -> {
      OWLNamedIndividual socketIndividual = SocketConverter.convert(socket, owlSystemStructure);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, OntologyObjectProperties.MODEL_HAS_TYPED_SIGNAL_CONNECTOR, socketIndividual);
    });
    
    ospSimulator.getBonds().forEach((bondName, bond) -> {
      OWLNamedIndividual bondIndividual = BondConverter.convert(bond, owlSystemStructure);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, OntologyObjectProperties.MODEL_HAS_TYPED_BOND_CONNECTOR, bondIndividual);
    });
  }
}
