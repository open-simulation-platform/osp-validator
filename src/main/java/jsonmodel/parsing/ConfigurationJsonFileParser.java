package jsonmodel.parsing;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jsonmodel.configuration.JsonConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigurationJsonFileParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(ConfigurationJsonFileParser.class);
  
  public static JsonConfiguration parse(File file) {
    try {
      LOG.debug("Parsing " + file.getAbsolutePath() + "...");
      ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      JsonConfiguration jsonConfiguration = objectMapper.readValue(Files.readAllBytes(Paths.get(file.getAbsolutePath())), JsonConfiguration.class);
      LOG.debug("done!");
      
      return jsonConfiguration;
    } catch (Exception e) {
      String msg = "Error during parsing of " + file.getAbsolutePath();
      LOG.error(msg);
      throw new RuntimeException(msg, e);
    }
  }
}
