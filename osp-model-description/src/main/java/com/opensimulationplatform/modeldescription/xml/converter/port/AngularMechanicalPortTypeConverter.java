package com.opensimulationplatform.modeldescription.xml.converter.port;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalport.AngularMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.AngularMechanicalPortType;

import java.util.List;
import java.util.stream.Collectors;

public class AngularMechanicalPortTypeConverter extends Converter<AngularMechanicalPortType, AngularMechanicalPort> {

  public AngularMechanicalPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public AngularMechanicalPort convert(AngularMechanicalPortType angularMechanicalPortType) {
    AngularMechanicalPort angularMechanicalPort = new AngularMechanicalPort();

    Torque torque = context.torqueTypeConverter.convert(angularMechanicalPortType.getTorque());
    AngularVelocity angularVelocity = context.angularVelocityTypeConverter.convert(angularMechanicalPortType.getAngularVelocity());

    context.modelDescription.getTorques().add(torque);
    context.modelDescription.getAngularVelocities().add(angularVelocity);

    angularMechanicalPort.setName(angularMechanicalPortType.getName());
    angularMechanicalPort.setTorque(torque);
    angularMechanicalPort.setAngularVelocity(angularVelocity);

    return angularMechanicalPort;
  }

  @Override
  public List<AngularMechanicalPort> convert(List<AngularMechanicalPortType> angularMechanicalPortTypes) {
    return angularMechanicalPortTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
