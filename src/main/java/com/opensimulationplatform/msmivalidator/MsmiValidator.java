package com.opensimulationplatform.msmivalidator;

import com.opensimulationplatform.datamodel.configuration.Configuration;
import com.opensimulationplatform.datamodel.configuration.ConfigurationFactory;
import com.opensimulationplatform.hermitwrapper.HermitReasonerWrapper;
import com.opensimulationplatform.jsonmodel.parsing.ConfigurationJsonFileParser;
import com.opensimulationplatform.owlconverter.ConfigurationConverter;
import com.opensimulationplatform.owlmodel.OwlConfiguration;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class MsmiValidator {
  
  private static final Logger LOG = LoggerFactory.getLogger(MsmiValidator.class);
  
  public static Result validate(File ospOwlFile, File cseConfigFile) {
    LOG.debug("Checking:");
    LOG.debug("  > cse-config: " + cseConfigFile.getAbsolutePath());
    LOG.debug("Against:");
    LOG.debug("  > ontology:   " + ospOwlFile.getAbsolutePath());
    
    Configuration config = ConfigurationFactory.create(ConfigurationJsonFileParser.parse(cseConfigFile));
    
    OwlConfiguration owlConfig = ConfigurationConverter.convert(config, ospOwlFile);
    
    HermitReasonerWrapper reasoner = new HermitReasonerWrapper(owlConfig.getOntology());
    
    if (!reasoner.isConsistent()) {
      LOG.debug("Configuration is inconsistent!");
      return new Result(owlConfig, reasoner.getExplanation());
    } else {
      LOG.debug("Configuration is consistent!");
      return new Result(owlConfig);
    }
  }
  
  public static class Result {
    
    private OwlConfiguration owlConfiguration;
    private Set<Set<OWLAxiom>> explanation;
    private boolean success;
  
    private Result(OwlConfiguration owlConfiguration, Set<Set<OWLAxiom>> explanation, boolean success) {
      this.owlConfiguration = owlConfiguration;
      this.explanation = explanation;
      this.success = success;
    }
    
    Result(OwlConfiguration owlConfiguration) {
      this(owlConfiguration, new HashSet<>(new HashSet<>()), true);
    }
    
    Result(OwlConfiguration owlConfiguration, Set<Set<OWLAxiom>> explanation) {
      this(owlConfiguration, explanation, false);
    }
    
    public OwlConfiguration getOwlConfiguration() {
      return owlConfiguration;
    }
    
    public boolean isSuccess() {
      return success;
    }
    
    public Set<Set<OWLAxiom>> getExplanation() {
      return explanation;
    }
  }
}
