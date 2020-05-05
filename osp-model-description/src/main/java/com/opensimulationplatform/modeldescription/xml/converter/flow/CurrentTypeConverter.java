package com.opensimulationplatform.modeldescription.xml.converter.flow;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.current.Current;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.CurrentType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class CurrentTypeConverter extends Converter<CurrentType, Current> {

  public CurrentTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Current convert(CurrentType currentType) {
    Current current = new Current();

    current.setName(currentType.getName());
    List<VariableType> variableTypes = currentType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    current.setVariables(variables);

    return current;
  }

  @Override
  public List<Current> convert(List<CurrentType> linearVelocityTypes) {
    return linearVelocityTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
