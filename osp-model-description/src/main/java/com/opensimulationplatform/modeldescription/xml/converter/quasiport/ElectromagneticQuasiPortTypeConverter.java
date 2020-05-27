package com.opensimulationplatform.modeldescription.xml.converter.quasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.ElectromagneticQuasiPortType;

public class ElectromagneticQuasiPortTypeConverter extends Converter<ElectromagneticQuasiPortType, ElectromagneticQuasiPort> {

  public ElectromagneticQuasiPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public ElectromagneticQuasiPort convert(ElectromagneticQuasiPortType linearMechanicalPortType) {
    ElectromagneticQuasiPort linearMechanicalPort = new ElectromagneticQuasiPort();

    Voltage voltage = context.voltageTypeConverter.convert(linearMechanicalPortType.getVoltage());
    Charge charge = context.chargeTypeConverter.convert(linearMechanicalPortType.getCharge());

    context.modelDescription.getVoltages().add(voltage);
    context.modelDescription.getCharges().add(charge);

    linearMechanicalPort.setName(linearMechanicalPortType.getName());
    linearMechanicalPort.setVoltage(voltage);
    linearMechanicalPort.setCharge(charge);

    return linearMechanicalPort;
  }
}
