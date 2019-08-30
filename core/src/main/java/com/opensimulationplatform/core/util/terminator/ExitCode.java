package com.opensimulationplatform.core.util.terminator;

public class ExitCode {
  private final int exitCode;
  private final String description;
  
  public ExitCode(int exitCode, String description) {
    this.exitCode = exitCode;
    this.description = description;
  }
  
  public int getExitCode() {
    return exitCode;
  }
  
  public String getDescription() {
    return description;
  }
  
  @Override
  public String toString() {
    return "exitCode=" + exitCode + ", description='" + description + '\'';
  }
}
