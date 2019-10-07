package com.opensimulationplatform.systemstructure.cli;

import com.opensimulationplatform.core.util.loghelper.LogHelper;
import com.opensimulationplatform.core.util.terminator.ExitCode;
import com.opensimulationplatform.core.util.terminator.Terminator;
import com.opensimulationplatform.core.validator.systemstructure.SystemStructureValidator;
import com.opensimulationplatform.systemstructure.xml.validator.XmlValidator;
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

import static java.util.Objects.nonNull;

class CommandLineInterface {
  
  private static final Logger LOG = LoggerFactory.getLogger(CommandLineInterface.class);
  
  public static void main(String[] args) {
    if (!LogHelper.setLogLevelFromOspSystemProperty()) {
      Terminator.exit(ExitCodes.INVALID_LOG_LEVEL);
    }
    
    CommandLine cmd = parseCommandLineOptions(args);
    File ospSystemStructureFile = getRequiredConfigurationFile(cmd);
    File ospOwlFile = getOptionalOntologyFile(cmd);
  
    SystemStructureValidator.Result result = validate(ospSystemStructureFile, ospOwlFile);
  
    if (!result.isSuccess()) {
      if (nonNull(ospOwlFile)) {
        LOG.error("Validation of: " + ospSystemStructureFile.getAbsolutePath() + " based on: " + ospOwlFile.getAbsolutePath() + " failed!");
      } else {
        LOG.error("Validation of: " + ospSystemStructureFile.getAbsolutePath() + " based on default ontology failed!");
      }
      
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
    
    String saveOption = cmd.getOptionValue("save");
    if (nonNull(saveOption)) {
      saveConfigOwlFile(result, new File(saveOption));
    }
    
    Terminator.exit(ExitCodes.SUCCESS);
  }
  
  private static SystemStructureValidator.Result validate(File ospSystemStructureFile, File ospOwlFile) {
    if (nonNull(ospOwlFile)) {
      return XmlValidator.validate(ospSystemStructureFile, ospOwlFile);
    } else {
      return XmlValidator.validate(ospSystemStructureFile);
    }
  }
  
  private static File getOptionalOntologyFile(CommandLine cmd) {
    String ontologyValue = cmd.getOptionValue("ontology");
    if (nonNull(ontologyValue)) {
      File file = new File(ontologyValue);
      LOG.trace("Using input defined ontology from: " + file.getAbsolutePath());
      if (!file.exists()) {
        LOG.error("Input file " + file.getAbsolutePath() + " does not exist!");
        Terminator.exit(ExitCodes.INVALID_INPUT);
      }
      return file;
    } else {
      LOG.trace("Ontology input not specified, using default");
      return null;
    }
  }
  
  private static File getRequiredConfigurationFile(CommandLine cmd) {
    File file = new File(cmd.getOptionValue("config"));
    
    if (!file.exists()) {
      LOG.error("Input file " + file.getAbsolutePath() + " does not exist!");
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }
    
    if (!supportedFileType(file)) {
      LOG.error("Input file format not supported!");
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }
    
    return file;
  }
  
  private static boolean supportedFileType(File file) {
    return file.getAbsolutePath().endsWith(".json") || file.getAbsolutePath().endsWith(".xml");
  }
  
  private static void saveConfigOwlFile(SystemStructureValidator.Result result, File saveDirectory) {
    if (!saveDirectory.exists()) {
      LOG.trace("Specified save directory: " + saveDirectory.getAbsolutePath() + " does not exist. Creating directory...");
      if (!saveDirectory.mkdirs()) {
        LOG.error("Error creating save directory: " + saveDirectory.getAbsolutePath());
        Terminator.exit(ExitCodes.FILE_SYSTEM);
      } else {
        LOG.trace("Save directory: " + saveDirectory.getAbsolutePath() + " created successfully");
      }
    }
    File configOwlFile = new File(saveDirectory, "configuration.owl");
    LOG.trace("Saving configuration ontology to: " + configOwlFile.getAbsolutePath());
    try {
      OWLOntology ontology = result.getOwlConfiguration().getOntology();
      ontology.getOWLOntologyManager().saveOntology(ontology, IRI.create(configOwlFile));
      LOG.trace("done!");
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
    
    Option input = new Option("c", "config", true, "Path to cse-config.json file");
    
    input.setRequired(true);
    options.addOption(input);
    
    input = new Option("o", "ontology", true, "Path to osp.owl file. Default is to use osp.owl inside .jar file");
    input.setRequired(false);
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
