package com.opensimulationplatform.servlet;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationServerTest {
  @Test
  public void canStartAndStopServer() throws Exception {
    ValidationServer validationServer = new ValidationServer(8001);
    
    validationServer.start();
    assertTrue(validationServer.isRunning());
    
    validationServer.stop();
    assertFalse(validationServer.isRunning());
  }
}