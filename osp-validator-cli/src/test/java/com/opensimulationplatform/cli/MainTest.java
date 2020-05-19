package com.opensimulationplatform.cli;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class MainTest {

  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  @Test
  public void canValidateOspSystemStructure() {
    exit.expectSystemExitWithStatus(0);
    Main.main(new String[]{"osp-system-structure", "-file", "./src/test/resources/OspSystemStructure_Crane.xml"});
  }

  @Test
  public void canValidateOspModelDescription() {
    exit.expectSystemExitWithStatus(0);
    Main.main(new String[]{"osp-model-description",
        "-file", "./src/test/resources/KnuckleBoomCrane_OspModelDescription.xml",
        "-fmu", "./src/test/resources/KnuckleBoomCrane.fmu"});
  }

  @Test
  public void handlesNonExistingOspSystemStructure() {
    exit.expectSystemExitWithStatus(-1);
    Main.main(new String[]{"osp-system-structure", "-file", "does-not-exist"});
  }

  @Test
  public void handlesNonExistingOspModelDescription() {
    exit.expectSystemExitWithStatus(-1);
    Main.main(new String[]{"osp-model-description",
        "-file", "does-not-exist",
        "-fmu", "./src/test/resources/CraneController.fmu"});
  }

  @Test
  public void handlesNonExistingFmu() {
    exit.expectSystemExitWithStatus(-1);
    Main.main(new String[]{"osp-model-description",
        "-file", "./src/test/resources/CraneController_OspModelDescription.xml",
        "-fmu", "does-not-exist"});
  }

  @Test
  public void getVersion() {
    exit.expectSystemExitWithStatus(0);
    Main.main(new String[]{"-v"});
  }
}