/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.port;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.HydraulicPortType;

public class HydraulicPortTypeConverter extends Converter<HydraulicPortType, HydraulicPort> {

  public HydraulicPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public HydraulicPort convert(HydraulicPortType electromagneticPortType) {
    HydraulicPort electromagneticPort = new HydraulicPort();

    Pressure pressure = context.pressureTypeConverter.convert(electromagneticPortType.getPressure());
    VolumeFlowRate volumeFlowRate = context.volumeFlowRateTypeConverter.convert(electromagneticPortType.getVolumeFlowRate());

    context.modelDescription.getPressures().add(pressure);
    context.modelDescription.getVolumeFlowRates().add(volumeFlowRate);

    electromagneticPort.setName(electromagneticPortType.getName());
    electromagneticPort.setPressure(pressure);
    electromagneticPort.setVolumeFlowRate(volumeFlowRate);

    return electromagneticPort;
  }
}
