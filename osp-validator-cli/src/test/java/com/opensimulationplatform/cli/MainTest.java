package com.opensimulationplatform.cli;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class MainTest {

  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  @Test
  public void canValidate() {
    exit.expectSystemExitWithStatus(0);
    Main.main(new String[]{"-osp_system_structure", "./src/test/resources/OspSystemStructure_Crane.xml"});
  }

  @Test
  public void getVersion() {
    exit.expectSystemExitWithStatus(0);
    Main.main(new String[]{"-v"});
  }
}