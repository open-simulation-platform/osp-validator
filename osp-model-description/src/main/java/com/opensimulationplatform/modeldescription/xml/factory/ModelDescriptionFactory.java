package com.opensimulationplatform.modeldescription.xml.factory;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.modeldescription.xml.converter.ospmodeldescriptiontype.OspModelDescriptionTypeConverter;
import com.opensimulationplatform.modeldescription.xml.model.OspModelDescriptionType;
import com.opensimulationplatform.modeldescription.xml.parser.OspModelDescriptionParser;

import java.io.File;

public class ModelDescriptionFactory {
  public static ModelDescription create(File ospModelDescription, File fmu) {
    OspModelDescriptionType ospModelDescriptionType = OspModelDescriptionParser.parse(ospModelDescription);
    OspModelDescriptionTypeConverter converter = new OspModelDescriptionTypeConverter(fmu);
    return converter.convert(ospModelDescriptionType);
  }
}
