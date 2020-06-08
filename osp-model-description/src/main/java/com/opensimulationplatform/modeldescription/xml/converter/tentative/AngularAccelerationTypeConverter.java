/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.tentative;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularacceleration.AngularAcceleration;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.AngularAccelerationType;

import java.util.List;

public class AngularAccelerationTypeConverter extends Converter<AngularAccelerationType, AngularAcceleration> {
  public AngularAccelerationTypeConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public AngularAcceleration convert(AngularAccelerationType angularAccelerationType) {
    AngularAcceleration angularAcceleration = new AngularAcceleration();

    angularAcceleration.setName(angularAccelerationType.getName());
    List<Variable> variables = context.variableTypeConverter.convert(angularAccelerationType.getVariable());
    angularAcceleration.setVariables(variables);

    return angularAcceleration;
  }
}
