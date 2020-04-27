package com.opensimulationplatform.modeldescription.cli;

import com.beust.jcommander.JCommander;
import com.opensimulationplatform.core.util.loghelper.LogHelper;
import com.opensimulationplatform.core.util.terminator.Terminator;
import com.opensimulationplatform.core.validation.result.Result;
import com.opensimulationplatform.modeldescription.cli.jcommander.Arguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class CommandLineInterface {
  private static final Logger LOG = LoggerFactory.getLogger(CommandLineInterface.class);
  
  public static void main(String[] args) {
    setLogLevel();
    Arguments arguments = getArguments(args);
    
    File ospModelDescription = arguments.getOspModelDescription();
    File fmu = arguments.getFmu();
    Result result = validate(ospModelDescription, fmu);
    
    evaluateResult(result, arguments);
  }
  
  private static Result validate(File ospModelDescriptionFile, File fmu) {
    return XmlValidator.validate(ospModelDescriptionFile, fmu);
  }
  
  private static void evaluateResult(Result result, Arguments arguments) {
    if (result.isValid()) {
      Terminator.exit(ExitCodes.SUCCESS);
    } else {
      LOG.error("OspModelDescription file " + arguments.getOspModelDescription().getAbsolutePath() + " is not valid");
      LOG.error("#--- Messages ---#");
      result.getDiagnostics().forEach(d -> LOG.error(d.getMessage()));
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
    JCommander jcommander = JCommander.newBuilder().addObject(arguments).build();
    jcommander.setProgramName("java -jar osp-model-description-cli-<version>-jar-with-dependencies.jar");
    try {
      jcommander.parse(args);
    } catch (Exception e) {
      LOG.error("Error parsing input arguments");
      jcommander.usage();
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }
    return arguments;
  }
}
