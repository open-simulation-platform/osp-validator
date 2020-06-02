/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch.BladePitch;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedthrusterfeedback.FixedThrusterFeedback;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed.ShaftSpeed;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.FixedThrusterFeedbackType;

public class FixedThrusterFeedbackTypeConverter extends Converter<FixedThrusterFeedbackType, FixedThrusterFeedback> {
  public FixedThrusterFeedbackTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public FixedThrusterFeedback convert(FixedThrusterFeedbackType fixedThrusterFeedbackType) {
    FixedThrusterFeedback fixedThrusterFeedback = new FixedThrusterFeedback();

    fixedThrusterFeedback.setName(fixedThrusterFeedbackType.getName());

    BladePitch bladePitch = context.bladePitchTypeConverter.convert(fixedThrusterFeedbackType.getBladePitch());
    Force force = context.forceTypeConverter.convert(fixedThrusterFeedbackType.getForce());
    ShaftSpeed shaftSpeed = context.shaftSpeedTypeConverter.convert(fixedThrusterFeedbackType.getShaftSpeed());

    fixedThrusterFeedback.setBladePitch(bladePitch);
    fixedThrusterFeedback.setForce(force);
    fixedThrusterFeedback.setShaftSpeed(shaftSpeed);

    return fixedThrusterFeedback;
  }
}
