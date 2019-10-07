package com.opensimulationplatform.systemstructure.xml.parser;


import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class OspSystemStructureParser {
  
  private static final Logger LOG = LoggerFactory.getLogger(OspSystemStructureParser.class);
  
  public static OspSystemStructure parse(File ospSystemStructureFile) {
    try (InputStreamReader reader = new InputStreamReader(new FileInputStream(ospSystemStructureFile), StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(OspSystemStructure.class.getPackage().getName());
      return (OspSystemStructure) context.createUnmarshaller().unmarshal(reader);
    } catch (Exception e) {
      String message = "Unable to parse '" + ospSystemStructureFile.getAbsolutePath() + "'";
      LOG.error(message, e);
      throw new RuntimeException(message, e);
    }
  }
}
