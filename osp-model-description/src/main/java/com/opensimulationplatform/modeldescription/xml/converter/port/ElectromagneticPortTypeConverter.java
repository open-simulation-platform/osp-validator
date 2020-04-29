package com.opensimulationplatform.modeldescription.xml.converter.port;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.current.Current;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticport.ElectromagneticPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.ElectromagneticPortType;

import java.util.List;
import java.util.stream.Collectors;

public class ElectromagneticPortTypeConverter extends Converter<ElectromagneticPortType, ElectromagneticPort> {

  public ElectromagneticPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public ElectromagneticPort convert(ElectromagneticPortType electromagneticPortType) {
    ElectromagneticPort electromagneticPort = new ElectromagneticPort();

    Voltage voltage = converterContext.voltageTypeConverter.convert(electromagneticPortType.getVoltage());
    Current current = converterContext.currentTypeConverter.convert(electromagneticPortType.getCurrent());

    electromagneticPort.setName(electromagneticPortType.getName());
    electromagneticPort.setVoltage(voltage);
    electromagneticPort.setCurrent(current);

    return electromagneticPort;
  }

  @Override
  public List<ElectromagneticPort> convert(List<ElectromagneticPortType> electromagneticPortTypes) {
    return electromagneticPortTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
