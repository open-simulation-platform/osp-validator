/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.batteryfeedback.BatteryFeedback;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electricpower.ElectricPower;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.BatteryFeedbackType;

import java.util.Collections;

public class BatteryFeedbackTypeConverter extends Converter<BatteryFeedbackType, BatteryFeedback> {
  public BatteryFeedbackTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public BatteryFeedback convert(BatteryFeedbackType batteryFeedbackType) {
    BatteryFeedback batteryFeedback = new BatteryFeedback();

    batteryFeedback.setName(batteryFeedbackType.getName());

    Variable variables = context.variableTypeConverter.convert(batteryFeedbackType.getVariable());
    ElectricPower electricPower = context.electricPowerTypeConverter.convert(batteryFeedbackType.getElectricPower());

    context.modelDescription.getElectricPowers().add(electricPower);

    batteryFeedback.setVariables(Collections.singletonList(variables));
    batteryFeedback.setElectricPower(electricPower);

    return batteryFeedback;
  }
}
