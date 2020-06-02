/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.quasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.AngularMechanicalQuasiPortType;

public class AngularMechanicalQuasiPortTypeConverter extends Converter<AngularMechanicalQuasiPortType, AngularMechanicalQuasiPort> {

  public AngularMechanicalQuasiPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public AngularMechanicalQuasiPort convert(AngularMechanicalQuasiPortType angularMechanicalPortType) {
    AngularMechanicalQuasiPort angularMechanicalPort = new AngularMechanicalQuasiPort();

    Torque torque = context.torqueTypeConverter.convert(angularMechanicalPortType.getTorque());
    AngularDisplacement angularDisplacement = context.angularDisplacementTypeConverter.convert(angularMechanicalPortType.getAngularDisplacement());

    context.modelDescription.getTorques().add(torque);
    context.modelDescription.getAngularDisplacements().add(angularDisplacement);

    angularMechanicalPort.setName(angularMechanicalPortType.getName());
    angularMechanicalPort.setTorque(torque);
    angularMechanicalPort.setAngularDisplacement(angularDisplacement);

    return angularMechanicalPort;
  }
}
