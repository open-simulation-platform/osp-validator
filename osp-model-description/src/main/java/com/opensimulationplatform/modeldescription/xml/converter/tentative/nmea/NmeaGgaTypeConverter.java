package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagga.NmeaGga;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix.NmeaGgaFix;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggalatitudelongitude.NmeaGgaLatitudeLongitude;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatime.NmeaTime;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.NmeaGgaType;

public class NmeaGgaTypeConverter extends Converter<NmeaGgaType, NmeaGga> {
  @Override
  public NmeaGga convert(NmeaGgaType nmeaGgaType) {
    NmeaGga nmeaGga = new NmeaGga();

    nmeaGga.setName(nmeaGgaType.getName());

    NmeaTime nmeaTime = context.nmeaTimeTypeConverter.convert(nmeaGgaType.getNmeaTime());
    NmeaGgaFix nmeaGgaFix = context.nmeaGgaFixTypeConverter.convert(nmeaGgaType.getNmeaGgaFix());
    NmeaGgaLatitudeLongitude nmeaGgaLatitudeLongitude = context.nmeaGgaLatitudeLongitudeConverter.convert(nmeaGgaType.getNmeaGgaLatitudeLongitude());

    context.modelDescription.getNmeaTimes().add(nmeaTime);
    context.modelDescription.getNmeaGgaFixs().add(nmeaGgaFix);
    context.modelDescription.getNmeaGgaLatitudeLongitudes().add(nmeaGgaLatitudeLongitude);

    nmeaGga.setNmeaTime(nmeaTime);
    nmeaGga.setNmeaGgaFix(nmeaGgaFix);
    nmeaGga.setNmeaGgaLatitudeLongitude(nmeaGgaLatitudeLongitude);

    return nmeaGga;
  }
}
