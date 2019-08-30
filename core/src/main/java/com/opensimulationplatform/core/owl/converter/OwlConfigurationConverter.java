package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.configuration.OspConfiguration;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

public class OwlConfigurationConverter {
  public static OwlConfiguration convert(OspConfiguration ospConfiguration, File ospOwlFile) {
    try {
      OwlConfiguration owlConfiguration = new OwlConfiguration();
      
      OWLOntology ospOntology = OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(ospOwlFile);
      OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
      OWLOntology configurationOntology = manager.createOntology(ospOntology.getAxioms(), ospOntology.getOntologyID().getOntologyIRI());
      owlConfiguration.setOntology(configurationOntology);
      
      ospConfiguration.getSimulators().forEach((simulatorName, simulator) -> OwlSimulatorConverter.convert(simulator, owlConfiguration));
  
      ospConfiguration.getOspPlugSocketConnections().forEach(connection -> OwlPlugSocketConnectionConverter.convert(connection, owlConfiguration));
  
      ospConfiguration.getOspBondConnections().forEach(connection -> OwlBondConnectionConverter.convert(connection, owlConfiguration));
  
      owlConfiguration.setOspConfiguration(ospConfiguration);
      return owlConfiguration;
    } catch (OWLOntologyCreationException e) {
      throw new RuntimeException("Error loading the osp ontology from: " + ospOwlFile.getAbsolutePath(), e);
    }
  }
}
