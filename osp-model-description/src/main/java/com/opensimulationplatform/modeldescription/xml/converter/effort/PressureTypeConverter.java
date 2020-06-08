/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.effort;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.PressureType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class PressureTypeConverter extends Converter<PressureType, Pressure> {

  public PressureTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Pressure convert(PressureType pressureType) {
    Pressure pressure = new Pressure();

    pressure.setName(pressureType.getName());

    List<VariableType> variableTypes = pressureType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    pressure.setVariables(variables);

    return pressure;

  }
}
