/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.displacement;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.ChargeType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class ChargeTypeConverter extends Converter<ChargeType, Charge> {

  public ChargeTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Charge convert(ChargeType chargeType) {
    Charge charge = new Charge();

    charge.setName(chargeType.getName());

    List<VariableType> variableTypes = chargeType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    charge.setVariables(variables);

    return charge;
  }
}
