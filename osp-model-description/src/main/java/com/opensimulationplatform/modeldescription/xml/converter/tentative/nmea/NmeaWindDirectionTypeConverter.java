package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawinddirection.NmeaWindDirection;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.NmeaWindDirectionType;

import java.util.List;

public class NmeaWindDirectionTypeConverter extends Converter<NmeaWindDirectionType, NmeaWindDirection> {
  @Override
  public NmeaWindDirection convert(NmeaWindDirectionType nmeaWindDirectionType) {
    NmeaWindDirection nmeaWindDirection = new NmeaWindDirection();

    nmeaWindDirection.setName(nmeaWindDirectionType.getName());
    List<Variable> variables = context.variableTypeConverter.convert(nmeaWindDirectionType.getVariable());
    nmeaWindDirection.setVariables(variables);

    return nmeaWindDirection;
  }
}
