/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.cli;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import com.opensimulationplatform.core.model.systemstructure.SystemStructure;
import com.opensimulationplatform.core.model.systemstructure.VariableConnection;
import com.opensimulationplatform.core.model.systemstructure.VariableGroupConnection;
import com.opensimulationplatform.core.util.modeldescription.ModelDescriptionUtil;
import com.opensimulationplatform.core.util.systemstructure.SystemStructureUtil;
import com.opensimulationplatform.core.validation.ValidationDiagnostic;
import com.opensimulationplatform.core.validation.modeldescription.ModelDescriptionValidator;
import com.opensimulationplatform.core.validation.systemstructure.SystemStructureValidator;
import com.opensimulationplatform.modeldescription.xml.factory.ModelDescriptionFactory;
import com.opensimulationplatform.modeldescription.xml.model.*;
import com.opensimulationplatform.modeldescription.xml.parser.OspModelDescriptionParser;
import com.opensimulationplatform.systemstructure.util.DefaultFmuLocator;
import com.opensimulationplatform.systemstructure.util.DefaultOspModelDescriptionLocator;
import com.opensimulationplatform.systemstructure.util.FmuLocator;
import com.opensimulationplatform.systemstructure.util.OspModelDescriptionLocator;
import com.opensimulationplatform.systemstructure.xml.factory.SystemStructureFactory;
import com.opensimulationplatform.systemstructure.xml.model.Connections;
import com.opensimulationplatform.systemstructure.xml.model.OspSystemStructure;
import com.opensimulationplatform.systemstructure.xml.model.Simulators;
import com.opensimulationplatform.systemstructure.xml.model.VariableEndpoint;
import com.opensimulationplatform.systemstructure.xml.parser.OspSystemStructureParser;

import javax.xml.stream.Location;
import java.io.File;
import java.net.URI;
import java.util.*;

public class Validator {
  public List<String> validate(File ospSystemStructureFile) {
    List<String> errorMessages = new ArrayList<>();

    OspSystemStructureParser parser = new OspSystemStructureParser();
    OspSystemStructure ospSystemStructureElement = parser.parse(ospSystemStructureFile);
    Map<Object, Location> locations = parser.getLocations();

    errorMessages.addAll(getModelDescriptionErrorMessages(ospSystemStructureFile, ospSystemStructureElement));
    errorMessages.addAll(getSystemStructureErrorMessages(ospSystemStructureFile, ospSystemStructureElement, locations));

    return errorMessages;
  }

  public List<String> validate(File ospModelDescription, File fmu) {
    OspModelDescriptionParser parser = new OspModelDescriptionParser();
    OspModelDescriptionType ospModelDescriptionElement = parser.parse(ospModelDescription);
    Map<Object, Location> locations = parser.getLocations();

    ModelDescriptionFactory factory = new ModelDescriptionFactory();
    ModelDescription modelDescription = factory.create(ospModelDescription, fmu.toURI());
    Simulator s = new Simulator();
    s.setModelDescription(modelDescription);
    SystemStructure systemStructure = new SystemStructure();
    systemStructure.getSimulators().add(s);
    ModelDescriptionValidator validator = new ModelDescriptionValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(systemStructure);

    Map<Object, Object> coreToJaxb = createModelDescriptionMap(ospModelDescriptionElement, modelDescription);

    return new ArrayList<>(getErrorMessages(ospModelDescription, locations, diagnostics, coreToJaxb));
  }

  private List<String> getSystemStructureErrorMessages(File ospSystemStructureFile, OspSystemStructure ospSystemStructureElement, Map<Object, Location> locations) {
    SystemStructureFactory factory = new SystemStructureFactory();
    SystemStructure systemStructure = factory.create(ospSystemStructureFile);

    SystemStructureValidator validator = new SystemStructureValidator();
    List<ValidationDiagnostic<Object>> diagnostics = validator.validate(systemStructure);

    Map<Object, Object> coreToJaxb = createSystemStructureMap(systemStructure, ospSystemStructureElement);

    return getErrorMessages(ospSystemStructureFile, locations, diagnostics, coreToJaxb);
  }

