package com.opensimulationplatform.modeldescription.xml.converter.flow;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.VolumeFlowRate;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;
import com.opensimulationplatform.modeldescription.xml.model.VolumeFlowRateType;

import java.util.List;
import java.util.stream.Collectors;

public class VolumeFlowRateTypeConverter extends Converter<VolumeFlowRateType, VolumeFlowRate> {

  public VolumeFlowRateTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public VolumeFlowRate convert(VolumeFlowRateType volumeFlowRateType) {
    VolumeFlowRate volumeFlowRate = new VolumeFlowRate();

    volumeFlowRate.setName(volumeFlowRateType.getName());
    List<VariableType> variableTypes = volumeFlowRateType.getVariable();
    List<Variable> variables = variableTypes.stream().map(converterContext.variableTypeConverter::convert).collect(Collectors.toList());
    volumeFlowRate.setVariables(variables);

    return volumeFlowRate;
  }

  @Override
  public List<VolumeFlowRate> convert(List<VolumeFlowRateType> volumeFlowRateTypes) {
    return volumeFlowRateTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
