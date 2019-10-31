package com.opensimulationplatform.systemstructure.xml.parser;


import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;

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

public class OspSystemStructureParser {
  public static OspSystemStructure parse(File ospSystemStructureFile) {
    try (InputStreamReader reader = new InputStreamReader(new FileInputStream(ospSystemStructureFile), StandardCharsets.UTF_8)) {
      SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = schemaFactory.newSchema(OspSystemStructureParser.class.getResource("/OspSystemStructure.xsd"));

      JAXBContext context = JAXBContext.newInstance(OspSystemStructure.class.getPackage().getName());

      Unmarshaller unmarshaller = context.createUnmarshaller();
      unmarshaller.setSchema(schema);

      return (OspSystemStructure) JAXBIntrospector.getValue(unmarshaller.unmarshal(reader));
    } catch (Exception e) {
      String message = "Unable to parse '" + ospSystemStructureFile.getAbsolutePath() + "'";
      throw new RuntimeException(message, e);
    }
  }
}