  private List<String> getModelDescriptionErrorMessages(File ospSystemStructureFile, OspSystemStructure ospSystemStructureElement) {
    List<String> errorMessages = new ArrayList<>();

    for (Simulators.Simulator simulator : ospSystemStructureElement.getSimulators().getSimulator()) {
      FmuLocator fmuLocator = new DefaultFmuLocator(ospSystemStructureFile);
      URI fmu = fmuLocator.get(simulator);

      OspModelDescriptionLocator ospModelDescriptionLocator = new DefaultOspModelDescriptionLocator(ospSystemStructureFile, fmuLocator);
      Optional<File> ospModelDescriptionXml = ospModelDescriptionLocator.get(simulator);

      if (ospModelDescriptionXml.isPresent()) {
        OspModelDescriptionParser parser = new OspModelDescriptionParser();
        OspModelDescriptionType ospModelDescriptionElement = parser.parse(ospModelDescriptionXml.get());
        Map<Object, Location> locations = parser.getLocations();

        ModelDescriptionFactory factory = new ModelDescriptionFactory();
        ModelDescription modelDescription = factory.create(ospModelDescriptionXml.get(), fmu);
        Simulator s = new Simulator();
        s.setName(simulator.getName());
        s.setModelDescription(modelDescription);
        SystemStructure systemStructure = new SystemStructure();
        systemStructure.getSimulators().add(s);
        ModelDescriptionValidator validator = new ModelDescriptionValidator();
        List<ValidationDiagnostic<Object>> diagnostics = validator.validate(systemStructure);

        Map<Object, Object> coreToJaxb = createModelDescriptionMap(ospModelDescriptionElement, modelDescription);

        errorMessages.addAll(getErrorMessages(ospModelDescriptionXml.get(), locations, diagnostics, coreToJaxb));
      }
    }

    return errorMessages;
  }

  private List<String> getErrorMessages(File file, Map<Object, Location> locations, List<ValidationDiagnostic<Object>> diagnostics, Map<Object, Object> coreToJaxb) {
    List<String> errorMessages = new ArrayList<>();

    for (ValidationDiagnostic<Object> diagnostic : diagnostics) {
      Object coreObject = diagnostic.getValidatedObject();
      Object jaxbObject = coreToJaxb.get(coreObject);
      if (jaxbObject != null) {
        Location location = locations.get(jaxbObject);
        errorMessages.add("Validation error in " + file.getAbsolutePath() + " on line " + location.getLineNumber() + ": " + diagnostic.getErrorMessage());
      } else {
        errorMessages.add("Validation error in " + file.getAbsolutePath() + ": " + diagnostic.getErrorMessage());
      }
    }

    return errorMessages;
  }

