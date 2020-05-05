package com.opensimulationplatform.modeldescription.xml.converter.displacement;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volume.Volume;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.VariableType;
import com.opensimulationplatform.modeldescription.xml.model.VolumeType;

import java.util.List;
import java.util.stream.Collectors;

public class VolumeTypeConverter extends Converter<VolumeType, Volume> {

  public VolumeTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Volume convert(VolumeType volumeType) {
    Volume volume = new Volume();

    volume.setName(volumeType.getName());

    List<VariableType> variableTypes = volumeType.getVariable();
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    volume.setVariables(variables);

    return volume;
  }

  @Override
  public List<Volume> convert(List<VolumeType> volumeTypes) {
    return volumeTypes.stream().map(this::convert).collect(Collectors.toList());
  }
}
