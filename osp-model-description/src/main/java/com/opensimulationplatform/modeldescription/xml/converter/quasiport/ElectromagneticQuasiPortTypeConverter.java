package com.opensimulationplatform.modeldescription.xml.converter.quasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.ElectromagneticQuasiPortType;

import java.util.List;
import java.util.stream.Collectors;

public class ElectromagneticQuasiPortTypeConverter extends Converter<ElectromagneticQuasiPortType, ElectromagneticQuasiPort> {

  public ElectromagneticQuasiPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public ElectromagneticQuasiPort convert(ElectromagneticQuasiPortType linearMechanicalPortType) {
    ElectromagneticQuasiPort linearMechanicalPort = new ElectromagneticQuasiPort();

    Voltage voltage = converterContext.voltageTypeConverter.convert(linearMechanicalPortType.getVoltage());
    Charge charge = converterContext.chargeTypeConverter.convert(linearMechanicalPortType.getCharge());

    linearMechanicalPort.setName(linearMechanicalPortType.getName());
    linearMechanicalPort.setVoltage(voltage);
    linearMechanicalPort.setCharge(charge);

    return linearMechanicalPort;
  }

  @Override
  public List<ElectromagneticQuasiPort> convert(List<ElectromagneticQuasiPortType> linearMechanicalPortTypes) {
    return linearMechanicalPortTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
