package com.opensimulationplatform.cseconfig.json.parser;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensimulationplatform.cseconfig.json.model.OspSystemStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OspSystemStructureParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(OspSystemStructureParser.class);
  
  public static OspSystemStructure parse(File file) {
    try {
      LOG.debug("Parsing " + file.getAbsolutePath() + "...");
      ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      OspSystemStructure systemStructure = objectMapper.readValue(Files.readAllBytes(Paths.get(file.getAbsolutePath())), OspSystemStructure.class);
      LOG.debug("done!");
      
      return systemStructure;
    } catch (Exception e) {
      String msg = "Error during parsing of " + file.getAbsolutePath();
      LOG.error(msg);
      throw new RuntimeException(msg, e);
    }
  }
}
