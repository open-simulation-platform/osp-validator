package com.opensimulationplatform.modeldescription.xml.converter.displacement;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.LinearDisplacement;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.LinearDisplacementType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class LinearDisplacementTypeConverter extends Converter<LinearDisplacementType, LinearDisplacement> {

  public LinearDisplacementTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public LinearDisplacement convert(LinearDisplacementType linearDisplacementType) {
    LinearDisplacement linearDisplacement = new LinearDisplacement();

    linearDisplacement.setName(linearDisplacementType.getName());

    List<VariableType> variableTypes = linearDisplacementType.getVariable();
    List<Variable> variables = variableTypes.stream().map(converterContext.variableTypeConverter::convert).collect(Collectors.toList());
    linearDisplacement.getVariables().addAll(variables);

    return linearDisplacement;
  }

  @Override
  public List<LinearDisplacement> convert(List<LinearDisplacementType> linearDisplacementTypes) {
    return linearDisplacementTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
