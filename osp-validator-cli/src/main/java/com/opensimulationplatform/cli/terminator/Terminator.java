/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.cli.terminator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Terminator {

  private static final Logger LOG = LoggerFactory.getLogger(Terminator.class);

  public static void exit(ExitCode exitCode) {
    LOG.debug("Exiting...");
    LOG.debug(exitCode.toString());
    System.exit(exitCode.getExitCode());
  }
}
