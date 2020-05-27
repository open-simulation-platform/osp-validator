package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatime.NmeaTime;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaTimeType;

import java.util.Collections;

public class NmeaTimeTypeConverter extends Converter<NmeaTimeType, NmeaTime> {
  public NmeaTimeTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaTime convert(NmeaTimeType nmeaTimeType) {
    NmeaTime nmeaTime = new NmeaTime();

    nmeaTime.setName(nmeaTimeType.getName());
    Variable variables = context.variableTypeConverter.convert(nmeaTimeType.getVariable());
    nmeaTime.setVariables(Collections.singletonList(variables));

    return nmeaTime;
  }
}
