package com.opensimulationplatform.owlconverter;

import com.opensimulationplatform.datamodel.configuration.Configuration;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

public class ConfigurationConverter {
  public static OWLOntology convert(Configuration configuration, File ospOwlFile) {
    try {
      OWLOntology ospOntology = OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(ospOwlFile);
      OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
      OWLOntology configurationOntology = manager.createOntology(ospOntology.getAxioms(), ospOntology.getOntologyID().getOntologyIRI());
  
      configuration.getSimulators().forEach((simulatorName, simulator) -> {
        SimulatorConverter.convert(simulator, configurationOntology);
      });
  
      configuration.getPlugSocketConnections().forEach(connection -> {
        PlugSocketConnectionConverter.convert(connection, configurationOntology);
      });
  
      configuration.getBondConnections().forEach(connection -> {
        BondConnectionConverter.convert(connection, configurationOntology);
      });
      
      return configurationOntology;
    } catch (OWLOntologyCreationException e) {
      throw new RuntimeException("Error loading the osp ontology from: " + ospOwlFile.getAbsolutePath(), e);
    }
  }
}
