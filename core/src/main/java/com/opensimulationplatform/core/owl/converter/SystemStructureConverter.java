package com.opensimulationplatform.core.owl.converter;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.owl.model.OwlSystemStructure;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

public class SystemStructureConverter {
  public static OwlSystemStructure convert(SystemStructure systemStructure, File ospOwlFile) {
    try {
      OwlSystemStructure owlSystemStructure = new OwlSystemStructure();
      
      OWLOntology ospOntology = OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(ospOwlFile);
      OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
      OWLOntology configurationOntology = manager.createOntology(ospOntology.getAxioms(), ospOntology.getOntologyID().getOntologyIRI());
      owlSystemStructure.setOntology(configurationOntology);
      
      systemStructure.getOspSimulators().forEach(simulator -> SimulatorConverter.convert(simulator, owlSystemStructure));
  
      systemStructure.getOspPlugSocketConnections().forEach(connection -> PlugSocketConnectionConverter.convert(connection, owlSystemStructure));
  
      systemStructure.getOspBondConnections().forEach(connection -> BondConnectionConverter.convert(connection, owlSystemStructure));
  
      owlSystemStructure.setSystemStructure(systemStructure);
      return owlSystemStructure;
    } catch (OWLOntologyCreationException e) {
      throw new RuntimeException("Error loading the osp ontology from: " + ospOwlFile.getAbsolutePath(), e);
    }
  }
}
