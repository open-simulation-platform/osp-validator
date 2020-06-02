/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstellipse.NmeaGstEllipse;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaGstEllipseType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class NmeaGstEllipseTypeConverter extends Converter<NmeaGstEllipseType, NmeaGstEllipse> {
  public NmeaGstEllipseTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaGstEllipse convert(NmeaGstEllipseType nmeaGstEllipseType) {
    NmeaGstEllipse nmeaGstEllipse = new NmeaGstEllipse();

    nmeaGstEllipse.setName(nmeaGstEllipseType.getName());
    List<VariableType> variableTypes = nmeaGstEllipseType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    nmeaGstEllipse.setVariables(variables);

    return nmeaGstEllipse;
  }
}
