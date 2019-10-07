package com.opensimulationplatform.core.validator.systemstructure;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.owl.converter.OwlConfigurationConverter;
import com.opensimulationplatform.core.owl.model.OwlConfiguration;
import com.opensimulationplatform.core.owl.util.hermitwrapper.HermitReasonerWrapper;
import com.opensimulationplatform.core.util.resource.Resources;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class SystemStructureValidator {
  private static final Logger LOG = LoggerFactory.getLogger(SystemStructureValidator.class);
  
  public static Result validate(SystemStructure systemStructure, File ospOwlFile) {
    OwlConfiguration owlConfiguration = OwlConfigurationConverter.convert(systemStructure, ospOwlFile);
    HermitReasonerWrapper reasoner = new HermitReasonerWrapper(owlConfiguration.getOntology());
    
    if (!reasoner.isConsistent()) {
      LOG.error("Configuration is inconsistent!");
      return new Result(owlConfiguration, reasoner.getExplanations());
    } else {
      LOG.debug("Configuration is consistent!");
      return new Result(owlConfiguration);
    }
  }
  
  public static Result validate(SystemStructure systemStructure) {
    return validate(systemStructure, Resources.OSP_OWL.toFile());
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
