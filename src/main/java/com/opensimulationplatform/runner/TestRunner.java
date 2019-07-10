package com.opensimulationplatform.runner;

import com.opensimulationplatform.msmivalidator.MsmiValidator;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TestRunner {
  
  private static final Logger LOG = LoggerFactory.getLogger(TestRunner.class);
  
  public static void main(String[] args) {
    File ospOwlFile = new File("./src/test/resources/validator/osp.owl");
    File cseConfigFile = new File("./src/test/resources/validator/cse-config.json");
    
    MsmiValidator.Result result = MsmiValidator.validate(ospOwlFile, cseConfigFile);
    if (result.isSuccess()) {
      System.exit(1);
    } else {
      File configOwlFile = new File("./configuration.owl");
      LOG.info("Storing configuration ontology to: " + configOwlFile.getAbsolutePath());
      try {
        OWLOntology ontology = result.getOntology();
        ontology.getOWLOntologyManager().saveOntology(ontology, IRI.create(configOwlFile));
      } catch (OWLOntologyStorageException e) {
        String message = "Error saving configuration ontology to: " + configOwlFile.getAbsolutePath();
        LOG.error(message, e);
        throw new RuntimeException(e);
      }
      LOG.info("exiting!");
      System.exit(0);
    }
  }
}
