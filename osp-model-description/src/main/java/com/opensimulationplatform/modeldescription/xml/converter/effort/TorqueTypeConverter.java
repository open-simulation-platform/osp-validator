/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.effort;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.TorqueType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class TorqueTypeConverter extends Converter<TorqueType, Torque> {

  public TorqueTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Torque convert(TorqueType torqueType) {
    Torque torque = new Torque();

    torque.setName(torqueType.getName());

    List<VariableType> variableTypes = torqueType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    torque.setVariables(variables);

    return torque;

  }
}
