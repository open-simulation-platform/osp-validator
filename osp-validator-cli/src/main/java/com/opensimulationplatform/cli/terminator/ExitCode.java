/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.cli.terminator;

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
