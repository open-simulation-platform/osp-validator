package com.opensimulationplatform.modeldescription.xml.converter.quasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.AngularMechanicalQuasiPortType;

import java.util.List;
import java.util.stream.Collectors;

public class AngularMechanicalQuasiPortTypeConverter extends Converter<AngularMechanicalQuasiPortType, AngularMechanicalQuasiPort> {

  public AngularMechanicalQuasiPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public AngularMechanicalQuasiPort convert(AngularMechanicalQuasiPortType angularMechanicalPortType) {
    AngularMechanicalQuasiPort angularMechanicalPort = new AngularMechanicalQuasiPort();

    Torque torque = context.torqueTypeConverter.convert(angularMechanicalPortType.getTorque());
    AngularDisplacement angularDisplacement = context.angularDisplacementTypeConverter.convert(angularMechanicalPortType.getAngularDisplacement());

    context.modelDescription.getTorques().add(torque);
    context.modelDescription.getAngularDisplacements().add(angularDisplacement);

    angularMechanicalPort.setName(angularMechanicalPortType.getName());
    angularMechanicalPort.setTorque(torque);
    angularMechanicalPort.setAngularDisplacement(angularDisplacement);

    return angularMechanicalPort;
  }

  @Override
  public List<AngularMechanicalQuasiPort> convert(List<AngularMechanicalQuasiPortType> angularMechanicalPortTypes) {
    return angularMechanicalPortTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