  private Map<Object, Object> createModelDescriptionMap(OspModelDescriptionType ospModelDescriptionElement, ModelDescription modelDescription) {
    Map<Object, Object> map = new HashMap<>();

    VariableGroupsType variableGroups = ospModelDescriptionElement.getVariableGroups();
    if (variableGroups != null) {
      for (AngularDisplacementType element : variableGroups.getAngularDisplacement()) {
        addAngularDisplacement(modelDescription, map, element);
      }

      for (AngularMechanicalPortType element : variableGroups.getAngularMechanicalPort()) {
        addAngularMechanicalPort(modelDescription, map, element);
      }

      for (AngularMechanicalQuasiPortType element : variableGroups.getAngularMechanicalQuasiPort()) {
        addAngularMechanicalQuasiPort(modelDescription, map, element);
      }

      for (AngularVelocityType element : variableGroups.getAngularVelocity()) {
        addAngularVelocity(modelDescription, map, element);
      }

      for (ChargeType element : variableGroups.getCharge()) {
        addCharge(modelDescription, map, element);
      }

      for (CurrentType element : variableGroups.getCurrent()) {
        addCurrent(modelDescription, map, element);
      }

      for (ElectromagneticPortType element : variableGroups.getElectromagneticPort()) {
        addElectromagneticPort(modelDescription, map, element);
      }

      for (ElectromagneticQuasiPortType element : variableGroups.getElectromagneticQuasiPort()) {
        addElectromagneticQuasiPort(modelDescription, map, element);
      }

      for (ForceType element : variableGroups.getForce()) {
        addForce(modelDescription, map, element);
      }

      for (HydraulicPortType element : variableGroups.getHydraulicPort()) {
        addHydraulicPort(modelDescription, map, element);
      }

      for (HydraulicQuasiPortType element : variableGroups.getHydraulicQuasiPort()) {
        addHydraulicQuasiPort(modelDescription, map, element);
      }

      for (LinearDisplacementType element : variableGroups.getLinearDisplacement()) {
        addLinearDisplacement(modelDescription, map, element);
      }

      for (LinearMechanicalPortType element : variableGroups.getLinearMechanicalPort()) {
        addLinearMechanicalPort(modelDescription, map, element);
      }

      for (LinearMechanicalQuasiPortType element : variableGroups.getLinearMechanicalQuasiPort()) {
        addLinearMechanicalQuasiPort(modelDescription, map, element);
      }

      for (LinearVelocityType element : variableGroups.getLinearVelocity()) {
        addLinearVelocity(modelDescription, map, element);
      }

      for (PressureType element : variableGroups.getPressure()) {
        addPressure(modelDescription, map, element);
      }

      for (TorqueType element : variableGroups.getTorque()) {
        addTorque(modelDescription, map, element);
      }

      for (VoltageType element : variableGroups.getVoltage()) {
        addVoltage(modelDescription, map, element);
      }

      for (VolumeType element : variableGroups.getVolume()) {
        addVolume(modelDescription, map, element);
      }

      for (VolumeFlowRateType element : variableGroups.getVolumeFlowRate()) {
        addVolumeFlowRate(modelDescription, map, element);
      }

      for (GenericType element : variableGroups.getGeneric()) {
        addGeneric(modelDescription, map, element);
      }
    }

    return map;
  }

