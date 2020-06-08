/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.util.modeldescription.ModelDescriptionUtil;
import com.opensimulationplatform.core.util.systemstructure.SystemStructureUtil;
import com.opensimulationplatform.systemstructure.xml.model.Connections;
import com.opensimulationplatform.systemstructure.xml.model.VariableEndpoint;

import java.util.List;
import java.util.stream.Collectors;

public class VariableConnectionConverter extends Converter<Connections.VariableConnection, VariableConnection> {

  public VariableConnectionConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  public VariableConnectionConverter() {
  }

  @Override
  public VariableConnection convert(Connections.VariableConnection variableConnectionElement) {
    VariableConnection variableConnection = new VariableConnection();

    List<VariableEndpoint> variableEndpoints = variableConnectionElement.getVariable();
    VariableEndpoint variableEndpointA = variableEndpoints.get(0);
    VariableEndpoint variableEndpointB = variableEndpoints.get(1);

    Simulator simulatorA = SystemStructureUtil.getSimulatorByName(context.systemStructure, variableEndpointA.getSimulator()).get();
    Variable variableA = ModelDescriptionUtil.getVariableByName(simulatorA.getModelDescription(), variableEndpointA.getName());
    variableConnection.setSimulatorA(simulatorA);
    variableConnection.setVariableA(variableA);

    Simulator simulatorB = SystemStructureUtil.getSimulatorByName(context.systemStructure, variableEndpointB.getSimulator()).get();
    Variable variableB = ModelDescriptionUtil.getVariableByName(simulatorB.getModelDescription(), variableEndpointB.getName());
    variableConnection.setSimulatorB(simulatorB);
    variableConnection.setVariableB(variableB);

    return variableConnection;
  }

  @Override
  public List<VariableConnection> convert(List<Connections.VariableConnection> variableConnectionElements) {
    return variableConnectionElements.stream().map(this::convert).collect(Collectors.toList());
  }
}
