package com.opensimulationplatform.modeldescription.cli;

import com.opensimulationplatform.core.util.terminator.ExitCode;

class ExitCodes {
  static final ExitCode SUCCESS = new ExitCode(0, "SUCCESS");
  static final ExitCode INVALID_CONFIGURATION = new ExitCode(1, "INVALID_CONFIGURATION");
  static final ExitCode INVALID_INPUT = new ExitCode(2, "INVALID_INPUT");
  static final ExitCode INVALID_LOG_LEVEL = new ExitCode(3, "INVALID_LOG_LEVEL");
}
