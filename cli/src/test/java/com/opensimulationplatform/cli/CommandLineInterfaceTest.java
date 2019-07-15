package com.opensimulationplatform.cli;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static com.opensimulationplatform.cli.CommandLineInterface.ExitCodes.*;
import static org.junit.Assert.assertTrue;

public class CommandLineInterfaceTest {
  
  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();
  
  @Rule
  public final TemporaryFolder tempFolder = new TemporaryFolder();
  
  @Test
  public void canCheckInvalidConfiguration() {
    exit.expectSystemExitWithStatus(INVALID_CONFIGURATION.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", "../core/src/test/resources/validator/osp.owl", "--cse-config", "../core/src/test/resources/validator/cse-config-invalid.json"});
  }
  
  @Test
  public void canCheckValidConfiguration() {
    exit.expectSystemExitWithStatus(SUCCESS.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", "../core/src/test/resources/validator/osp.owl", "--cse-config", "../core/src/test/resources/validator/cse-config-valid.json"});
  }
  
  @Test
  public void exitIfConfigFileDoesNotExist() {
    exit.expectSystemExitWithStatus(INVALID_INPUT.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", "../core/src/test/resources/validator/osp.owl", "--cse-config", "./i-do-not-exist.json"});
  }
  
  @Test
  public void exitIfOspOwlFileDoesNotExist() {
    exit.expectSystemExitWithStatus(INVALID_INPUT.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", "./i-do-not-exist.owl", "--cse-config", "../core/src/test/resources/validator/cse-config-valid.json"});
  }
  
  @Test
  public void exitIfInvalidLogLevelIsSet() {
    System.setProperty("msmi.validator.log.level", "invalid-level");
    exit.expectSystemExitWithStatus(INVALID_LOG_LEVEL.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", "../core/src/test/resources/validator/osp.owl", "--cse-config", "../core/src/test/resources/validator/cse-config-valid.json"});
  }
  
  @Test
  public void canSaveGeneratedOwlFile() throws IOException {
    File saveDir = tempFolder.newFolder();
    exit.expectSystemExitWithStatus(SUCCESS.getExitCode());
    exit.checkAssertionAfterwards(() -> assertTrue(new File(saveDir, "configuration.owl").exists()));
    CommandLineInterface.main(new String[]{"--osp-ontology", "../core/src/test/resources/validator/osp.owl", "--cse-config", "../core/src/test/resources/validator/cse-config-valid.json", "--save", saveDir.getAbsolutePath()});
  }
}