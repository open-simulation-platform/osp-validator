package com.opensimulationplatform.msmivalidator;

import com.opensimulationplatform.datamodel.Configuration;
import com.opensimulationplatform.datamodel.ConfigurationFactory;
import com.opensimulationplatform.hermitwrapper.HermitReasonerWrapper;
import com.opensimulationplatform.jsonmodel.parsing.ConfigurationJsonFileParser;
import com.opensimulationplatform.owlconverter.ConfigurationConverter;
import org.coode.owlapi.latex.LatexOWLObjectRenderer;
import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxInlineAxiomParser;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.util.SimpleRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxOWLObjectRendererImpl;
import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxRenderer;
import uk.ac.manchester.cs.owlapi.dlsyntax.DLSyntaxObjectRenderer;
import uk.ac.manchester.owl.owlapi.tutorialowled2011.Formatter;

import java.io.File;
import java.util.Set;

public class MsmiValidator {
  
  private static final Logger LOG = LoggerFactory.getLogger(MsmiValidator.class);
  
  public static Result validate(File ospOwlFile, File cseConfigFile) {
    LOG.debug("Checking:");
    LOG.debug("  > cse-config: " + cseConfigFile.getAbsolutePath());
    LOG.debug("Against:");
    LOG.debug("  > ontology:   " + ospOwlFile.getAbsolutePath());
    
    Configuration config = ConfigurationFactory.create(ConfigurationJsonFileParser.parse(cseConfigFile));
    
    OWLOntology configOntology = ConfigurationConverter.convert(config, ospOwlFile);
    
    HermitReasonerWrapper reasoner = new HermitReasonerWrapper(configOntology);
    
    if (!reasoner.isConsistent()) {
      LOG.debug("Configuration is inconsistent!");
      LOG.debug("Computing explanations for the inconsistency...");
      OWLObjectRenderer renderer = new ManchesterOWLSyntaxOWLObjectRendererImpl();
      Set<Set<OWLAxiom>> explanation = reasoner.getExplanation();
      explanation.forEach(axioms -> {
        LOG.error("------------------");
        LOG.error("Axioms causing the inconsistency: ");
        axioms.forEach(axiom -> {
          LOG.error(renderer.render(axiom.getAxiomWithoutAnnotations()));
        });
        LOG.error("------------------");
      });
      
      return new Result(explanation, false);
    } else {
      LOG.debug("Configuration is consistent!");
      
      return new Result(configOntology, true);
    }
  }
  
  public static class Result {
    
    private OWLOntology ontology;
    private Set<Set<OWLAxiom>> explanation;
    private boolean success;
    
    Result(OWLOntology ontology, boolean success) {
      this.ontology = ontology;
      this.success = success;
    }
    
    Result(Set<Set<OWLAxiom>> explanation, boolean success) {
      this.explanation = explanation;
      this.success = success;
    }
    
    public OWLOntology getOntology() {
      if (success) {
        return ontology;
      } else {
        throw new RuntimeException("MsmiValidator.Result.getOntology() can not be called when validation failed!");
      }
    }
    
    public boolean isSuccess() {
      return success;
    }
    
    public Set<Set<OWLAxiom>> getExplanation() {
      if (!success) {
        return explanation;
      } else {
        throw new RuntimeException("MsmiValidator.Result.getExplanation() can not be called when validation succeeded!");
      }
      
    }
  }
}
