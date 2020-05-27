package com.opensimulationplatform.modeldescription.xml.converter.port;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalport.LinearMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.LinearMechanicalPortType;

public class LinearMechanicalPortTypeConverter extends Converter<LinearMechanicalPortType, LinearMechanicalPort> {

  public LinearMechanicalPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public LinearMechanicalPort convert(LinearMechanicalPortType linearMechanicalPortType) {
    LinearMechanicalPort linearMechanicalPort = new LinearMechanicalPort();

    Force force = context.forceTypeConverter.convert(linearMechanicalPortType.getForce());
    LinearVelocity linearVelocity = context.linearVelocityTypeConverter.convert(linearMechanicalPortType.getLinearVelocity());

    context.modelDescription.getForces().add(force);
    context.modelDescription.getLinearVelocities().add(linearVelocity);

    linearMechanicalPort.setName(linearMechanicalPortType.getName());
    linearMechanicalPort.setForce(force);
    linearMechanicalPort.setLinearVelocity(linearVelocity);

    return linearMechanicalPort;
  }
}
