package com.opensimulationplatform.modeldescription.xml.factory;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.modeldescription.xml.converter.ospmodeldescriptiontype.OspModelDescriptionTypeConverter;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescriptionType;
import com.opensimulationplatform.modeldescription.xml.parser.OspModelDescriptionParser;

import java.io.File;
import java.net.URI;

public class ModelDescriptionFactory {
  public ModelDescription create(File ospModelDescription, URI fmu) {
    OspModelDescriptionParser parser = new OspModelDescriptionParser();
    OspModelDescriptionType ospModelDescriptionType = parser.parse(ospModelDescription);
    OspModelDescriptionTypeConverter converter = new OspModelDescriptionTypeConverter(fmu);
    return converter.convert(ospModelDescriptionType);
  }
}
