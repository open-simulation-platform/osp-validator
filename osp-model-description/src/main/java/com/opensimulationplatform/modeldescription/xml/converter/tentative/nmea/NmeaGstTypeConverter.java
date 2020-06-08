/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagst.NmeaGst;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstellipse.NmeaGstEllipse;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstpositionerror.NmeaGstPositionError;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstrms.NmeaGstRms;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaGstType;

public class NmeaGstTypeConverter extends Converter<NmeaGstType, NmeaGst> {
  public NmeaGstTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaGst convert(NmeaGstType nmeaGstType) {
    NmeaGst nmeaGst = new NmeaGst();

    nmeaGst.setName(nmeaGstType.getName());

    NmeaGstEllipse nmeaGstEllipse = context.nmeaGstEllipseTypeConverter.convert(nmeaGstType.getNmeaGstEllipse());
    NmeaGstPositionError nmeaGstPositionError = context.nmeaGstPositionErrorTypeConverter.convert(nmeaGstType.getNmeaGstPositionError());
    NmeaGstRms nmeaGstRms = context.nmeaGstRmsTypeConverter.convert(nmeaGstType.getNmeaGstRms());

    context.modelDescription.getNmeaGstEllipses().add(nmeaGstEllipse);
    context.modelDescription.getNmeaGstPositionErrors().add(nmeaGstPositionError);
    context.modelDescription.getNmeaGstRmss().add(nmeaGstRms);

    nmeaGst.setNmeaGstEllipse(nmeaGstEllipse);
    nmeaGst.setNmeaGstPositionError(nmeaGstPositionError);
    nmeaGst.setNmeaGstRms(nmeaGstRms);

    return nmeaGst;
  }
}
