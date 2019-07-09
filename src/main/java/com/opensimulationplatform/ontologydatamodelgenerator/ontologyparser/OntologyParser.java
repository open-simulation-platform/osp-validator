package com.opensimulationplatform.ontologydatamodelgenerator.ontologyparser;

import com.opensimulationplatform.ontologydatamodelgenerator.ontologycontent.OntologyContent;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

public class OntologyParser {
  public static OntologyContent parse(File ontologyFile) {
    OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
    
    OWLOntology ontology;
    try {
      ontology = manager.loadOntologyFromOntologyDocument(ontologyFile);
    } catch (OWLOntologyCreationException e) {
      throw new RuntimeException("Unable to load ontolog from: " + ontologyFile.getAbsolutePath(), e);
    }
    
    return new OntologyContent(ontology);
  }
}
