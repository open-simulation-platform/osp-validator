package com.opensimulationplatform.modeldescription.xml.converter.port;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalport.LinearMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.LinearMechanicalPortType;

import java.util.List;
import java.util.stream.Collectors;

public class LinearMechanicalPortTypeConverter extends Converter<LinearMechanicalPortType, LinearMechanicalPort> {

  public LinearMechanicalPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public LinearMechanicalPort convert(LinearMechanicalPortType linearMechanicalPortType) {
    LinearMechanicalPort linearMechanicalPort = new LinearMechanicalPort();

    Force force = converterContext.forceTypeConverter.convert(linearMechanicalPortType.getForce());
    LinearVelocity linearVelocity = converterContext.linearVelocityTypeConverter.convert(linearMechanicalPortType.getLinearVelocity());

    linearMechanicalPort.setName(linearMechanicalPortType.getName());
    linearMechanicalPort.setForce(force);
    linearMechanicalPort.setLinearVelocity(linearVelocity);

    return linearMechanicalPort;
  }

  @Override
  public List<LinearMechanicalPort> convert(List<LinearMechanicalPortType> linearMechanicalPortTypes) {
    return linearMechanicalPortTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
