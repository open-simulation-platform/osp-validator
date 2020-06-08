/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.quasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicquasiport.HydraulicQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volume.Volume;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.HydraulicQuasiPortType;

public class HydraulicQuasiPortTypeConverter extends Converter<HydraulicQuasiPortType, HydraulicQuasiPort> {

  public HydraulicQuasiPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public HydraulicQuasiPort convert(HydraulicQuasiPortType linearMechanicalPortType) {
    HydraulicQuasiPort linearMechanicalPort = new HydraulicQuasiPort();

    Pressure pressure = context.pressureTypeConverter.convert(linearMechanicalPortType.getPressure());
    Volume volume = context.volumeTypeConverter.convert(linearMechanicalPortType.getVolume());

    context.modelDescription.getPressures().add(pressure);
    context.modelDescription.getVolumes().add(volume);

    linearMechanicalPort.setName(linearMechanicalPortType.getName());
    linearMechanicalPort.setPressure(pressure);
    linearMechanicalPort.setVolume(volume);

    return linearMechanicalPort;
  }
}
