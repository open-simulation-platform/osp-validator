/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative.nmea;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstpositionerror.NmeaGstPositionError;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.NmeaGstPositionErrorType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class NmeaGstPositionErrorTypeConverter extends Converter<NmeaGstPositionErrorType, NmeaGstPositionError> {
  public NmeaGstPositionErrorTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public NmeaGstPositionError convert(NmeaGstPositionErrorType nmeaGstPositionErrorType) {
    NmeaGstPositionError nmeaGstPositionError = new NmeaGstPositionError();

    nmeaGstPositionError.setName(nmeaGstPositionErrorType.getName());
    List<VariableType> variableTypes = nmeaGstPositionErrorType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    nmeaGstPositionError.setVariables(variables);

    return nmeaGstPositionError;
  }
}
