package com.opensimulationplatform.runner;

import com.opensimulationplatform.msmivalidator.MsmiValidator;
import com.opensimulationplatform.terminator.ExitCode;
import com.opensimulationplatform.terminator.Terminator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class TestRunner {
  
  private static final Logger LOG = LoggerFactory.getLogger(TestRunner.class);
  
  public static void main(String[] args) {
    Configurator.setLevel(System.getProperty("log4j.logger"), Level.ALL);
  
    File ospOwlFile = new File("./src/test/resources/validator/osp.owl");
    File cseConfigFile = new File("./src/test/resources/validator/cse-config-valid.json");
    
    MsmiValidator.Result result = MsmiValidator.validate(ospOwlFile, cseConfigFile);
    File configOwlFile = new File("./configuration.owl");
    LOG.debug("Storing configuration ontology to: " + configOwlFile.getAbsolutePath());
    try {
      OWLOntology ontology = result.getOwlConfiguration().getOntology();
      ontology.getOWLOntologyManager().saveOntology(ontology, IRI.create(configOwlFile));
      LOG.debug("done!");
    } catch (OWLOntologyStorageException e) {
      String message = "Error saving configuration ontology to: " + configOwlFile.getAbsolutePath();
      LOG.error(message, e);
      throw new RuntimeException(e);
    }
  
    if (result.isSuccess()) {
      Terminator.exit(new ExitCode(0, "Great success!"));
    } else {
      Terminator.exit(new ExitCode(1, "Validation failed!"));
    }
  }
}
