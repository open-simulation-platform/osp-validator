package com.opensimulationplatform.cli.terminator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

public class TerminatorTest {

  @Rule
  public final ExpectedSystemExit exit = ExpectedSystemExit.none();

  @Test
  public void canTerminate() {
    ExitCode exitCode = new ExitCode(-1, "description");
    exit.expectSystemExitWithStatus(exitCode.getExitCode());
    Terminator.exit(exitCode);
  }
}