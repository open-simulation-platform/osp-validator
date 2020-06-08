/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electricpower.ElectricPower;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.ElectricPowerType;

import java.util.Collections;

public class ElectricPowerTypeConverter extends Converter<ElectricPowerType, ElectricPower> {
  public ElectricPowerTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public ElectricPower convert(ElectricPowerType electricPowerType) {
    ElectricPower electricPower = new ElectricPower();

    electricPower.setName(electricPowerType.getName());
    Variable variable = context.variableTypeConverter.convert(electricPowerType.getVariable());
    electricPower.setVariables(Collections.singletonList(variable));

    return electricPower;
  }
}
