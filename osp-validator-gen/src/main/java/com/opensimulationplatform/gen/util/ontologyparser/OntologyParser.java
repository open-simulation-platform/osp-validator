package com.opensimulationplatform.gen.util.ontologyparser;

import com.opensimulationplatform.gen.util.ontologycontent.OntologyContent;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;

public class OntologyParser {
  public static OntologyContent parse(File ontologyFile) {
    try {
      OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
      OWLOntology ontology = manager.loadOntologyFromOntologyDocument(ontologyFile);
      return new OntologyContent(ontology);
    } catch (OWLOntologyCreationException e) {
      throw new RuntimeException("Unable to load ontology from: " + ontologyFile.getAbsolutePath(), e);
    }
  }
}
