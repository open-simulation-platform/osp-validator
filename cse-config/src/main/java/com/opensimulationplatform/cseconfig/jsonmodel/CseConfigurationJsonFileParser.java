package com.opensimulationplatform.cseconfig.jsonmodel;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CseConfigurationJsonFileParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(CseConfigurationJsonFileParser.class);
  
  public static JsonCseConfiguration parse(File file) {
    try {
      LOG.debug("Parsing " + file.getAbsolutePath() + "...");
      ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      JsonCseConfiguration jsonCseConfiguration = objectMapper.readValue(Files.readAllBytes(Paths.get(file.getAbsolutePath())), JsonCseConfiguration.class);
      LOG.debug("done!");
      
      return jsonCseConfiguration;
    } catch (Exception e) {
      String msg = "Error during parsing of " + file.getAbsolutePath();
      LOG.error(msg);
      throw new RuntimeException(msg, e);
    }
  }
}
