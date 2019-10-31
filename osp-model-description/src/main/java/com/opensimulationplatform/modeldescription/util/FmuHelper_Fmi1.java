package com.opensimulationplatform.modeldescription.util;

import no.ntnu.ihb.fmi4j.modeldescription.ModelDescriptionParser;
import no.ntnu.ihb.fmi4j.modeldescription.fmi1.FmiModelDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.StringReader;

public class FmuHelper_Fmi1 {
  private static final Logger LOG = LoggerFactory.getLogger(FmuHelper_Fmi1.class);

  public static FmiModelDescription getFmiModelDescription(File fmu) {
    try {
      String fmiModelDescriptionXml = ModelDescriptionParser.extractModelDescriptionXml(fmu);
      JAXBContext jaxbContext = JAXBContext.newInstance(FmiModelDescription.class);
      return (FmiModelDescription) jaxbContext.createUnmarshaller().unmarshal(new StringReader(fmiModelDescriptionXml));
    } catch (JAXBException e) {
      String message = "Error trying to extract modelDescription.xml from " + fmu.getAbsolutePath();
      LOG.error(message, e);
      throw new RuntimeException(message, e);
    }
  }
}
