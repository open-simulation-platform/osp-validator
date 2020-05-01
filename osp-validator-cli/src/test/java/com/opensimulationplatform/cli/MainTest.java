package com.opensimulationplatform.cli;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.systemstructure.SystemStructureValidator;
import com.opensimulationplatform.systemstructure.xml.factory.SystemStructureFactory;
import com.opensimulationplatform.systemstructure.xml.model.Connections;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;
import com.opensimulationplatform.systemstructure.xml.model.VariableEndpoint;
import com.opensimulationplatform.systemstructure.xml.parser.OspSystemStructureParser;
import org.junit.Test;

import javax.xml.stream.Location;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {
  @Test
  public void brainstorming() {
    File ospSystemStructureFile = new File("./src/test/resources/OspSystemStructure_Crane.xml");

    OspSystemStructureParser parser = new OspSystemStructureParser();
    OspSystemStructure ospSystemStructureElement = parser.parse(ospSystemStructureFile);
    Map<Object, Location> locations = parser.getLocations();

    SystemStructureFactory factory = new SystemStructureFactory();
    factory.setFmuLocator(new MockFmuLocator());
    SystemStructure systemStructure = factory.create(ospSystemStructureFile);

    SystemStructureValidator validator = new SystemStructureValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(systemStructure);

    Map<Object, Object> coreToJaxb = createMap(systemStructure, ospSystemStructureElement);

    List<String> errorMessages = new ArrayList<>();
    for (ValidationDiagnostic<Object> diagnostic : diagnostics) {
      Object coreObject = diagnostic.getValidatedObject();
      Object jaxbObject = coreToJaxb.get(coreObject);
      if (jaxbObject != null) {
        Location location = locations.get(jaxbObject);
        errorMessages.add("Validation error on line " + location.getLineNumber() + ": " + diagnostic.getErrorMessage());
      } else {
        errorMessages.add("Validation error: " + diagnostic.getErrorMessage());
      }
    }

    System.out.println(errorMessages);
  }

  private Map<Object, Object> createMap(SystemStructure systemStructure, OspSystemStructure ospSystemStructureElement) {
    HashMap<Object, Object> map = new HashMap<>();

    List<Simulator> simulators = systemStructure.getSimulators();
    List<VariableConnection> variableConnections = systemStructure.getVariableConnections();
    List<VariableGroupConnection> variableGroupConnections = systemStructure.getVariableGroupConnections();

    List<Simulators.Simulator> simulatorElements = ospSystemStructureElement.getSimulators().getSimulator();
    List<Connections.VariableConnection> variableConnectionElements = ospSystemStructureElement.getConnections().getVariableConnection();
    List<Connections.VariableGroupConnection> variableGroupConnectionElements = ospSystemStructureElement.getConnections().getVariableGroupConnection();

    for (Simulator simulator : simulators) {
      for (Simulators.Simulator simulatorElement : simulatorElements) {
        if (simulator.getName().get().equals(simulatorElement.getName())) {
          map.put(simulator, simulatorElement);
          break;
        }
      }
    }

    for (VariableConnection variableConnection : variableConnections) {
      Variable variableA = variableConnection.getVariableA();
      Simulator simulatorA = variableConnection.getSimulatorA();
      Variable variableB = variableConnection.getVariableB();
      Simulator simulatorB = variableConnection.getSimulatorB();
      for (Connections.VariableConnection variableConnectionElement : variableConnectionElements) {
        List<VariableEndpoint> variableEndpointElements = variableConnectionElement.getVariable();
        String variableAName = variableEndpointElements.get(0).getName();
        String simulatorAName = variableEndpointElements.get(0).getSimulator();
        String variableBName = variableEndpointElements.get(1).getName();
        String simulatorBName = variableEndpointElements.get(1).getSimulator();

        if (variableA.getName().get().equals(variableAName)
            && simulatorA.getName().get().equals(simulatorAName)
            && variableB.getName().get().equals(variableBName)
            && simulatorB.getName().get().equals(simulatorBName)) {
          map.put(variableConnection, variableConnectionElement);
          break;
        }
      }
    }

    for (VariableGroupConnection variableGroupConnection : variableGroupConnections) {
      VariableGroup variableGroupA = variableGroupConnection.getVariableGroupA();
      Simulator simulatorA = variableGroupConnection.getSimulatorA();
      VariableGroup variableGroupB = variableGroupConnection.getVariableGroupB();
      Simulator simulatorB = variableGroupConnection.getSimulatorB();
      for (Connections.VariableGroupConnection variableGroupConnectionElement : variableGroupConnectionElements) {
        List<VariableEndpoint> variableEndpointElements = variableGroupConnectionElement.getVariableGroup();
        String variableGroupAName = variableEndpointElements.get(0).getName();
        String simulatorAName = variableEndpointElements.get(0).getSimulator();
        String variableGroupBName = variableEndpointElements.get(1).getName();
        String simulatorBName = variableEndpointElements.get(1).getSimulator();

        if (variableGroupA.getName().get().equals(variableGroupAName)
            && simulatorA.getName().get().equals(simulatorAName)
            && variableGroupB.getName().get().equals(variableGroupBName)
            && simulatorB.getName().get().equals(simulatorBName)) {
          map.put(variableGroupConnection, variableGroupConnectionElement);
          break;
        }
      }
    }

    return map;
  }
}