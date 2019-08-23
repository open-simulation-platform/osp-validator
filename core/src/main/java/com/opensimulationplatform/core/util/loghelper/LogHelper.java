package com.opensimulationplatform.core.util.loghelper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;

public class LogHelper {
  
  public static boolean setLogLevel() {
    String property = System.getProperty("msmi.validator.log.level");
    if (property != null) {
      switch (property) {
        case "debug":
          Configurator.setLevel(System.getProperty("log4j.logger"), Level.DEBUG);
          break;
        case "trace":
          Configurator.setLevel(System.getProperty("log4j.logger"), Level.TRACE);
          break;
        case "error":
          Configurator.setLevel(System.getProperty("log4j.logger"), Level.ERROR);
          break;
        case "fatal":
          Configurator.setLevel(System.getProperty("log4j.logger"), Level.FATAL);
          break;
        case "all":
          Configurator.setLevel(System.getProperty("log4j.logger"), Level.ALL);
          break;
        case "info":
          Configurator.setLevel(System.getProperty("log4j.logger"), Level.INFO);
          break;
        case "warn":
          Configurator.setLevel(System.getProperty("log4j.logger"), Level.WARN);
          break;
        case "off":
          Configurator.setLevel(System.getProperty("log4j.logger"), Level.OFF);
          break;
        default:
          Configurator.setLevel(System.getProperty("log4j.logger"), Level.ALL);
          return false;
      }
    }
    return true;
  }
}
