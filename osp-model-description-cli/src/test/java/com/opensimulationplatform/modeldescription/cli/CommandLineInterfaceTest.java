package com.opensimulationplatform.modeldescription.cli;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.TemporaryFolder;

import static com.opensimulationplatform.modeldescription.cli.ExitCodes.*;

public class CommandLineInterfaceTest {
  
  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();
  
  @Rule
  public final TemporaryFolder tempFolder = new TemporaryFolder();
  private final String ontology = TestResources.OSP_OWL.toFile().getAbsolutePath();
  private final String fmu = TestResources.CRANE_CONTROLLER_FMU.toFile().getAbsolutePath();
  private final String invalidModelDescription = TestResources.CRANE_CONTROLLER_INVALID.toFile().getAbsolutePath();
  private final String validModelDescription = TestResources.CRANE_CONTROLLER_VALID.toFile().getAbsolutePath();
  
  @Test
  public void canCheckInvalidModelDescription() {
    exit.expectSystemExitWithStatus(INVALID_CONFIGURATION.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", ontology, "--osp-model-description", invalidModelDescription, "--fmu", fmu});
  }
  
  @Test
  public void canCheckValidModelDescription() {
    exit.expectSystemExitWithStatus(SUCCESS.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", ontology, "--osp-model-description", validModelDescription, "--fmu", fmu});
  }
  
  @Test
  public void exitIfModelDescriptionFileDoesNotExist() {
    exit.expectSystemExitWithStatus(INVALID_INPUT.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", ontology, "--osp-model-description", "./i-do-not-exist.json", "--fmu", fmu});
  }
  
  @Test
  public void exitIfOspOwlFileDoesNotExist() {
    exit.expectSystemExitWithStatus(INVALID_INPUT.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", "./i-do-not-exist.owl", "--osp-model-description", validModelDescription, "--fmu", fmu});
  }
  
  @Test
  public void exitIfInvalidLogLevelIsSet() {
    System.setProperty("osp.validator.log.level", "invalid-level");
    exit.expectSystemExitWithStatus(INVALID_LOG_LEVEL.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", ontology, "--osp-model-description", validModelDescription, "--fmu", fmu});
  }
  
  @Test
  public void canValidateWithDefaultOwlFile() {
    exit.expectSystemExitWithStatus(SUCCESS.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-model-description", validModelDescription, "--fmu", fmu});
  }
  
  @Test
  public void canValidateWithSpecifiedOwlFile() {
    exit.expectSystemExitWithStatus(SUCCESS.getExitCode());
    CommandLineInterface.main(new String[]{"--osp-ontology", ontology, "--osp-model-description", validModelDescription, "--fmu", fmu});
  }
}
