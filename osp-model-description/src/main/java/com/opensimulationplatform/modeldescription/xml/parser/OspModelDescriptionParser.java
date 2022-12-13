/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.parser;

import com.opensimulationplatform.modeldescription.xml.model.OspModelDescriptionType;

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

public class OspModelDescriptionParser {

  private LocationListener listener;

  public OspModelDescriptionType parse(File ospModelDescriptionFile) {
    XMLInputFactory inputFactory = XMLInputFactory.newFactory();
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    try (FileInputStream fileInputStream = new FileInputStream(ospModelDescriptionFile)) {
      XMLStreamReader reader = inputFactory.createXMLStreamReader(fileInputStream);
      listener = new LocationListener(reader);

      Schema schema = schemaFactory.newSchema(OspModelDescriptionParser.class.getResource("/OspModelDescription-1.0.1.xsd"));

      JAXBContext context = JAXBContext.newInstance(OspModelDescriptionType.class.getPackage().getName());

      Unmarshaller unmarshaller = context.createUnmarshaller();
      unmarshaller.setSchema(schema);
      unmarshaller.setListener(listener);

      return (OspModelDescriptionType) JAXBIntrospector.getValue(unmarshaller.unmarshal(reader));
    } catch (Exception e) {
      String message = "Unable to parse '" + ospModelDescriptionFile.getAbsolutePath() + "'";
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
  }
}
