package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch.BladePitch;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.model.BladePitchType;

import java.util.List;

public class BladePitchTypeConverter extends Converter<BladePitchType, BladePitch> {
  @Override
  public BladePitch convert(BladePitchType bladePitchType) {
    BladePitch bladePitch = new BladePitch();

    bladePitch.setName(bladePitchType.getName());

    List<Variable> variable = context.variableTypeConverter.convert(bladePitchType.getVariable());
    bladePitch.setVariables(variable);

    return bladePitch;
  }
}
