package com.opensimulationplatform.cseconfig.http;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidationServerMainTest {
  @Test
  public void getDefaultPortIfNotSpecified() {
    int port = ValidationServerMain.getPort(ValidationServerMain.parseCommandLineOptions(new String[]{""}));
    assertEquals(8001, port);
  }
  
  @Test
  public void getPortIfSpecified() {
    int port = ValidationServerMain.getPort(ValidationServerMain.parseCommandLineOptions(new String[]{"--port", "1234"}));
    assertEquals(1234, port);
    
    port = ValidationServerMain.getPort(ValidationServerMain.parseCommandLineOptions(new String[]{"-p", "1234"}));
    assertEquals(1234, port);
  }
}