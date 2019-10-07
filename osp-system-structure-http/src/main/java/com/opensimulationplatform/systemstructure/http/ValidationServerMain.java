package com.opensimulationplatform.systemstructure.http;

import com.opensimulationplatform.core.util.loghelper.LogHelper;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;

public class ValidationServerMain {
  
  private static final Logger LOG = LoggerFactory.getLogger(ValidationServerMain.class);
  
  public static void main(String[] args) throws Exception {
    LogHelper.setLogLevelFromOspSystemProperty();
    CommandLine cmd = parseCommandLineOptions(args);
    int port = getPort(cmd);
    new ValidationServer(port).start();
  }
  
  static int getPort(CommandLine cmd) {
    String port = cmd.getOptionValue("port");
    if (isNull(port)) {
      return 8001;
    } else {
      return Integer.parseInt(port);
    }
  }
  
  static CommandLine parseCommandLineOptions(String[] args) {
    Options options = addOptions();
    
    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine cmd = null;
    try {
      cmd = parser.parse(options, args);
    } catch (Exception e) {
      LOG.error("Error parsing input arguments", e);
      formatter.printHelp("msmi-validator-http", options);
    }
    
    return cmd;
  }
  
  private static Options addOptions() {
    Options options = new Options();
    
    Option input = new Option("p", "port", true, "Port the server will bind to");
    input.setRequired(false);
    options.addOption(input);
    
    return options;
  }
}
