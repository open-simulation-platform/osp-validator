package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed.ShaftSpeed;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.ShaftSpeedType;

import java.util.List;

public class ShaftSpeedTypeConverter extends Converter<ShaftSpeedType, ShaftSpeed> {
  @Override
  public ShaftSpeed convert(ShaftSpeedType shaftSpeedType) {
    ShaftSpeed shaftSpeed = new ShaftSpeed();

    shaftSpeed.setName(shaftSpeedType.getName());
    List<Variable> variable = context.variableTypeConverter.convert(shaftSpeedType.getVariable());
    shaftSpeed.setVariables(variable);

    return shaftSpeed;
  }
}
