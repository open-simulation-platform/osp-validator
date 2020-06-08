/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.systemstructure.xml.model.Connections;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OspSystemStructureConverter extends Converter<OspSystemStructure, SystemStructure> {

  public OspSystemStructureConverter(ConverterContext context) {
    super(context);
  }

  @Override
  public SystemStructure convert(OspSystemStructure ospSystemStructureElement) {
    SystemStructure systemStructure = context.systemStructure;

    List<Simulators.Simulator> simulatorElements = ospSystemStructureElement.getSimulators().getSimulator();
    List<Simulator> simulators = context.simulatorConverter.convert(simulatorElements);
    systemStructure.getSimulators().addAll(simulators);

    List<Object> connectionElements = ospSystemStructureElement.getConnections().getVariableConnectionOrSignalConnectionOrVariableGroupConnection();
    List<Connections.VariableConnection> variableConnectionElements = new ArrayList<>();
    List<Connections.VariableGroupConnection> variableGroupConnectionElements = new ArrayList<>();
    for (Object connectionElement : connectionElements) {
      if (connectionElement instanceof Connections.VariableConnection) {
        variableConnectionElements.add((Connections.VariableConnection) connectionElement);
      } else if (connectionElement instanceof Connections.VariableGroupConnection) {
        variableGroupConnectionElements.add((Connections.VariableGroupConnection) connectionElement);
      }
    }

    List<VariableConnection> variableConnections = context.variableConnectionConverter.convert(variableConnectionElements);
    systemStructure.getVariableConnections().addAll(variableConnections);

    List<VariableGroupConnection> variableGroupConnections = context.variableGroupConnectionConverter.convert(variableGroupConnectionElements);
    systemStructure.getVariableGroupConnections().addAll(variableGroupConnections);

    return systemStructure;
  }

  @Override
  public List<SystemStructure> convert(List<OspSystemStructure> ospSystemStructureElements) {
    return ospSystemStructureElements.stream().map(this::convert).collect(Collectors.toList());
  }
}
