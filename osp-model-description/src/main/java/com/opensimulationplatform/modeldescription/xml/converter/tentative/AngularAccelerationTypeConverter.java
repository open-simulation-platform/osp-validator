package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularacceleration.AngularAcceleration;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.AngularAccelerationType;

import java.util.List;

public class AngularAccelerationTypeConverter extends Converter<AngularAccelerationType, AngularAcceleration> {
  @Override
  public AngularAcceleration convert(AngularAccelerationType angularAccelerationType) {
    AngularAcceleration angularAcceleration = new AngularAcceleration();

    angularAcceleration.setName(angularAccelerationType.getName());
    List<Variable> variables = context.variableTypeConverter.convert(angularAccelerationType.getVariable());
    angularAcceleration.setVariables(variables);

    return angularAcceleration;
  }
}
