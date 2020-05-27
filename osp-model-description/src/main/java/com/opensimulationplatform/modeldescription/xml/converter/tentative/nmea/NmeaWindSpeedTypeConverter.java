package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawindspeed.NmeaWindSpeed;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.NmeaWindSpeedType;

import java.util.List;

public class NmeaWindSpeedTypeConverter extends Converter<NmeaWindSpeedType, NmeaWindSpeed> {
  @Override
  public NmeaWindSpeed convert(NmeaWindSpeedType nmeaWindSpeedType) {
    NmeaWindSpeed nmeaWindSpeed = new NmeaWindSpeed();

    nmeaWindSpeed.setName(nmeaWindSpeedType.getName());
    List<Variable> variables = context.variableTypeConverter.convert(nmeaWindSpeedType.getVariable());
    nmeaWindSpeed.setVariables(variables);

    return nmeaWindSpeed;
  }
}
