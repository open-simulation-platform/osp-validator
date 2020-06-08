/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearacceleration.LinearAcceleration;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.LinearAccelerationType;

import java.util.List;

public class LinearAccelerationTypeConverter extends Converter<LinearAccelerationType, LinearAcceleration> {
  public LinearAccelerationTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public LinearAcceleration convert(LinearAccelerationType linearAccelerationType) {
    LinearAcceleration linearAcceleration = new LinearAcceleration();

    linearAcceleration.setName(linearAccelerationType.getName());
    List<Variable> variables = context.variableTypeConverter.convert(linearAccelerationType.getVariable());
    linearAcceleration.setVariables(variables);

    return linearAcceleration;
  }
}
