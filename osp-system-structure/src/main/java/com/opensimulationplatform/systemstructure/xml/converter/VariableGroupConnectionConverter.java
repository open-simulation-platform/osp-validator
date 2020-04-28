package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.util.modeldescription.ModelDescriptionUtil;
import com.opensimulationplatform.systemstructure.util.SystemStructureUtil;
import com.opensimulationplatform.systemstructure.xml.model.Connections;
import com.opensimulationplatform.systemstructure.xml.model.VariableEndpoint;

import java.util.List;
import java.util.stream.Collectors;

public class VariableGroupConnectionConverter extends Converter<Connections.VariableGroupConnection, VariableGroupConnection> {

  public VariableGroupConnectionConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  public VariableGroupConnectionConverter() {
  }

  @Override
  public VariableGroupConnection convert(Connections.VariableGroupConnection variableGroupConnectionElement) {
    VariableGroupConnection variableGroupConnection = new VariableGroupConnection();

    List<VariableEndpoint> variableEndpoints = variableGroupConnectionElement.getVariableGroup();
    VariableEndpoint variableEndpointA = variableEndpoints.get(0);
    VariableEndpoint variableEndpointB = variableEndpoints.get(1);

    Simulator simulatorA = SystemStructureUtil.getSimulatorByName(context.systemStructure, variableEndpointA.getSimulator());
    VariableGroup variableGroupA = ModelDescriptionUtil.getVariableGroupByName(simulatorA.getModelDescription(), variableEndpointA.getName());
    variableGroupConnection.setSimulatorA(simulatorA);
    variableGroupConnection.setVariableGroupA(variableGroupA);

    Simulator simulatorB = SystemStructureUtil.getSimulatorByName(context.systemStructure, variableEndpointB.getSimulator());
    VariableGroup variableGroupB = ModelDescriptionUtil.getVariableGroupByName(simulatorB.getModelDescription(), variableEndpointB.getName());
    variableGroupConnection.setSimulatorB(simulatorB);
    variableGroupConnection.setVariableGroupB(variableGroupB);

    return variableGroupConnection;
  }

  @Override
  public List<VariableGroupConnection> convert(List<Connections.VariableGroupConnection> variableGroupConnectionElements) {
    return variableGroupConnectionElements.stream().map(this::convert).collect(Collectors.toList());
  }
}
