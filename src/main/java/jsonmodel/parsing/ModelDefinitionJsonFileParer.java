package jsonmodel.parsing;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jsonmodel.modeldefinition.JsonModelDefinition;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ModelDefinitionJsonFileParer {
  public static JsonModelDefinition parse(File file) {
    try {
      ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return objectMapper.readValue(Files.readAllBytes(Paths.get(file.getAbsolutePath())), JsonModelDefinition.class);
    } catch (Exception e) {
      throw new RuntimeException("Error during parsing of " + file.getAbsolutePath(), e);
    }
  }
}
