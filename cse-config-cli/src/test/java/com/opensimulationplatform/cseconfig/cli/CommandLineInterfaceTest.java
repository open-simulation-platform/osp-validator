package com.opensimulationplatform.cseconfig.cli;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static com.opensimulationplatform.cseconfig.cli.CommandLineInterface.ExitCodes.*;
import static org.junit.Assert.assertTrue;

public class CommandLineInterfaceTest {
  
  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();
  
  @Rule
  public final TemporaryFolder tempFolder = new TemporaryFolder();
  private final String ontology = TestResources.OSP_OWL.toFile().getAbsolutePath();
  private final String invalidConfig = TestResources.CSE_CONFIG_INVALID_JSON.toFile().getAbsolutePath();
  private final String validConfig = TestResources.CSE_CONFIG_VALID_JSON.toFile().getAbsolutePath();
  
  @Test
  public void canCheckInvalidConfiguration() {
    exit.expectSystemExitWithStatus(INVALID_CONFIGURATION.getExitCode());
    CommandLineInterface.main(new String[]{"--ontology", ontology, "--config", invalidConfig});
  }
  
  @Test
  public void canCheckValidConfiguration() {
    exit.expectSystemExitWithStatus(SUCCESS.getExitCode());
    CommandLineInterface.main(new String[]{"--ontology", ontology, "--config", validConfig});
  }
  
  @Test
  public void exitIfConfigFileDoesNotExist() {
    exit.expectSystemExitWithStatus(INVALID_INPUT.getExitCode());
    CommandLineInterface.main(new String[]{"--ontology", ontology, "--config", "./i-do-not-exist.json"});
  }
  
  @Test
  public void exitIfOspOwlFileDoesNotExist() {
    exit.expectSystemExitWithStatus(INVALID_INPUT.getExitCode());
    CommandLineInterface.main(new String[]{"--ontology", "./i-do-not-exist.owl", "--config", validConfig});
  }
  
  @Test
  public void exitIfInvalidLogLevelIsSet() {
    System.setProperty("osp.validator.log.level", "invalid-level");
    exit.expectSystemExitWithStatus(INVALID_LOG_LEVEL.getExitCode());
    CommandLineInterface.main(new String[]{"--ontology", ontology, "--config", validConfig});
  }
  
  @Test
  public void canSaveGeneratedOwlFile() throws IOException {
    File saveDir = tempFolder.newFolder();
    exit.expectSystemExitWithStatus(SUCCESS.getExitCode());
    exit.checkAssertionAfterwards(() -> assertTrue(new File(saveDir, "configuration.owl").exists()));
    CommandLineInterface.main(new String[]{"--ontology", ontology, "--config", validConfig, "--save", saveDir.getAbsolutePath()});
  }
  
  @Test
  public void canValidateWithDefaultOwlFile() {
    exit.expectSystemExitWithStatus(SUCCESS.getExitCode());
    CommandLineInterface.main(new String[]{"--config", validConfig});
  }
  
  @Test
  public void canValidateWithSpecifiedOwlFile() {
    exit.expectSystemExitWithStatus(SUCCESS.getExitCode());
    CommandLineInterface.main(new String[]{"--ontology", ontology, "--config", validConfig});
  }
}