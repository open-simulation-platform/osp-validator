package com.opensimulationplatform.modeldescription.xml.converter.effort;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Pressure;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.PressureType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class PressureTypeConverter extends Converter<PressureType, Pressure> {

  public PressureTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Pressure convert(PressureType pressureType) {
    Pressure pressure = new Pressure();

    pressure.setName(pressureType.getName());

    List<VariableType> variableTypes = pressureType.getVariable();
    List<Variable> variables = variableTypes.stream().map(converterContext.variableTypeConverter::convert).collect(Collectors.toList());
    pressure.getVariables().addAll(variables);

    return pressure;

  }

  @Override
  public List<Pressure> convert(List<PressureType> pressureTypes) {
    return pressureTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
