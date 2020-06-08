/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.flow;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.LinearVelocityType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class LinearVelocityTypeConverter extends Converter<LinearVelocityType, LinearVelocity> {

  public LinearVelocityTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public LinearVelocity convert(LinearVelocityType linearVelocityType) {
    LinearVelocity linearVelocity = new LinearVelocity();

    linearVelocity.setName(linearVelocityType.getName());

    List<VariableType> variableTypes = linearVelocityType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    linearVelocity.setVariables(variables);

    return linearVelocity;
  }
}
