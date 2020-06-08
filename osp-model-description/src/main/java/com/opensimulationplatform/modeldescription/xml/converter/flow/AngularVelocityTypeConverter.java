/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.flow;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.AngularVelocityType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class AngularVelocityTypeConverter extends Converter<AngularVelocityType, AngularVelocity> {

  public AngularVelocityTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public AngularVelocity convert(AngularVelocityType angularVelocityType) {
    AngularVelocity angularVelocity = new AngularVelocity();

    angularVelocity.setName(angularVelocityType.getName());
    List<VariableType> variableTypes = angularVelocityType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    angularVelocity.setVariables(variables);

    return angularVelocity;
  }
}
