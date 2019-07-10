package com.opensimulationplatform.terminator;

public class ExitCode {
  private int exitCode;
  private String description;
  
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
