package com.opensimulationplatform.runner;

import com.opensimulationplatform.msmivalidator.MsmiValidator;
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
      System.exit(ExitCodes.INVALID_INPUT);
    }
    
    File ospOwlFile = new File(cmd.getOptionValue("osp-ontology"));
    if (!ospOwlFile.exists()) {
      LOG.error("Input file " + ospOwlFile.getAbsolutePath() + " does not exist!");
      System.exit(ExitCodes.INVALID_INPUT);
    }
    
    File cseConfigFile = new File(cmd.getOptionValue("cse-config"));
    if (!cseConfigFile.exists()) {
      LOG.error("Input file " + cseConfigFile.getAbsolutePath() + " does not exist!");
      System.exit(ExitCodes.INVALID_INPUT);
    }
    
    
    OWLOntology ontology = MsmiValidator.validate(ospOwlFile, cseConfigFile);
    if (ontology == null) {
      System.exit(ExitCodes.INVALID_CONFIGURATION);
    }
    
    if (cmd.hasOption("save")) {
      File configOwlFile = new File(cmd.getOptionValue("save"));
      if (!configOwlFile.exists()) {
        LOG.debug("Specified save directory: " + configOwlFile.getAbsolutePath() + " does not exist. Creating directory...");
        if (!configOwlFile.mkdirs()) {
          LOG.error("Error creating save directory: " + configOwlFile.getAbsolutePath());
          System.exit(ExitCodes.FILE_SYSTEM);
        } else {
          LOG.debug("Save directory: " + configOwlFile.getAbsolutePath() + " created successfully");
          LOG.info("Saving configuration ontology to: " + configOwlFile.getAbsolutePath());
          try {
            ontology.getOWLOntologyManager().saveOntology(ontology, IRI.create(configOwlFile));
          } catch (OWLOntologyStorageException e) {
            String message = "Error saving configuration ontology to: " + configOwlFile.getAbsolutePath();
            LOG.error(message, e);
            System.exit(ExitCodes.FILE_SYSTEM);
          }
        }
      }
    }
    
    LOG.info("exiting!");
    System.exit(ExitCodes.SUCCESS);
  }
  
  private class ExitCodes {
    static final int SUCCESS = 0;
    static final int INVALID_CONFIGURATION = 1;
    static final int INVALID_INPUT = 2;
    static final int FILE_SYSTEM = 3;
  }
}
