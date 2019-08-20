package com.opensimulationplatform.json.model.parsing;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensimulationplatform.json.model.modeldefinition.JsonModelDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ModelDefinitionJsonFileParser {
  
  private final static Logger LOG = LoggerFactory.getLogger(ModelDefinitionJsonFileParser.class);
  
  public static JsonModelDefinition parse(File file) {
    try {
      LOG.debug("Parsing " + file.getAbsolutePath() + "...");
      ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      JsonModelDefinition jsonModelDefinition = objectMapper.readValue(Files.readAllBytes(Paths.get(file.getAbsolutePath())), JsonModelDefinition.class);
      LOG.debug("done!");
      
      return jsonModelDefinition;
    } catch (Exception e) {
      String msg = "Error during parsing of " + file.getAbsolutePath();
      LOG.error(msg);
      throw new RuntimeException(msg, e);
    }
  }
}
