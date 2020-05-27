package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatrueheading.NmeaTrueHeading;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaTrueHeadingType;

import java.util.Collections;

public class NmeaTrueHeadingTypeConverter extends Converter<NmeaTrueHeadingType, NmeaTrueHeading> {
  public NmeaTrueHeadingTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaTrueHeading convert(NmeaTrueHeadingType nmeaTrueHeadingType) {
    NmeaTrueHeading nmeaTrueHeading = new NmeaTrueHeading();

    nmeaTrueHeading.setName(nmeaTrueHeadingType.getName());
    Variable variable = context.variableTypeConverter.convert(nmeaTrueHeadingType.getVariable());
    nmeaTrueHeading.setVariables(Collections.singletonList(variable));

    return nmeaTrueHeading;
  }
}
