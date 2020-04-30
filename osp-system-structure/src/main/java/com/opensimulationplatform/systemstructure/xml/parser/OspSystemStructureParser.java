package com.opensimulationplatform.systemstructure.xml.parser;


import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.Location;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class OspSystemStructureParser {

  private LocationListener listener;

  public OspSystemStructure parse(File ospSystemStructureFile) {
    XMLInputFactory inputFactory = XMLInputFactory.newFactory();
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try (FileInputStream fileInputStream = new FileInputStream(ospSystemStructureFile)) {
      XMLStreamReader reader = inputFactory.createXMLStreamReader(fileInputStream);
      listener = new LocationListener(reader);

      Schema schema = schemaFactory.newSchema(OspSystemStructureParser.class.getResource("/OspSystemStructure.xsd"));

      JAXBContext context = JAXBContext.newInstance(OspSystemStructure.class.getPackage().getName());

      Unmarshaller unmarshaller = context.createUnmarshaller();
      unmarshaller.setSchema(schema);
      unmarshaller.setListener(listener);

      return (OspSystemStructure) JAXBIntrospector.getValue(unmarshaller.unmarshal(reader));
    } catch (Exception e) {
      String message = "Unable to parse '" + ospSystemStructureFile.getAbsolutePath() + "'";
      throw new RuntimeException(message, e);
    }
  }

  public Map<Object, Location> getLocations() {
    return this.listener.locations;
  }

  private static class LocationListener extends Unmarshaller.Listener {
    private final Map<Object, Location> locations;
    private final XMLStreamReader reader;

    public LocationListener(XMLStreamReader reader) {
      this.reader = reader;
      this.locations = new HashMap<>();
    }

    @Override
    public void beforeUnmarshal(Object target, Object parent) {
      locations.put(target, reader.getLocation());
    }

    public Location getLocation(Object o) {
      return locations.get(o);
    }
  }
}
