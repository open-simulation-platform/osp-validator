package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.frequency.Frequency;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.FrequencyType;

import java.util.Collections;

public class FrequencyTypeConverter extends Converter<FrequencyType, Frequency> {
  public FrequencyTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public Frequency convert(FrequencyType frequencyType) {
    Frequency frequency = new Frequency();

    frequency.setName(frequencyType.getName());
    Variable variable = context.variableTypeConverter.convert(frequencyType.getVariable());
    frequency.setVariables(Collections.singletonList(variable));

    return frequency;
  }
}
