/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch.BladePitch;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.BladePitchType;

import java.util.List;

public class BladePitchTypeConverter extends Converter<BladePitchType, BladePitch> {
  public BladePitchTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public BladePitch convert(BladePitchType bladePitchType) {
    BladePitch bladePitch = new BladePitch();

    bladePitch.setName(bladePitchType.getName());

    List<Variable> variable = context.variableTypeConverter.convert(bladePitchType.getVariable());
    bladePitch.setVariables(variable);

    return bladePitch;
  }
}
