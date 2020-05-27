package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch.BladePitch;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedthrusterfeedback.FixedThrusterFeedback;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed.ShaftSpeed;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.FixedThrusterFeedbackType;

public class FixedThrusterFeedbackTypeConverter extends Converter<FixedThrusterFeedbackType, FixedThrusterFeedback> {
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
