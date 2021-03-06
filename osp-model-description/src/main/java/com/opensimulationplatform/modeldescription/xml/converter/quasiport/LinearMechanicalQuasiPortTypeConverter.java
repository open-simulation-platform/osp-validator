/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.quasiport;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.LinearMechanicalQuasiPortType;

public class LinearMechanicalQuasiPortTypeConverter extends Converter<LinearMechanicalQuasiPortType, LinearMechanicalQuasiPort> {

  public LinearMechanicalQuasiPortTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public LinearMechanicalQuasiPort convert(LinearMechanicalQuasiPortType linearMechanicalPortType) {
    LinearMechanicalQuasiPort linearMechanicalPort = new LinearMechanicalQuasiPort();

    Force force = context.forceTypeConverter.convert(linearMechanicalPortType.getForce());
    LinearDisplacement linearDisplacement = context.linearDisplacementTypeConverter.convert(linearMechanicalPortType.getLinearDisplacement());

    context.modelDescription.getForces().add(force);
    context.modelDescription.getLinearDisplacements().add(linearDisplacement);

    linearMechanicalPort.setName(linearMechanicalPortType.getName());
    linearMechanicalPort.setForce(force);
    linearMechanicalPort.setLinearDisplacement(linearDisplacement);

    return linearMechanicalPort;
  }
}
