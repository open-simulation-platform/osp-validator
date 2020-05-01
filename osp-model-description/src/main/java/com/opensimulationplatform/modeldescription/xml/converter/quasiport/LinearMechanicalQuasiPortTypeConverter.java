package com.opensimulationplatform.modeldescription.xml.converter.quasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.LinearMechanicalQuasiPortType;

import java.util.List;
import java.util.stream.Collectors;

public class LinearMechanicalQuasiPortTypeConverter extends Converter<LinearMechanicalQuasiPortType, LinearMechanicalQuasiPort> {

  public LinearMechanicalQuasiPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public LinearMechanicalQuasiPort convert(LinearMechanicalQuasiPortType linearMechanicalPortType) {
    LinearMechanicalQuasiPort linearMechanicalPort = new LinearMechanicalQuasiPort();

    Force force = converterContext.forceTypeConverter.convert(linearMechanicalPortType.getForce());
    LinearDisplacement linearDisplacement = converterContext.linearDisplacementTypeConverter.convert(linearMechanicalPortType.getLinearDisplacement());

    converterContext.modelDescription.getForces().add(force);
    converterContext.modelDescription.getLinearDisplacements().add(linearDisplacement);

    linearMechanicalPort.setName(linearMechanicalPortType.getName());
    linearMechanicalPort.setForce(force);
    linearMechanicalPort.setLinearDisplacement(linearDisplacement);

    return linearMechanicalPort;
  }

  @Override
  public List<LinearMechanicalQuasiPort> convert(List<LinearMechanicalQuasiPortType> linearMechanicalPortTypes) {
    return linearMechanicalPortTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
