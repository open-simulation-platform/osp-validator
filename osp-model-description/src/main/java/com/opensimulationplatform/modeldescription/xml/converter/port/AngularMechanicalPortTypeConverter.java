/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.port;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalport.AngularMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.AngularMechanicalPortType;

public class AngularMechanicalPortTypeConverter extends Converter<AngularMechanicalPortType, AngularMechanicalPort> {

  public AngularMechanicalPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public AngularMechanicalPort convert(AngularMechanicalPortType angularMechanicalPortType) {
    AngularMechanicalPort angularMechanicalPort = new AngularMechanicalPort();

    Torque torque = context.torqueTypeConverter.convert(angularMechanicalPortType.getTorque());
    AngularVelocity angularVelocity = context.angularVelocityTypeConverter.convert(angularMechanicalPortType.getAngularVelocity());

    context.modelDescription.getTorques().add(torque);
    context.modelDescription.getAngularVelocities().add(angularVelocity);

    angularMechanicalPort.setName(angularMechanicalPortType.getName());
    angularMechanicalPort.setTorque(torque);
    angularMechanicalPort.setAngularVelocity(angularVelocity);

    return angularMechanicalPort;
  }
}
