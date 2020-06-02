/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix.NmeaGgaFix;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaGgaFixType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class NmeaGgaFixTypeConverter extends Converter<NmeaGgaFixType, NmeaGgaFix> {
  public NmeaGgaFixTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaGgaFix convert(NmeaGgaFixType nmeaGgaFixType) {
    NmeaGgaFix nmeaGgaFix = new NmeaGgaFix();

    nmeaGgaFix.setName(nmeaGgaFixType.getName());
    List<VariableType> variableTypes = nmeaGgaFixType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    nmeaGgaFix.setVariables(variables);

    return nmeaGgaFix;
  }
}
