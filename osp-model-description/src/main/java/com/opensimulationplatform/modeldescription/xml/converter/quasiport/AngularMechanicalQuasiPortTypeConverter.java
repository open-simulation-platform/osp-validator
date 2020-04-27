package com.opensimulationplatform.modeldescription.xml.converter.quasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport.AngularMechanicalQuasiPort;
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

    Torque torque = converterContext.torqueTypeConverter.convert(angularMechanicalPortType.getTorque());
    AngularDisplacement angularDisplacement = converterContext.angularDisplacementTypeConverter.convert(angularMechanicalPortType.getAngularDisplacement());

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
