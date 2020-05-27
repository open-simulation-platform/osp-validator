package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.azimuthangle.AzimuthAngle;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.AzimuthAngleType;

import java.util.List;

public class AzimuthAngleTypeConverter extends Converter<AzimuthAngleType, AzimuthAngle> {
  public AzimuthAngleTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public AzimuthAngle convert(AzimuthAngleType azimuthAngleType) {
    AzimuthAngle azimuthAngle = new AzimuthAngle();

    azimuthAngle.setName(azimuthAngleType.getName());
    List<Variable> variables = context.variableTypeConverter.convert(azimuthAngleType.getVariable());
    azimuthAngle.setVariables(variables);

    return azimuthAngle;
  }
}
