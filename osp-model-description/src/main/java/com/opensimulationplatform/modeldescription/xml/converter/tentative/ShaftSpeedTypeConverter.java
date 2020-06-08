/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed.ShaftSpeed;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.ShaftSpeedType;

import java.util.List;

public class ShaftSpeedTypeConverter extends Converter<ShaftSpeedType, ShaftSpeed> {
  public ShaftSpeedTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public ShaftSpeed convert(ShaftSpeedType shaftSpeedType) {
    ShaftSpeed shaftSpeed = new ShaftSpeed();

    shaftSpeed.setName(shaftSpeedType.getName());
    List<Variable> variable = context.variableTypeConverter.convert(shaftSpeedType.getVariable());
    shaftSpeed.setVariables(variable);

    return shaftSpeed;
  }
}
