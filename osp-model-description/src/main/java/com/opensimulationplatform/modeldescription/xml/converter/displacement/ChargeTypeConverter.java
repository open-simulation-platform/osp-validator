package com.opensimulationplatform.modeldescription.xml.converter.displacement;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.Charge;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.ChargeType;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;

import java.util.List;
import java.util.stream.Collectors;

public class ChargeTypeConverter extends Converter<ChargeType, Charge> {

  public ChargeTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Charge convert(ChargeType chargeType) {
    Charge charge = new Charge();

    charge.setName(chargeType.getName());

    List<VariableType> variableTypes = chargeType.getVariable();
    List<Variable> variables = variableTypes.stream().map(converterContext.variableTypeConverter::convert).collect(Collectors.toList());
    charge.setVariables(variables);

    return charge;
  }

  @Override
  public List<Charge> convert(List<ChargeType> chargeTypes) {
    return chargeTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
