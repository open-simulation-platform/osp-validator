package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeamwv.NmeaMwv;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeastatus.NmeaStatus;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawinddirection.NmeaWindDirection;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawindspeed.NmeaWindSpeed;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaMwvType;

public class NmeaMwvTypeConverter extends Converter<NmeaMwvType, NmeaMwv> {
  public NmeaMwvTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaMwv convert(NmeaMwvType nmeaMwvType) {
    NmeaMwv nmeaMwv = new NmeaMwv();

    NmeaStatus nmeaStatus = context.nmeaStatusTypeConverter.convert(nmeaMwvType.getNmeaStatus());
    NmeaWindDirection nmeaWindDirection = context.nmeaWindDirectionTypeConverter.convert(nmeaMwvType.getNmeaWindDirection());
    NmeaWindSpeed nmeaWindSpeed = context.nmeaWindSpeedTypeConverter.convert(nmeaMwvType.getNmeaWindSpeed());

    context.modelDescription.getNmeaStatuses().add(nmeaStatus);
    context.modelDescription.getNmeaWindDirections().add(nmeaWindDirection);
    context.modelDescription.getNmeaWindSpeeds().add(nmeaWindSpeed);

    nmeaMwv.setNmeaStatus(nmeaStatus);
    nmeaMwv.setNmeaWindDirection(nmeaWindDirection);
    nmeaMwv.setNmeaWindSpeed(nmeaWindSpeed);

    return nmeaMwv;
  }
}
