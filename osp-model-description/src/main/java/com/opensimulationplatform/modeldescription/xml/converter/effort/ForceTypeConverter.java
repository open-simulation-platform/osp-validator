package com.opensimulationplatform.modeldescription.xml.converter.effort;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.ForceType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class ForceTypeConverter extends Converter<ForceType, Force> {

  public ForceTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Force convert(ForceType forceType) {
    Force force = new Force();

    force.setName(forceType.getName());

    List<VariableType> variableTypes = forceType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    force.setVariables(variables);

    return force;
  }
}
