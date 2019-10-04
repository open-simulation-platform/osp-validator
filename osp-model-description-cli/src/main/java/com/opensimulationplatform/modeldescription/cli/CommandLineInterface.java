package com.opensimulationplatform.modeldescription.cli;

import com.beust.jcommander.JCommander;
import com.opensimulationplatform.core.util.loghelper.LogHelper;
import com.opensimulationplatform.core.util.terminator.Terminator;
import com.opensimulationplatform.core.validator.modeldescription.ModelDescriptionValidator;
import com.opensimulationplatform.modeldescription.cli.jcommander.Arguments;
import com.opensimulationplatform.modeldescription.json.validator.JsonValidator;
import com.opensimulationplatform.modeldescription.xml.validator.XmlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static java.util.Objects.isNull;

public class CommandLineInterface {
  private static final Logger LOG = LoggerFactory.getLogger(CommandLineInterface.class);
  
  public static void main(String[] args) {
    setLogLevel();
    Arguments arguments = getArguments(args);
    
    File ospModelDescription = arguments.getOspModelDescription();
    File fmu = arguments.getFmu();
    File ospOntology = arguments.getOspOntology();
    ModelDescriptionValidator.Result result = validate(ospModelDescription, fmu, ospOntology);
    
    evaluateResult(result, arguments);
  }
  
  private static ModelDescriptionValidator.Result validate(File ospModelDescriptionFile, File fmu, File ospOwlFile) {
    if (ospModelDescriptionFile.getAbsolutePath().endsWith(".json")) {
      if (isNull(ospOwlFile)) {
        return JsonValidator.validate(ospModelDescriptionFile, fmu);
      } else {
        return JsonValidator.validate(ospModelDescriptionFile, fmu, ospOwlFile);
      }
    } else if (ospModelDescriptionFile.getAbsolutePath().endsWith(".xml")) {
      if (isNull(ospOwlFile)) {
        return XmlValidator.validate(ospModelDescriptionFile, fmu);
      } else {
        return XmlValidator.validate(ospModelDescriptionFile, fmu, ospOwlFile);
      }
    } else {
      LOG.error("Can only validate json or xml system structure files");
      Terminator.exit(ExitCodes.INVALID_INPUT);
      throw new RuntimeException("This should never happen");
    }
  }
  
  private static void evaluateResult(ModelDescriptionValidator.Result result, Arguments arguments) {
    if (result.isValid()) {
      Terminator.exit(ExitCodes.SUCCESS);
    } else {
      LOG.error("OSP model description file " + arguments.getOspModelDescription().getAbsolutePath() + " is not valid");
      LOG.error("#--- Messages ---#");
      result.getMessages().forEach(LOG::error);
      LOG.error("#----------------#");
      Terminator.exit(ExitCodes.INVALID_CONFIGURATION);
    }
  }
  
  private static void setLogLevel() {
    if (!LogHelper.setLogLevelFromOspSystemProperty()) {
      Terminator.exit(ExitCodes.INVALID_LOG_LEVEL);
    }
  }
  
  private static Arguments getArguments(String[] args) {
    Arguments arguments = new Arguments();
    try {
      JCommander.newBuilder().addObject(arguments).build().parse(args);
    } catch (Exception e) {
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }
    return arguments;
  }
}
