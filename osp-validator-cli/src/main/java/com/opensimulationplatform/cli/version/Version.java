package com.opensimulationplatform.cli.version;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Version {
  public static String getVersionInfo() {
    StringBuilder version = new StringBuilder();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(Version.class.getResourceAsStream("/git.info")));
      String line = reader.readLine();
      while (line != null) {
        if (!line.startsWith("#")) {
          version.append(line).append("\n");
        }
        line = reader.readLine();
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return version.toString().trim();
  }
}
