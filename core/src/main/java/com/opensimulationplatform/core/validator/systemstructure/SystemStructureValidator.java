package com.opensimulationplatform.core.validator.systemstructure;

import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.owl.converter.SystemStructureConverter;
import com.opensimulationplatform.core.owl.model.OwlSystemStructure;
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
    OwlSystemStructure owlSystemStructure = SystemStructureConverter.convert(systemStructure, ospOwlFile);
    HermitReasonerWrapper reasoner = new HermitReasonerWrapper(owlSystemStructure.getOntology());
    
    if (!reasoner.isConsistent()) {
      LOG.error("Configuration is inconsistent!");
      return new Result(owlSystemStructure, reasoner.getExplanations());
    } else {
      LOG.debug("Configuration is consistent!");
      return new Result(owlSystemStructure);
    }
  }
  
  public static Result validate(SystemStructure systemStructure) {
    return validate(systemStructure, Resources.OSP_OWL.toFile());
  }
  
  public static class Result {
    private final OwlSystemStructure owlSystemStructure;
    private final Set<Set<OWLAxiom>> explanations;
    private final boolean success;
    
    private Result(OwlSystemStructure owlSystemStructure, Set<Set<OWLAxiom>> explanations, boolean success) {
      this.owlSystemStructure = owlSystemStructure;
      this.explanations = explanations;
      this.success = success;
    }
    
    Result(OwlSystemStructure owlSystemStructure) {
      this(owlSystemStructure, new HashSet<>(new HashSet<>()), true);
    }
    
    Result(OwlSystemStructure owlSystemStructure, Set<Set<OWLAxiom>> explanations) {
      this(owlSystemStructure, explanations, false);
    }
    
    public OwlSystemStructure getOwlSystemStructure() {
      return owlSystemStructure;
    }
    
    public boolean isSuccess() {
      return success;
    }
    
    public Set<Set<OWLAxiom>> getExplanations() {
      return explanations;
    }
  }
}
