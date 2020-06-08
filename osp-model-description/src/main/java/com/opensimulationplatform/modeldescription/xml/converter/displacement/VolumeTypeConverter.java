/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

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
}
