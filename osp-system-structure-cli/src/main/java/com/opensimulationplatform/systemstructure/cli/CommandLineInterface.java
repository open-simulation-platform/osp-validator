package com.opensimulationplatform.systemstructure.cli;

import com.opensimulationplatform.core.util.loghelper.LogHelper;
import com.opensimulationplatform.core.util.terminator.ExitCode;
import com.opensimulationplatform.core.util.terminator.Terminator;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

class CommandLineInterface {

  private static final Logger LOG = LoggerFactory.getLogger(CommandLineInterface.class);

  public static void main(String[] args) {
    if (!LogHelper.setLogLevelFromOspSystemProperty()) {
      Terminator.exit(ExitCodes.INVALID_LOG_LEVEL);
    }

    CommandLine cmd = parseCommandLineOptions(args);
    File ospSystemStructureFile = getRequiredConfigurationFile(cmd);

    Terminator.exit(ExitCodes.SUCCESS);
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
    return file.getAbsolutePath().endsWith(".xml");
  }

  private static CommandLine parseCommandLineOptions(String[] args) {
    Options options = addOptions();

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine cmd = null;
    try {
      cmd = parser.parse(options, args);
    } catch (Exception e) {
      LOG.error("Error parsing input arguments");
      formatter.printHelp("java -jar osp-system-structure-cli-<version>-jar-with-dependencies.jar", options);
      Terminator.exit(ExitCodes.INVALID_INPUT);
    }

    return cmd;
  }

  private static Options addOptions() {
    Options options = new Options();

    Option input = new Option("c", "config", true, "Path to OspSystemStructure.xml file");

    input.setRequired(true);
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
