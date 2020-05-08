package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.systemstructure.xml.model.Connections;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;

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

    List<Connections.VariableConnection> variableConnectionElements = ospSystemStructureElement.getConnections().getVariableConnection();
    List<VariableConnection> variableConnections = context.variableConnectionConverter.convert(variableConnectionElements);
    systemStructure.getVariableConnections().addAll(variableConnections);

    List<Connections.VariableGroupConnection> variableGroupConnectionElements = ospSystemStructureElement.getConnections().getVariableGroupConnection();
    List<VariableGroupConnection> variableGroupConnections = context.variableGroupConnectionConverter.convert(variableGroupConnectionElements);
    systemStructure.getVariableGroupConnections().addAll(variableGroupConnections);

    return systemStructure;
  }

  @Override
  public List<SystemStructure> convert(List<OspSystemStructure> ospSystemStructureElements) {
    return ospSystemStructureElements.stream().map(this::convert).collect(Collectors.toList());
  }
}
