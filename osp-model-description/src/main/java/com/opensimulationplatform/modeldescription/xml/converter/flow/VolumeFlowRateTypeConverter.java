/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.flow;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;
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
    List<Variable> variables = variableTypes.stream().map(context.variableTypeConverter::convert).collect(Collectors.toList());
    volumeFlowRate.setVariables(variables);

    return volumeFlowRate;
  }
}
