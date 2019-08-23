package com.opensimulationplatform.http;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.MultipartConfigElement;

class ValidationServer {
  
  private static final Logger LOG = LoggerFactory.getLogger(ValidationServer.class);
  
  private final Server server;
  private final int port;
  
  ValidationServer(int port) {
    this.port = port;
    server = new Server(port);
  }
  
  void start() throws Exception {
    ServletHolder holder = new ServletHolder(new ValidationServlet());
    holder.getRegistration().setMultipartConfig(new MultipartConfigElement("data/tmp"));
    ServletContextHandler handler = new ServletContextHandler(server, "/");
    handler.addServlet(holder, "/validate");
    
    LOG.info("Starting msmi-validator-http server on port: " + port);
    LOG.info("Run validation checks using HTTP GET: http://localhost:" + port + "/validate?configuration=/path/to/cse-config.json&ontology=/path/to/osp.owl");
    LOG.info("Run validation checks using HTTP POST: http://localhost:" + port + "/validate with \"configuration\" and \"ontology\" as form params");
    server.start();
  }
  
  void stop() throws Exception {
    LOG.info("Stopping msmi-validator-http server on port: " + port);
    server.stop();
  }
  
  boolean isRunning() {
    return server.isRunning();
  }
}
