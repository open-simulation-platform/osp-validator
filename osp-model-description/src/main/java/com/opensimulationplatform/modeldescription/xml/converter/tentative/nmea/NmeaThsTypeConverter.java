package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeastatus.NmeaStatus;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaths.NmeaThs;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatrueheading.NmeaTrueHeading;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.NmeaThsType;

public class NmeaThsTypeConverter extends Converter<NmeaThsType, NmeaThs> {
  @Override
  public NmeaThs convert(NmeaThsType nmeaThsType) {
    NmeaThs nmeaThs = new NmeaThs();

    nmeaThs.setName(nmeaThsType.getName());

    NmeaStatus nmeaStatus = context.nmeaStatusTypeConverter.convert(nmeaThsType.getNmeaStatus());
    NmeaTrueHeading nmeaTrueHeading = context.nmeaTrueHeadingTypeConverter.convert(nmeaThsType.getNmeaTrueHeading());

    context.modelDescription.getNmeaStatuses().add(nmeaStatus);
    context.modelDescription.getNmeaTrueHeadings().add(nmeaTrueHeading);

    nmeaThs.setNmeaStatus(nmeaStatus);
    nmeaThs.setNmeaTrueHeading(nmeaTrueHeading);

    return nmeaThs;
  }
}
