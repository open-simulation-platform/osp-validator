/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch.BladePitch;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedthrustersetpoint.FixedThrusterSetpoint;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed.ShaftSpeed;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.FixedThrusterSetpointType;

public class FixedThrusterSetpointTypeConverter extends Converter<FixedThrusterSetpointType, FixedThrusterSetpoint> {
  public FixedThrusterSetpointTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public FixedThrusterSetpoint convert(FixedThrusterSetpointType fixedThrusterSetpointType) {
    FixedThrusterSetpoint fixedThrusterSetpoint = new FixedThrusterSetpoint();

    fixedThrusterSetpoint.setName(fixedThrusterSetpointType.getName());

    BladePitch bladePitch = context.bladePitchTypeConverter.convert(fixedThrusterSetpointType.getBladePitch());
    ShaftSpeed shaftSpeed = context.shaftSpeedTypeConverter.convert(fixedThrusterSetpointType.getShaftSpeed());

    fixedThrusterSetpoint.setBladePitch(bladePitch);
    fixedThrusterSetpoint.setShaftSpeed(shaftSpeed);

    return fixedThrusterSetpoint;
  }
}
