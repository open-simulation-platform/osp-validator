package com.opensimulationplatform.modeldescription.xml.converter.flow;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.LinearVelocityType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class LinearVelocityTypeConverter extends Converter<LinearVelocityType, LinearVelocity> {

  public LinearVelocityTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public LinearVelocity convert(LinearVelocityType linearVelocityType) {
    LinearVelocity linearVelocity = new LinearVelocity();

    linearVelocity.setName(linearVelocityType.getName());

    List<VariableType> variableTypes = linearVelocityType.getVariable();
    List<Variable> variables = variableTypes.stream().map(converterContext.variableTypeConverter::convert).collect(Collectors.toList());
    linearVelocity.setVariables(variables);

    return linearVelocity;
  }

  @Override
  public List<LinearVelocity> convert(List<LinearVelocityType> linearVelocityTypes) {
    return linearVelocityTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
