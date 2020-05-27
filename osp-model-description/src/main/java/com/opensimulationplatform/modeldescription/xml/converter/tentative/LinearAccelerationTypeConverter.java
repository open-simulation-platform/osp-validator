package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearacceleration.LinearAcceleration;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.LinearAccelerationType;

import java.util.List;

public class LinearAccelerationTypeConverter extends Converter<LinearAccelerationType, LinearAcceleration> {
  public LinearAccelerationTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public LinearAcceleration convert(LinearAccelerationType linearAccelerationType) {
    LinearAcceleration linearAcceleration = new LinearAcceleration();

    linearAcceleration.setName(linearAccelerationType.getName());
    List<Variable> variables = context.variableTypeConverter.convert(linearAccelerationType.getVariable());
    linearAcceleration.setVariables(variables);

    return linearAcceleration;
  }
}
