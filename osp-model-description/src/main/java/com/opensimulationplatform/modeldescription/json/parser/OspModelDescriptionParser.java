package com.opensimulationplatform.modeldescription.json.parser;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensimulationplatform.modeldescription.json.model.OspModelDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OspModelDescriptionParser {
  
  private final static Logger LOG = LoggerFactory.getLogger(OspModelDescriptionParser.class);
  
  public static OspModelDescription parse(File file) {
    try {
      LOG.debug("Parsing '" + file.getAbsolutePath() + "'...");
      ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      OspModelDescription jsonOspModelDescription = objectMapper.readValue(Files.readAllBytes(Paths.get(file.getAbsolutePath())), OspModelDescription.class);
      LOG.debug("done!");
      
      return jsonOspModelDescription;
    } catch (Exception e) {
      String msg = "Error during parsing of '" + file.getAbsolutePath() + "'";
      LOG.error(msg);
      throw new RuntimeException(msg, e);
    }
  }
}
