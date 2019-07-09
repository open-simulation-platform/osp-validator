package com.opensimulationplatform.msmivalidator;

import com.opensimulationplatform.datamodel.Configuration;
import com.opensimulationplatform.hermitwrapper.HermitReasonerWrapper;
import com.opensimulationplatform.jsonmodel.parsing.ConfigurationJsonFileParser;
import com.opensimulationplatform.owlconverter.ConfigurationConverter;
import com.opensimulationplatform.datamodel.ConfigurationFactory;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MsmiValidator {
  
  private static final Logger LOG = LoggerFactory.getLogger(MsmiValidator.class);
  
  public static OWLOntology validate(File ospOwlFile, File cseConfigFile) {
    LOG.info("Checking:");
    LOG.info("  > cse-config: " + cseConfigFile.getAbsolutePath());
    LOG.info("Against:");
    LOG.info("  > ontology:   " + ospOwlFile.getAbsolutePath());
  
    Configuration config = ConfigurationFactory.create(ConfigurationJsonFileParser.parse(cseConfigFile));
  
    OWLOntology configOntology = ConfigurationConverter.convert(config, ospOwlFile);
  
    HermitReasonerWrapper reasoner = new HermitReasonerWrapper(configOntology);
    
    if (!reasoner.isConsistent()) {
      LOG.info("Configuration is inconsistent!");
      LOG.info("Computing explanations for the inconsistency...");
      reasoner.getExplanation().forEach(axioms -> {
        LOG.error("------------------");
        LOG.error("Axioms causing the inconsistency: ");
        axioms.forEach(axiom -> LOG.error(axiom.toString()));
        LOG.error("------------------");
      });
      
      return null;
    } else {
      LOG.info("Configuration is consistent!");
      
      return configOntology;
    }
  }
}
