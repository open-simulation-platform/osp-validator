package owlconverter;

import datamodel.Configuration;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

import java.io.File;

public class ConfigurationConverter {
  public static OWLOntology convert(Configuration configuration, File ospOwlFile) {
    try {
      OWLOntology ospOntology = OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(ospOwlFile);
      OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
      PrefixManager prefixManager = new DefaultPrefixManager(ospOntology.getOntologyID().getOntologyIRI().toString());
      OWLOntology configurationOntology = manager.createOntology(ospOntology.getAxioms(), ospOntology.getOntologyID().getOntologyIRI());
  
      configuration.getSimulators().forEach((simulatorName, simulator) -> {
        SimulatorConverter.convert(simulator, configurationOntology, prefixManager);
      });
  
      configuration.getPlugSocketConnections().forEach(connection -> {
        PlugSocketConnectionConverter.convert(connection, configurationOntology, prefixManager);
      });
  
      configuration.getBondConnections().forEach(connection -> {
        BondConnectionConverter.convert(connection, configurationOntology, prefixManager);
      });
      
      return configurationOntology;
    } catch (OWLOntologyCreationException e) {
      throw new RuntimeException("Error loading the osp ontology from: " + ospOwlFile.getAbsolutePath(), e);
    }
  }
}
