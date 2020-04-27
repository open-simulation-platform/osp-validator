package com.opensimulationplatform.modeldescription.xml.parser;

import com.opensimulationplatform.modeldescription.xml.model.OspModelDescriptionType;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class OspModelDescriptionParser {
  public static OspModelDescriptionType parse(File ospModelDescriptionFile) {
    try (InputStreamReader reader = new InputStreamReader(new FileInputStream(ospModelDescriptionFile), StandardCharsets.UTF_8)) {
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = schemaFactory.newSchema(OspModelDescriptionParser.class.getResource("/OspModelDescription.xsd"));

      JAXBContext context = JAXBContext.newInstance(OspModelDescriptionType.class.getPackage().getName());
      Unmarshaller unmarshaller = context.createUnmarshaller();
      unmarshaller.setSchema(schema);

      return (OspModelDescriptionType) JAXBIntrospector.getValue(unmarshaller.unmarshal(reader));
    } catch (Exception e) {
      String message = "Unable to parse '" + ospModelDescriptionFile.getAbsolutePath() + "'";
      throw new RuntimeException(message, e);
    }
  }
}
