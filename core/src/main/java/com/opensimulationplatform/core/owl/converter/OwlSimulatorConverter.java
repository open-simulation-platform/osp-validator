package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.systemstructure.OspSimulator;
import com.opensimulationplatform.core.ontology.model.OntologyClasses;
import com.opensimulationplatform.core.ontology.model.OntologyObjectProperties;
import com.opensimulationplatform.core.owl.helper.OwlHelper;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

class OwlSimulatorConverter {
  public static void convert(OspSimulator ospSimulator, OwlConfiguration owlConfiguration) {
    OWLOntology ontology = owlConfiguration.getOntology();
    
    OWLNamedIndividual simulatorIndividual = OwlHelper.getNamedIndividual(ontology, IndividualNameGenerator.getIndividualName(ospSimulator));
    OwlHelper.addClassAssertionAxiom(ontology, simulatorIndividual, OntologyClasses.MODEL);
    owlConfiguration.addSimulator(simulatorIndividual, ospSimulator);
    
    ospSimulator.getPlugs().forEach((plugName, plug) -> {
      OWLNamedIndividual plugIndividual = OwlPlugConverter.convert(plug, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, OntologyObjectProperties.MODEL_HAS_TYPED_SIGNAL_CONNECTOR, plugIndividual);
    });
    
    ospSimulator.getSockets().forEach((socketName, socket) -> {
      OWLNamedIndividual socketIndividual = OwlSocketConverter.convert(socket, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, OntologyObjectProperties.MODEL_HAS_TYPED_SIGNAL_CONNECTOR, socketIndividual);
    });
    
    ospSimulator.getBonds().forEach((bondName, bond) -> {
      OWLNamedIndividual bondIndividual = OwlBondConverter.convert(bond, owlConfiguration);
      OwlHelper.addObjectPropertyAssertionAxiom(ontology, simulatorIndividual, OntologyObjectProperties.MODEL_HAS_TYPED_BOND_CONNECTOR, bondIndividual);
    });
  }
}
