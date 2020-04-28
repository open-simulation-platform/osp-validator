package com.opensimulationplatform.modeldescription.xml.converter.effort;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Voltage;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;
import com.opensimulationplatform.modeldescription.xml.model.VoltageType;

import java.util.List;
import java.util.stream.Collectors;

public class VoltageTypeConverter extends Converter<VoltageType, Voltage> {

  public VoltageTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Voltage convert(VoltageType voltageType) {
    Voltage voltage = new Voltage();

    voltage.setName(voltageType.getName());

    List<VariableType> variableTypes = voltageType.getVariable();
    List<Variable> variables = variableTypes.stream().map(converterContext.variableTypeConverter::convert).collect(Collectors.toList());
    voltage.setVariables(variables);

    return voltage;

  }

  @Override
  public List<Voltage> convert(List<VoltageType> voltageTypes) {
    return voltageTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
