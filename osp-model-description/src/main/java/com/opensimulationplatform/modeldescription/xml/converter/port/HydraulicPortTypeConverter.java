package com.opensimulationplatform.modeldescription.xml.converter.port;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.HydraulicPortType;

import java.util.List;
import java.util.stream.Collectors;

public class HydraulicPortTypeConverter extends Converter<HydraulicPortType, HydraulicPort> {

  public HydraulicPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public HydraulicPort convert(HydraulicPortType electromagneticPortType) {
    HydraulicPort electromagneticPort = new HydraulicPort();

    Pressure pressure = converterContext.pressureTypeConverter.convert(electromagneticPortType.getPressure());
    VolumeFlowRate volumeFlowRate = converterContext.volumeFlowRateTypeConverter.convert(electromagneticPortType.getVolumeFlowRate());

    electromagneticPort.setName(electromagneticPortType.getName());
    electromagneticPort.setPressure(pressure);
    electromagneticPort.setVolumeFlowRate(volumeFlowRate);

    return electromagneticPort;
  }

  @Override
  public List<HydraulicPort> convert(List<HydraulicPortType> electromagneticPortTypes) {
    return electromagneticPortTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
