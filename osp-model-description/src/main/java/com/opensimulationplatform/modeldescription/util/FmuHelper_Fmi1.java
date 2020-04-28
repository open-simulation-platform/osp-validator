package com.opensimulationplatform.modeldescription.util;

import no.ntnu.ihb.fmi4j.modeldescription.ModelDescriptionParser;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import java.io.StringReader;
import java.net.URI;

public class FmuHelper_Fmi1 {
  private static final Logger LOG = LoggerFactory.getLogger(FmuHelper_Fmi1.class);

  public static FmiModelDescription getFmiModelDescription(URI fmu) {
    try {
      String fmiModelDescriptionXml = ModelDescriptionParser.extractModelDescriptionXml(fmu.toURL());
      JAXBContext jaxbContext = JAXBContext.newInstance(FmiModelDescription.class);
      return (FmiModelDescription) jaxbContext.createUnmarshaller().unmarshal(new StringReader(fmiModelDescriptionXml));
    } catch (Exception e) {
      String message = "Error trying to extract modelDescription.xml from " + fmu;
      LOG.error(message, e);
      throw new RuntimeException(message, e);
    }
  }
}
