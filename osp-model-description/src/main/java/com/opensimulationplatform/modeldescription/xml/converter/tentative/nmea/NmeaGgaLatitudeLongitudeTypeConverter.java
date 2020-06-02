/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggalatitudelongitude.NmeaGgaLatitudeLongitude;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaGgaLatitudeLongitudeType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class NmeaGgaLatitudeLongitudeTypeConverter extends Converter<NmeaGgaLatitudeLongitudeType, NmeaGgaLatitudeLongitude> {
  public NmeaGgaLatitudeLongitudeTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaGgaLatitudeLongitude convert(NmeaGgaLatitudeLongitudeType nmeaGgaLatitudeLongitudeType) {
    NmeaGgaLatitudeLongitude nmeaGgaLatitudeLongitude = new NmeaGgaLatitudeLongitude();

    nmeaGgaLatitudeLongitude.setName(nmeaGgaLatitudeLongitudeType.getName());
    List<VariableType> variableTypes = nmeaGgaLatitudeLongitudeType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    nmeaGgaLatitudeLongitude.setVariables(variables);

    return nmeaGgaLatitudeLongitude;

  }
}
