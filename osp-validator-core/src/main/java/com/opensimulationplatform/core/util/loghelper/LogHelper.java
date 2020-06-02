/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.util.loghelper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

public class LogHelper {
  public static boolean setLogLevelFromOspSystemProperty() {
    String property = System.getProperty("osp.validator.log.level");
    if (property != null) {
      switch (property) {
        case "debug":
          setLogLevel(Level.DEBUG);
          break;
        case "trace":
          setLogLevel(Level.TRACE);
          break;
        case "error":
          setLogLevel(Level.ERROR);
          break;
        case "fatal":
          setLogLevel(Level.FATAL);
          break;
        case "all":
          setLogLevel(Level.ALL);
          break;
        case "info":
          setLogLevel(Level.INFO);
          break;
        case "warn":
          setLogLevel(Level.WARN);
          break;
        case "off":
          setLogLevel(Level.OFF);
          break;
        default:
          setLogLevel(Level.ALL);
          return false;
      }
    }
    return true;
  }

  public static void setLogLevel(Level level) {
    Configurator.setLevel(System.getProperty("log4j.logger"), level);
  }
}
