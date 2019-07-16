package com.opensimulationplatform.owl.converter;

import com.opensimulationplatform.validator.model.configuration.Configuration;
import com.opensimulationplatform.owl.model.OwlConfiguration;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

public class ConfigurationConverter {
  public static OwlConfiguration convert(Configuration configuration, File ospOwlFile) {
    try {
      OwlConfiguration owlConfiguration = new OwlConfiguration();
      
      OWLOntology ospOntology = OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(ospOwlFile);
      OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
      OWLOntology configurationOntology = manager.createOntology(ospOntology.getAxioms(), ospOntology.getOntologyID().getOntologyIRI());
      owlConfiguration.setOntology(configurationOntology);
      
      configuration.getSimulators().forEach((simulatorName, simulator) -> SimulatorConverter.convert(simulator, owlConfiguration));
  
      configuration.getPlugSocketConnections().forEach(connection -> PlugSocketConnectionConverter.convert(connection, owlConfiguration));
  
      configuration.getBondConnections().forEach(connection -> BondConnectionConverter.convert(connection, owlConfiguration));
  
      owlConfiguration.setConfiguration(configuration);
      return owlConfiguration;
    } catch (OWLOntologyCreationException e) {
      throw new RuntimeException("Error loading the osp ontology from: " + ospOwlFile.getAbsolutePath(), e);
    }
  }
}
