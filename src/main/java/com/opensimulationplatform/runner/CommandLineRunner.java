package com.opensimulationplatform.runner;

import com.opensimulationplatform.msmivalidator.MsmiValidator;
import com.opensimulationplatform.terminator.ExitCode;
import com.opensimulationplatform.terminator.Terminator;
import org.apache.commons.cli.*;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CommandLineRunner {
  
  private static final Logger LOG = LoggerFactory.getLogger(CommandLineRunner.class);
  
  public static void main(String[] args) {
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
    
    File ospOwlFile = new File(cmd.getOptionValue("osp-ontology"));
    if (!ospOwlFile.exists()) {
      LOG.error("Input file " + ospOwlFile.getAbsolutePath() + " does not exist!");
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }
    
    File cseConfigFile = new File(cmd.getOptionValue("cse-config"));
    if (!cseConfigFile.exists()) {
      LOG.error("Input file " + cseConfigFile.getAbsolutePath() + " does not exist!");
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }
    
    MsmiValidator.Result result = MsmiValidator.validate(ospOwlFile, cseConfigFile);
    if (!result.isSuccess()) {
      LOG.error("Validation failed!");
      Terminator.exit(ExitCodes.INVALID_CONFIGURATION);
    }
    
    String saveOption = cmd.getOptionValue("save");
    
    if (saveOption != null) {
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
        OWLOntology ontology = result.getOntology();
        ontology.getOWLOntologyManager().saveOntology(ontology, IRI.create(configOwlFile));
        LOG.debug("done!");
      } catch (OWLOntologyStorageException e) {
        String message = "Error saving configuration ontology to: " + configOwlFile.getAbsolutePath();
        LOG.error(message, e);
        Terminator.exit(ExitCodes.FILE_SYSTEM);
      }
    }
    
    Terminator.exit(ExitCodes.SUCCESS);
  }
  
  private static class ExitCodes {
    static final ExitCode SUCCESS = new ExitCode(0,"SUCCESS");
    static final ExitCode INVALID_CONFIGURATION = new ExitCode(1,"INVALID_CONFIGURATION");
    static final ExitCode INVALID_INPUT = new ExitCode(2,"INVALID_INPUT");
    static final ExitCode FILE_SYSTEM = new ExitCode(3,"FILE_SYSTEM");
  }
}
