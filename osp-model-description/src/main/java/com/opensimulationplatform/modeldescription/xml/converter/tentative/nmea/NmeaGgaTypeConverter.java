/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagga.NmeaGga;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix.NmeaGgaFix;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggalatitudelongitude.NmeaGgaLatitudeLongitude;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatime.NmeaTime;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaGgaType;

public class NmeaGgaTypeConverter extends Converter<NmeaGgaType, NmeaGga> {
  public NmeaGgaTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaGga convert(NmeaGgaType nmeaGgaType) {
    NmeaGga nmeaGga = new NmeaGga();

    nmeaGga.setName(nmeaGgaType.getName());

    NmeaTime nmeaTime = context.nmeaTimeTypeConverter.convert(nmeaGgaType.getNmeaTime());
    NmeaGgaFix nmeaGgaFix = context.nmeaGgaFixTypeConverter.convert(nmeaGgaType.getNmeaGgaFix());
    NmeaGgaLatitudeLongitude nmeaGgaLatitudeLongitude = context.nmeaGgaLatitudeLongitudeTypeConverter.convert(nmeaGgaType.getNmeaGgaLatitudeLongitude());

    context.modelDescription.getNmeaTimes().add(nmeaTime);
    context.modelDescription.getNmeaGgaFixs().add(nmeaGgaFix);
    context.modelDescription.getNmeaGgaLatitudeLongitudes().add(nmeaGgaLatitudeLongitude);

    nmeaGga.setNmeaTime(nmeaTime);
    nmeaGga.setNmeaGgaFix(nmeaGgaFix);
    nmeaGga.setNmeaGgaLatitudeLongitude(nmeaGgaLatitudeLongitude);

    return nmeaGga;
  }
}
