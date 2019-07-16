package com.opensimulationplatform.msmivalidator;

import com.opensimulationplatform.hermitwrapper.HermitReasonerWrapper;
import com.opensimulationplatform.json.model.parsing.ConfigurationJsonFileParser;
import com.opensimulationplatform.msmivalidator.model.configuration.Configuration;
import com.opensimulationplatform.msmivalidator.model.configuration.ConfigurationFactory;
import com.opensimulationplatform.owl.converter.ConfigurationConverter;
import com.opensimulationplatform.owl.model.OwlConfiguration;
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
      LOG.error("Configuration is inconsistent!");
      return new Result(owlConfig, reasoner.getExplanations());
    } else {
      LOG.debug("Configuration is consistent!");
      return new Result(owlConfig);
    }
  }
  
  public static class Result {
    
    private final OwlConfiguration owlConfiguration;
    private final Set<Set<OWLAxiom>> explanations;
    private final boolean success;
  
    private Result(OwlConfiguration owlConfiguration, Set<Set<OWLAxiom>> explanations, boolean success) {
      this.owlConfiguration = owlConfiguration;
      this.explanations = explanations;
      this.success = success;
    }
    
    Result(OwlConfiguration owlConfiguration) {
      this(owlConfiguration, new HashSet<>(new HashSet<>()), true);
    }
    
    Result(OwlConfiguration owlConfiguration, Set<Set<OWLAxiom>> explanations) {
      this(owlConfiguration, explanations, false);
    }
    
    public OwlConfiguration getOwlConfiguration() {
      return owlConfiguration;
    }
    
    public boolean isSuccess() {
      return success;
    }
    
    public Set<Set<OWLAxiom>> getExplanations() {
      return explanations;
    }
  }
}
