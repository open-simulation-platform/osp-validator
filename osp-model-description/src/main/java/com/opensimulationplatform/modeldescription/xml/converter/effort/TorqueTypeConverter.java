package com.opensimulationplatform.modeldescription.xml.converter.effort;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Torque;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.TorqueType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class TorqueTypeConverter extends Converter<TorqueType, Torque> {

  public TorqueTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Torque convert(TorqueType torqueType) {
    Torque torque = new Torque();

    torque.setName(torqueType.getName());

    List<VariableType> variableTypes = torqueType.getVariable();
    List<Variable> variables = variableTypes.stream().map(converterContext.variableTypeConverter::convert).collect(Collectors.toList());
    torque.setVariables(variables);

    return torque;

  }

  @Override
  public List<Torque> convert(List<TorqueType> torqueTypes) {
    return torqueTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
