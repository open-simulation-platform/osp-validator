package com.opensimulationplatform.modeldescription.xml.converter.variable;

import com.opensimulationplatform.core.model.modeldescription.Unit;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.util.modeldescription.ModelDescriptionUtil;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class VariableTypeConverter extends Converter<VariableType, Variable> {

  public VariableTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Variable convert(VariableType variableType) {
    Variable variable = ModelDescriptionUtil.getVariableByName(context.modelDescription, variableType.getRef());

    Unit unit = ModelDescriptionUtil.getUnitByName(context.modelDescription, variableType.getUnit());
    if (unit != null && variable != null) {
      variable.setUnit(unit);
    }

    return variable;
  }

  @Override
  public List<Variable> convert(List<VariableType> variableTypes) {
    return variableTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