  private void addLinearMechanicalQuasiPort(ModelDescription modelDescription, Map<Object, Object> map, LinearMechanicalQuasiPortType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addForce(modelDescription, map, element.getForce());
      addLinearDisplacement(modelDescription, map, element.getLinearDisplacement());
    }
  }

  private void addLinearMechanicalPort(ModelDescription modelDescription, Map<Object, Object> map, LinearMechanicalPortType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addForce(modelDescription, map, element.getForce());
      addLinearVelocity(modelDescription, map, element.getLinearVelocity());
    }
  }

  private void addHydraulicQuasiPort(ModelDescription modelDescription, Map<Object, Object> map, HydraulicQuasiPortType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addPressure(modelDescription, map, element.getPressure());
      addVolume(modelDescription, map, element.getVolume());
    }
  }

  private void addHydraulicPort(ModelDescription modelDescription, Map<Object, Object> map, HydraulicPortType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addPressure(modelDescription, map, element.getPressure());
      addVolumeFlowRate(modelDescription, map, element.getVolumeFlowRate());
    }
  }

  private void addElectromagneticQuasiPort(ModelDescription modelDescription, Map<Object, Object> map, ElectromagneticQuasiPortType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVoltage(modelDescription, map, element.getVoltage());
      addCharge(modelDescription, map, element.getCharge());
    }
  }

  private void addElectromagneticPort(ModelDescription modelDescription, Map<Object, Object> map, ElectromagneticPortType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVoltage(modelDescription, map, element.getVoltage());
      addCurrent(modelDescription, map, element.getCurrent());
    }
  }

  private void addAngularMechanicalQuasiPort(ModelDescription modelDescription, Map<Object, Object> map, AngularMechanicalQuasiPortType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addTorque(modelDescription, map, element.getTorque());
      addAngularDisplacement(modelDescription, map, element.getAngularDisplacement());
    }
  }

  private void addAngularMechanicalPort(ModelDescription modelDescription, Map<Object, Object> map, AngularMechanicalPortType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addTorque(modelDescription, map, element.getTorque());
      addAngularVelocity(modelDescription, map, element.getAngularVelocity());
    }
  }

  private void addGeneric(ModelDescription modelDescription, Map<Object, Object> map, GenericType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
      element.getGeneric().forEach(vg -> addGeneric(modelDescription, map, vg));
      element.getAngularDisplacement().forEach(vg -> addAngularDisplacement(modelDescription, map, vg));
      element.getAngularMechanicalPort().forEach(vg -> addAngularMechanicalPort(modelDescription, map, vg));
      element.getAngularMechanicalQuasiPort().forEach(vg -> addAngularMechanicalQuasiPort(modelDescription, map, vg));
      element.getAngularVelocity().forEach(vg -> addAngularVelocity(modelDescription, map, vg));
      element.getCharge().forEach(vg -> addCharge(modelDescription, map, vg));
      element.getCurrent().forEach(vg -> addCurrent(modelDescription, map, vg));
      element.getElectromagneticPort().forEach(vg -> addElectromagneticPort(modelDescription, map, vg));
      element.getElectromagneticQuasiPort().forEach(vg -> addElectromagneticQuasiPort(modelDescription, map, vg));
      element.getForce().forEach(vg -> addForce(modelDescription, map, vg));
      element.getHydraulicPort().forEach(vg -> addHydraulicPort(modelDescription, map, vg));
      element.getHydraulicQuasiPort().forEach(vg -> addHydraulicQuasiPort(modelDescription, map, vg));
      element.getLinearDisplacement().forEach(vg -> addLinearDisplacement(modelDescription, map, vg));
      element.getLinearMechanicalPort().forEach(vg -> addLinearMechanicalPort(modelDescription, map, vg));
      element.getLinearMechanicalQuasiPort().forEach(vg -> addLinearMechanicalQuasiPort(modelDescription, map, vg));
      element.getLinearVelocity().forEach(vg -> addLinearVelocity(modelDescription, map, vg));
      element.getPressure().forEach(vg -> addPressure(modelDescription, map, vg));
      element.getTorque().forEach(vg -> addTorque(modelDescription, map, vg));
      element.getVoltage().forEach(vg -> addVoltage(modelDescription, map, vg));
      element.getVolume().forEach(vg -> addVolume(modelDescription, map, vg));
      element.getVolumeFlowRate().forEach(vg -> addVolumeFlowRate(modelDescription, map, vg));
    }
  }

  private void addVolumeFlowRate(ModelDescription modelDescription, Map<Object, Object> map, VolumeFlowRateType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
    }
  }

  private void addVolume(ModelDescription modelDescription, Map<Object, Object> map, VolumeType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
    }
  }

  private void addVoltage(ModelDescription modelDescription, Map<Object, Object> map, VoltageType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
    }
  }

  private void addPressure(ModelDescription modelDescription, Map<Object, Object> map, PressureType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
    }
  }

  private void addLinearVelocity(ModelDescription modelDescription, Map<Object, Object> map, LinearVelocityType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
    }
  }

  private void addLinearDisplacement(ModelDescription modelDescription, Map<Object, Object> map, LinearDisplacementType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
    }
  }

  private void addForce(ModelDescription modelDescription, Map<Object, Object> map, ForceType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
    }
  }

  private void addCurrent(ModelDescription modelDescription, Map<Object, Object> map, CurrentType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
    }
  }

  private void addCharge(ModelDescription modelDescription, Map<Object, Object> map, ChargeType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
    }
  }

  private void addAngularDisplacement(ModelDescription modelDescription, Map<Object, Object> map, AngularDisplacementType angularDisplacementElement) {
    VariableGroup angularDisplacement = ModelDescriptionUtil.getVariableGroupByName(modelDescription, angularDisplacementElement.getName());
    if (angularDisplacement != null) {
      map.put(angularDisplacement, angularDisplacementElement);
      addVariables(modelDescription, map, angularDisplacementElement.getVariable());
    }
  }

  private void addTorque(ModelDescription modelDescription, Map<Object, Object> map, TorqueType torqueElement) {
    VariableGroup torque = ModelDescriptionUtil.getVariableGroupByName(modelDescription, torqueElement.getName());
    if (torque != null) {
      map.put(torque, torqueElement);
      addVariables(modelDescription, map, torqueElement.getVariable());
    }
  }

  private void addAngularVelocity(ModelDescription modelDescription, Map<Object, Object> map, AngularVelocityType element) {
    VariableGroup variableGroup = ModelDescriptionUtil.getVariableGroupByName(modelDescription, element.getName());
    if (variableGroup != null) {
      map.put(variableGroup, element);
      addVariables(modelDescription, map, element.getVariable());
    }
  }

  private void addVariables(ModelDescription modelDescription, Map<Object, Object> map, List<VariableType> variableElements) {
    for (VariableType variableElement : variableElements) {
      Variable variable = ModelDescriptionUtil.getVariableByName(modelDescription, variableElement.getRef());
      if (variable != null) {
        map.put(variable, variableElement);
      }
    }
  }

  private Map<Object, Object> createSystemStructureMap(SystemStructure systemStructure, OspSystemStructure ospSystemStructureElement) {
    Map<Object, Object> map = new HashMap<>();

    List<Simulators.Simulator> simulatorElements = ospSystemStructureElement.getSimulators().getSimulator();

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

    for (Simulators.Simulator simulatorElement : simulatorElements) {
      Optional<Simulator> simulator = SystemStructureUtil.getSimulatorByName(systemStructure, simulatorElement.getName());
      simulator.ifPresent(s -> map.put(s, simulatorElement));
    }

    for (Connections.VariableConnection variableConnectionElement : variableConnectionElements) {
      List<VariableEndpoint> variableEndpointElements = variableConnectionElement.getVariable();
      String variableAName = variableEndpointElements.get(0).getName();
      String simulatorAName = variableEndpointElements.get(0).getSimulator();
      String variableBName = variableEndpointElements.get(1).getName();
      String simulatorBName = variableEndpointElements.get(1).getSimulator();

      Optional<VariableConnection> variableConnection = SystemStructureUtil.getVariableConnectionByPartNames(systemStructure, simulatorAName, variableAName, simulatorBName, variableBName);
      variableConnection.ifPresent(connection -> map.put(connection, variableConnectionElement));
    }

    for (Connections.VariableGroupConnection variableGroupConnectionElement : variableGroupConnectionElements) {
      List<VariableEndpoint> variableEndpointElements = variableGroupConnectionElement.getVariableGroup();
      String variableGroupAName = variableEndpointElements.get(0).getName();
      String simulatorAName = variableEndpointElements.get(0).getSimulator();
      String variableGroupBName = variableEndpointElements.get(1).getName();
      String simulatorBName = variableEndpointElements.get(1).getSimulator();

      Optional<VariableGroupConnection> variableGroupConnection = SystemStructureUtil.getVariableGroupConnectionByPartNames(systemStructure, simulatorAName, variableGroupAName, simulatorBName, variableGroupBName);
      variableGroupConnection.ifPresent(variableConnection -> map.put(variableConnection, variableGroupConnectionElement));
    }

    return map;
  }
}
