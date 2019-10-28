package com.opensimulationplatform.systemstructure.cli;

import com.opensimulationplatform.core.util.resource.Resources;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.TemporaryFolder;

import static com.opensimulationplatform.systemstructure.cli.CommandLineInterface.ExitCodes.*;

public class CommandLineInterfaceTest {
  
  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();
  
  @Rule
  public final TemporaryFolder tempFolder = new TemporaryFolder();
  
  private final String ontology = Resources.OSP_OWL.toFile().getAbsolutePath();
  private final String invalidConfig = TestResources.SYSTEM_STRUCTURE_INVALID_XML.getAbsolutePath();
  private final String validConfig = TestResources.SYSTEM_STRUCTURE_VALID_XML.getAbsolutePath();
  
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