package com.opensimulationplatform.modeldescription.xml.converter.quasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.Volume;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport.HydraulicQuasiPort;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.HydraulicQuasiPortType;

import java.util.List;
import java.util.stream.Collectors;

public class HydraulicQuasiPortTypeConverter extends Converter<HydraulicQuasiPortType, HydraulicQuasiPort> {

  public HydraulicQuasiPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public HydraulicQuasiPort convert(HydraulicQuasiPortType linearMechanicalPortType) {
    HydraulicQuasiPort linearMechanicalPort = new HydraulicQuasiPort();

    Pressure pressure = converterContext.pressureTypeConverter.convert(linearMechanicalPortType.getPressure());
    Volume volume = converterContext.volumeTypeConverter.convert(linearMechanicalPortType.getVolume());

    linearMechanicalPort.setName(linearMechanicalPortType.getName());
    linearMechanicalPort.setPressure(pressure);
    linearMechanicalPort.setVolume(volume);

    return linearMechanicalPort;
  }

  @Override
  public List<HydraulicQuasiPort> convert(List<HydraulicQuasiPortType> linearMechanicalPortTypes) {
    return linearMechanicalPortTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
