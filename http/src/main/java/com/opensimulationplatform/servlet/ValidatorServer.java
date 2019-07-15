package com.opensimulationplatform.servlet;

import com.opensimulationplatform.loghelper.LogHelper;
import org.apache.commons.cli.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorServer {
  
  private static final Logger LOG = LoggerFactory.getLogger(ValidatorServer.class);
  
  private ValidatorServer(int port) throws Exception {
    Server server = new Server(port);
    ServletContextHandler handler = new ServletContextHandler(server, "/");
    handler.addServlet(ValidationServlet.class, "/validate");
  
    LOG.info("Starting msmi-validator-http server on port: " + port);
    LOG.info("Run validation checks using: http://<ip-address>:" + port + "/validate?configuration=/path/to/cse-config.json&ontology=/path/to/osp.owl");
    server.start();
  }
  
  public static void main(String[] args) throws Exception {
    LogHelper.setLogLevel();
    CommandLine cmd = parseCommandLineOptions(args);
    String port = cmd.getOptionValue("port");
    new ValidatorServer(Integer.parseInt(port));
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
      formatter.printHelp("msmi-validator-http", options);
    }
    
    return cmd;
  }
  
  private static Options addOptions() {
    Options options = new Options();
    
    Option input = new Option("p", "port", true, "Port the server will bind to");
    input.setRequired(true);
    options.addOption(input);
    
    return options;
  }
}
