/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.electricpower.ElectricPower;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.frequency.Frequency;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generatorfeedback.GeneratorFeedback;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.GeneratorFeedbackType;

public class GeneratorFeedbackTypeConverter extends Converter<GeneratorFeedbackType, GeneratorFeedback> {
  public GeneratorFeedbackTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public GeneratorFeedback convert(GeneratorFeedbackType object) {
    GeneratorFeedback generatorFeedback = new GeneratorFeedback();

    generatorFeedback.setName(object.getName());

    ElectricPower electricPower = context.electricPowerTypeConverter.convert(object.getElectricPower());
    Frequency frequency = context.frequencyTypeConverter.convert(object.getFrequency());
    Voltage voltage = context.voltageTypeConverter.convert(object.getVoltage());

    context.modelDescription.getElectricPowers().add(electricPower);
    context.modelDescription.getFrequencies().add(frequency);
    context.modelDescription.getVoltages().add(voltage);

    generatorFeedback.setElectricPower(electricPower);
    generatorFeedback.setFrequency(frequency);
    generatorFeedback.setVoltage(voltage);

    return generatorFeedback;
  }
}
