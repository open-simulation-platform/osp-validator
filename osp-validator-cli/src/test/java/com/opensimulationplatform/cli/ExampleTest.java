package com.opensimulationplatform.cli;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class ExampleTest {

  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  @Test
  public void canValidateOspSystemStructure() {
    exit.expectSystemExitWithStatus(0);
    Main.main(new String[]{"osp-system-structure", "-file", "./src/main/resources/example/OspSystemStructure_Crane.xml"});
  }

  @Test
  public void canValidateKnuckleBoomCraneOspModelDescription() {
    exit.expectSystemExitWithStatus(0);
    Main.main(new String[]{"osp-model-description",
        "-file", "./src/main/resources/example/KnuckleBoomCrane_OspModelDescription.xml",
        "-fmu", "./src/main/resources/example/KnuckleBoomCrane.fmu"});
  }

  @Test
  public void canValidateCraneControllerOspModelDescription() {
    exit.expectSystemExitWithStatus(0);
    Main.main(new String[]{"osp-model-description",
        "-file", "./src/main/resources/example/CraneController_OspModelDescription.xml",
        "-fmu", "./src/main/resources/example/CraneController.fmu"});
  }
}
