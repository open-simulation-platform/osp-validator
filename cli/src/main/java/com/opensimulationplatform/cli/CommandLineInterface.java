package com.opensimulationplatform.cli;

import com.opensimulationplatform.loghelper.LogHelper;
import com.opensimulationplatform.msmivalidator.MsmiValidator;
import com.opensimulationplatform.terminator.ExitCode;
import com.opensimulationplatform.terminator.Terminator;
import org.apache.commons.cli.*;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxOWLObjectRendererImpl;

import java.io.File;
import java.util.Set;

class CommandLineInterface {
  
  private static final Logger LOG = LoggerFactory.getLogger(CommandLineInterface.class);
  
  public static void main(String[] args) {
    if(!LogHelper.setLogLevel()){
      Terminator.exit(ExitCodes.INVALID_LOG_LEVEL);
    }
    
    CommandLine cmd = parseCommandLineOptions(args);
    
    File ospOwlFile = new File(cmd.getOptionValue("osp-ontology"));
    File cseConfigFile = new File(cmd.getOptionValue("cse-config"));
    String saveOption = cmd.getOptionValue("save");
    
    validateInput(ospOwlFile, cseConfigFile);
    
    MsmiValidator.Result result = MsmiValidator.validate(ospOwlFile, cseConfigFile);
    if (!result.isSuccess()) {
      LOG.error("Validation of: " + cseConfigFile.getAbsolutePath() + " based on: " + ospOwlFile.getAbsolutePath() + " failed!");
      
      Set<Set<OWLAxiom>> explanation = result.getExplanations();
      OWLObjectRenderer renderer = new ManchesterOWLSyntaxOWLObjectRendererImpl();
      explanation.forEach(axioms -> {
        LOG.error("------------------");
        LOG.error("Axioms causing the inconsistency: ");
        axioms.forEach(axiom -> LOG.error(renderer.render(axiom.getAxiomWithoutAnnotations())));
        LOG.error("------------------");
      });
      
      Terminator.exit(ExitCodes.INVALID_CONFIGURATION);
    }
    
    if (saveOption != null) {
      saveConfigOwlFile(result, saveOption);
    }
    
    Terminator.exit(ExitCodes.SUCCESS);
  }
  
  private static void validateInput(File ospOwlFile, File cseConfigFile) {
    if (!ospOwlFile.exists()) {
      LOG.error("Input file " + ospOwlFile.getAbsolutePath() + " does not exist!");
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }
    
    if (!cseConfigFile.exists()) {
      LOG.error("Input file " + cseConfigFile.getAbsolutePath() + " does not exist!");
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }
  }
  
  private static void saveConfigOwlFile(MsmiValidator.Result result, String saveOption) {
    File saveDirectory = new File(saveOption);
    if (!saveDirectory.exists()) {
      LOG.debug("Specified save directory: " + saveDirectory.getAbsolutePath() + " does not exist. Creating directory...");
      if (!saveDirectory.mkdirs()) {
        LOG.error("Error creating save directory: " + saveDirectory.getAbsolutePath());
        Terminator.exit(ExitCodes.FILE_SYSTEM);
      } else {
        LOG.debug("Save directory: " + saveDirectory.getAbsolutePath() + " created successfully");
      }
    }
    File configOwlFile = new File(saveDirectory, "configuration.owl");
    LOG.debug("Saving configuration ontology to: " + configOwlFile.getAbsolutePath());
    try {
      OWLOntology ontology = result.getOwlConfiguration().getOntology();
      ontology.getOWLOntologyManager().saveOntology(ontology, IRI.create(configOwlFile));
      LOG.debug("done!");
    } catch (OWLOntologyStorageException e) {
      String message = "Error saving configuration ontology to: " + configOwlFile.getAbsolutePath();
      LOG.error(message, e);
      Terminator.exit(ExitCodes.FILE_SYSTEM);
    }
  }
  
  private static CommandLine parseCommandLineOptions(String[] args) {
    Options options = addOptions();
    
    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine cmd = null;
    try {
      cmd = parser.parse(options, args);
    } catch (Exception e) {
      LOG.error("Error parsing input arguments", e);
      formatter.printHelp("msmi-validator", options);
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }
    
    return cmd;
  }
  
  private static Options addOptions() {
    Options options = new Options();
    
    Option input = new Option("i1", "osp-ontology", true, "Path to osp.owl file");
    input.setRequired(true);
    options.addOption(input);
    
    input = new Option("i2", "cse-config", true, "Path to cse-config.json file");
    input.setRequired(true);
    options.addOption(input);
    
    input = new Option("s", "save", true, "Path to directory where configuration.owl should be saved. If not specified, configuration ontology will not be saved to file");
    input.setRequired(false);
    options.addOption(input);
    return options;
  }
  
  static class ExitCodes {
    static final ExitCode SUCCESS = new ExitCode(0, "SUCCESS");
    static final ExitCode INVALID_CONFIGURATION = new ExitCode(1, "INVALID_CONFIGURATION");
    static final ExitCode INVALID_INPUT = new ExitCode(2, "INVALID_INPUT");
    static final ExitCode FILE_SYSTEM = new ExitCode(3, "FILE_SYSTEM");
    static final ExitCode INVALID_LOG_LEVEL = new ExitCode(4, "INVALID_LOG_LEVEL");
  }
}
