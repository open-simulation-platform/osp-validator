package com.opensimulationplatform.cseconfig.util.testrunner;

import com.opensimulationplatform.core.util.terminator.ExitCode;
import com.opensimulationplatform.core.util.terminator.Terminator;
import com.opensimulationplatform.cseconfig.util.explanationinterpreter.ExplanationInterpreter;
import com.opensimulationplatform.cseconfig.validator.CseConfigurationValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxOWLObjectRendererImpl;

import java.io.File;
import java.util.Set;

class TestRunner {
  
  private static final Logger LOG = LoggerFactory.getLogger(TestRunner.class);
  
  public static void main(String[] args) {
    Configurator.setLevel(System.getProperty("log4j.logger"), Level.DEBUG);
    
    File ospOwlFile = new File("./src/main/resources/osp.owl");
    File cseConfigFile = new File("./src/test/resources/validator/cse-config-valid.json");
    
    CseConfigurationValidator.Result result = CseConfigurationValidator.validate(ospOwlFile, cseConfigFile);
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
      Set<Set<OWLAxiom>> explanations = result.getExplanations();
      LOG.error("Computing explanations for the inconsistency...");
      OWLObjectRenderer renderer = new ManchesterOWLSyntaxOWLObjectRendererImpl();
      explanations.forEach(axioms -> {
        LOG.error("------------------");
        LOG.error("Axioms causing the inconsistency: ");
        axioms.forEach(axiom -> LOG.error(renderer.render(axiom.getAxiomWithoutAnnotations())));
        LOG.error("------------------");
        LOG.error("Explanation: ");
        try {
          ExplanationInterpreter.interpret(result, axioms);
        } catch (OWLOntologyCreationException e) {
          String message = "Error explaining the inconsistency";
          LOG.error(message, e);
          throw new RuntimeException(e);
        }
        LOG.error("------------------");
      });
      Terminator.exit(new ExitCode(1, "Validation failed!"));
    }
  }
}
