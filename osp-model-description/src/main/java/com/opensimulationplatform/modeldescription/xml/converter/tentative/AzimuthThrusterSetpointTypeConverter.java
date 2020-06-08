/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.azimuthangle.AzimuthAngle;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.azimuththrustersetpoint.AzimuthThrusterSetpoint;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch.BladePitch;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed.ShaftSpeed;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.AzimuthThrusterSetpointType;

public class AzimuthThrusterSetpointTypeConverter extends Converter<AzimuthThrusterSetpointType, AzimuthThrusterSetpoint> {
  public AzimuthThrusterSetpointTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public AzimuthThrusterSetpoint convert(AzimuthThrusterSetpointType azimuthThrusterSetpointType) {
    AzimuthThrusterSetpoint azimuthThrusterSetpoint = new AzimuthThrusterSetpoint();

    azimuthThrusterSetpoint.setName(azimuthThrusterSetpointType.getName());

    AzimuthAngle azimuthAngle = context.azimuthAngleTypeConverter.convert(azimuthThrusterSetpointType.getAzimuthAngle());
    BladePitch bladePitch = context.bladePitchTypeConverter.convert(azimuthThrusterSetpointType.getBladePitch());
    ShaftSpeed shaftSpeed = context.shaftSpeedTypeConverter.convert(azimuthThrusterSetpointType.getShaftSpeed());

    context.modelDescription.getAzimuthAngles().add(azimuthAngle);
    context.modelDescription.getBladePitches().add(bladePitch);
    context.modelDescription.getShaftSpeeds().add(shaftSpeed);

    azimuthThrusterSetpoint.setAzimuthAngle(azimuthAngle);
    azimuthThrusterSetpoint.setBladePitch(bladePitch);
    azimuthThrusterSetpoint.setShaftSpeed(shaftSpeed);

    return azimuthThrusterSetpoint;
  }
}
