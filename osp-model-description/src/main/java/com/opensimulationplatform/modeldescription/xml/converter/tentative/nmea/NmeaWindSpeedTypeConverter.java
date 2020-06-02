/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawindspeed.NmeaWindSpeed;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaWindSpeedType;

import java.util.List;

public class NmeaWindSpeedTypeConverter extends Converter<NmeaWindSpeedType, NmeaWindSpeed> {
  public NmeaWindSpeedTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaWindSpeed convert(NmeaWindSpeedType nmeaWindSpeedType) {
    NmeaWindSpeed nmeaWindSpeed = new NmeaWindSpeed();

    nmeaWindSpeed.setName(nmeaWindSpeedType.getName());
    List<Variable> variables = context.variableTypeConverter.convert(nmeaWindSpeedType.getVariable());
    nmeaWindSpeed.setVariables(variables);

    return nmeaWindSpeed;
  }
}
