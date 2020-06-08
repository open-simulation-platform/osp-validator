/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawinddirection.NmeaWindDirection;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaWindDirectionType;

import java.util.List;

public class NmeaWindDirectionTypeConverter extends Converter<NmeaWindDirectionType, NmeaWindDirection> {
  public NmeaWindDirectionTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaWindDirection convert(NmeaWindDirectionType nmeaWindDirectionType) {
    NmeaWindDirection nmeaWindDirection = new NmeaWindDirection();

    nmeaWindDirection.setName(nmeaWindDirectionType.getName());
    List<Variable> variables = context.variableTypeConverter.convert(nmeaWindDirectionType.getVariable());
    nmeaWindDirection.setVariables(variables);

    return nmeaWindDirection;
  }
}
