package com.opensimulationplatform.modeldescription.xml.converter.displacement;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.AngularDisplacement;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.AngularDisplacementType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class AngularDisplacementTypeConverter extends Converter<AngularDisplacementType, AngularDisplacement> {

  public AngularDisplacementTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public AngularDisplacement convert(AngularDisplacementType angularDisplacementType) {
    AngularDisplacement angularDisplacement = new AngularDisplacement();

    angularDisplacement.setName(angularDisplacementType.getName());

    List<VariableType> variableTypes = angularDisplacementType.getVariable();
    List<Variable> variables = variableTypes.stream().map(converterContext.variableTypeConverter::convert).collect(Collectors.toList());
    angularDisplacement.getVariables().addAll(variables);

    return angularDisplacement;
  }

  @Override
  public List<AngularDisplacement> convert(List<AngularDisplacementType> angularDisplacementTypes) {
    return angularDisplacementTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
