package com.opensimulationplatform.systemstructure.xml.converter;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.util.modeldescription.ModelDescriptionUtil;
import com.opensimulationplatform.systemstructure.util.SystemStructureUtil;
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

    Simulator simulatorA = SystemStructureUtil.getSimulatorByName(converterContext.systemStructure, variableEndpointA.getSimulator());
    Variable variableA = ModelDescriptionUtil.getVariableByName(simulatorA.getModelDescription(), variableEndpointA.getName());
    variableConnection.setSimulatorA(simulatorA);
    variableConnection.setVariableA(variableA);

    Simulator simulatorB = SystemStructureUtil.getSimulatorByName(converterContext.systemStructure, variableEndpointB.getSimulator());
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
