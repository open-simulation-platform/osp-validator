package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electricpower.ElectricPower;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.ElectricPowerType;

import java.util.Collections;

public class ElectricPowerTypeConverter extends Converter<ElectricPowerType, ElectricPower> {
  @Override
  public ElectricPower convert(ElectricPowerType electricPowerType) {
    ElectricPower electricPower = new ElectricPower();

    electricPower.setName(electricPowerType.getName());
    Variable variable = context.variableTypeConverter.convert(electricPowerType.getVariable());
    electricPower.setVariables(Collections.singletonList(variable));

    return electricPower;
  }
}
