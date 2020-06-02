/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstrms.NmeaGstRms;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaGstRmsType;

import java.util.Collections;

public class NmeaGstRmsTypeConverter extends Converter<NmeaGstRmsType, NmeaGstRms> {
  public NmeaGstRmsTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaGstRms convert(NmeaGstRmsType nmeaGstRmsType) {
    NmeaGstRms nmeaGstRms = new NmeaGstRms();

    nmeaGstRms.setName(nmeaGstRmsType.getName());
    Variable variables = context.variableTypeConverter.convert(nmeaGstRmsType.getVariable());
    nmeaGstRms.setVariables(Collections.singletonList(variables));

    return nmeaGstRms;
  }
}
