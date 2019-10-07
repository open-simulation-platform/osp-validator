package com.opensimulationplatform.modeldescription.xml.parser;

import com.opensimulationplatform.modeldescription.xml.model.OspModelDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class OspModelDescriptionParser {
  
  private final static Logger LOG = LoggerFactory.getLogger(OspModelDescriptionParser.class);
  
  public static OspModelDescription parse(File ospModelDescriptionFile) {
    try (InputStreamReader reader = new InputStreamReader(new FileInputStream(ospModelDescriptionFile), StandardCharsets.UTF_8)) {
      JAXBContext context = JAXBContext.newInstance(OspModelDescription.class.getPackage().getName());
      return (OspModelDescription) context.createUnmarshaller().unmarshal(reader);
    } catch (Exception e) {
      String message = "Unable to parse '" + ospModelDescriptionFile.getAbsolutePath() + "'";
      LOG.error(message, e);
      throw new RuntimeException(message, e);
    }
  }
}
