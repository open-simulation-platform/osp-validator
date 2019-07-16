package com.opensimulationplatform.util.terminator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Terminator {
  
  private static final Logger LOG = LoggerFactory.getLogger(Terminator.class);
  
  public static void exit(ExitCode exitCode) {
    LOG.debug("Exiting...");
    LOG.debug(exitCode.toString());
    
    System.exit(exitCode.getExitCode());
  }
}
