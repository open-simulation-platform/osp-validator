/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.busfeedback.BusFeedback;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electricpower.ElectricPower;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.frequency.Frequency;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.BusFeedbackType;

public class BusFeedbackTypeConverter extends Converter<BusFeedbackType, BusFeedback> {
  public BusFeedbackTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public BusFeedback convert(BusFeedbackType busFeedbackType) {
    BusFeedback busFeedback = new BusFeedback();

    busFeedback.setName(busFeedbackType.getName());

    ElectricPower electricPower = context.electricPowerTypeConverter.convert(busFeedbackType.getElectricPower());
    Frequency frequency = context.frequencyTypeConverter.convert(busFeedbackType.getFrequency());
    Voltage voltage = context.voltageTypeConverter.convert(busFeedbackType.getVoltage());

    context.modelDescription.getElectricPowers().add(electricPower);
    context.modelDescription.getFrequencies().add(frequency);
    context.modelDescription.getVoltages().add(voltage);

    busFeedback.setElectricPower(electricPower);
    busFeedback.setFrequency(frequency);
    busFeedback.setVoltage(voltage);

    return busFeedback;
  }
}
