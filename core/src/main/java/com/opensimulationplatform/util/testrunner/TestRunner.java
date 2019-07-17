package com.opensimulationplatform.util.testrunner;

import com.opensimulationplatform.validator.ExplanationInterpreter;
import com.opensimulationplatform.validator.MsmiValidator;
import com.opensimulationplatform.util.terminator.ExitCode;
import com.opensimulationplatform.util.terminator.Terminator;
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
    Configurator.setLevel(System.getProperty("log4j.logger"), Level.ALL);
    
    File ospOwlFile = new File("../core/src/test/resources/validator/osp.owl");
    File cseConfigFile = new File("../core/src/test/resources/validator/cse-config-invalid.json");

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
      Set<Set<OWLAxiom>> explanation = result.getExplanations();
      LOG.error("Computing explanations for the inconsistency...");
      OWLObjectRenderer renderer = new ManchesterOWLSyntaxOWLObjectRendererImpl();
      explanation.forEach(axioms -> {
        LOG.error("------------------");
        LOG.error("Axioms causing the inconsistency: ");
        axioms.forEach(axiom -> LOG.error(renderer.render(axiom.getAxiomWithoutAnnotations())));
        LOG.error("------------------");
        LOG.error("Explanation: ");
        try {
          ExplanationInterpreter.interpret(result, axioms);
        } catch (OWLOntologyCreationException e) {
          String message = "Error explaning the inconsistency";
          LOG.error(message, e);
          throw new RuntimeException(e);
        }
        LOG.error("------------------");
      });
      Terminator.exit(new ExitCode(1, "Validation failed!"));
    }
  }
}
