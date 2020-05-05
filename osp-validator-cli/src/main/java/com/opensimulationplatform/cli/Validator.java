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
import java.util.*;

public class Validator {
  public List<String> validate(File ospSystemStructureFile) {
    Map<Object, Object> coreToJaxb = new HashMap<>();
    Map<Object, Location> locations = new HashMap<>();
    List<ValidationDiagnostic<Object>> diagnostics = new ArrayList<>();

    OspSystemStructureParser parser = new OspSystemStructureParser();
    OspSystemStructure ospSystemStructureElement = parser.parse(ospSystemStructureFile);
    locations.putAll(parser.getLocations());

    addModelDescriptionStuff(ospSystemStructureFile, ospSystemStructureElement, coreToJaxb, locations, diagnostics);
    addSystemStructureStuff(ospSystemStructureFile, coreToJaxb, diagnostics, ospSystemStructureElement);

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

    return errorMessages;
  }

  private void addSystemStructureStuff(File ospSystemStructureFile, Map<Object, Object> coreToJaxb, List<ValidationDiagnostic<Object>> diagnostics, OspSystemStructure ospSystemStructureElement) {
    SystemStructureFactory factory = new SystemStructureFactory();
    SystemStructure systemStructure = factory.create(ospSystemStructureFile);

    SystemStructureValidator validator = new SystemStructureValidator();
    diagnostics.addAll(validator.validate(systemStructure));

    addSystemStructureObjects(systemStructure, ospSystemStructureElement, coreToJaxb);
  }

  private void addModelDescriptionStuff(File ospSystemStructureFile, OspSystemStructure ospSystemStructureElement, Map<Object, Object> map, Map<Object, Location> locations, List<ValidationDiagnostic<Object>> diagnostics) {
    for (Simulators.Simulator simulator : ospSystemStructureElement.getSimulators().getSimulator()) {
      FmuLocator fmuLocator = new DefaultFmuLocator(ospSystemStructureFile);
      OspModelDescriptionLocator ospModelDescriptionLocator = new DefaultOspModelDescriptionLocator(ospSystemStructureFile, fmuLocator);
      File ospModelDescriptionXml = ospModelDescriptionLocator.get(simulator);
      OspModelDescriptionParser parser = new OspModelDescriptionParser();
      OspModelDescriptionType ospModelDescriptionElement = parser.parse(ospModelDescriptionXml);
      locations.putAll(parser.getLocations());

      ModelDescriptionFactory factory = new ModelDescriptionFactory();
      ModelDescription modelDescription = factory.create(ospModelDescriptionXml, fmuLocator.get(simulator));

      ModelDescriptionValidator validator = new ModelDescriptionValidator();
      diagnostics.addAll(validator.validate(modelDescription));

      map.putAll(createModelDescriptionMap(ospModelDescriptionElement, modelDescription));
    }
  }

  private Map<Object, Object> createModelDescriptionMap(OspModelDescriptionType ospModelDescriptionElement, ModelDescription modelDescription) {
    Map<Object, Object> map = new HashMap<>();


    for (AngularDisplacementType element : ospModelDescriptionElement.getVariableGroups().getAngularDisplacement()) {
      addAngularDisplacement(modelDescription, map, element);
    }

    for (AngularMechanicalPortType element : ospModelDescriptionElement.getVariableGroups().getAngularMechanicalPort()) {
      addAngularMechanicalPort(modelDescription, map, element);
    }

    for (AngularMechanicalQuasiPortType element : ospModelDescriptionElement.getVariableGroups().getAngularMechanicalQuasiPort()) {
      addAngularMechanicalQuasiPort(modelDescription, map, element);
    }

    for (AngularVelocityType element : ospModelDescriptionElement.getVariableGroups().getAngularVelocity()) {
      addAngularVelocity(modelDescription, map, element);
    }

    for (ChargeType element : ospModelDescriptionElement.getVariableGroups().getCharge()) {
      addCharge(modelDescription, map, element);
    }

    for (CurrentType element : ospModelDescriptionElement.getVariableGroups().getCurrent()) {
      addCurrent(modelDescription, map, element);
    }

    for (ElectromagneticPortType element : ospModelDescriptionElement.getVariableGroups().getElectromagneticPort()) {
      addElectromagneticPort(modelDescription, map, element);
    }

    for (ElectromagneticQuasiPortType element : ospModelDescriptionElement.getVariableGroups().getElectromagneticQuasiPort()) {
      addElectromagneticQuasiPort(modelDescription, map, element);
    }

    for (ForceType element : ospModelDescriptionElement.getVariableGroups().getForce()) {
      addForce(modelDescription, map, element);
    }

    for (HydraulicPortType element : ospModelDescriptionElement.getVariableGroups().getHydraulicPort()) {
      addHydraulicPort(modelDescription, map, element);
    }

    for (HydraulicQuasiPortType element : ospModelDescriptionElement.getVariableGroups().getHydraulicQuasiPort()) {
      addHydraulicQuasiPort(modelDescription, map, element);
    }

    for (LinearDisplacementType element : ospModelDescriptionElement.getVariableGroups().getLinearDisplacement()) {
      addLinearDisplacement(modelDescription, map, element);
    }

    for (LinearMechanicalPortType element : ospModelDescriptionElement.getVariableGroups().getLinearMechanicalPort()) {
      addLinearMechanicalPort(modelDescription, map, element);
    }

    for (LinearMechanicalQuasiPortType element : ospModelDescriptionElement.getVariableGroups().getLinearMechanicalQuasiPort()) {
      addLinearMechanicalQuasiPort(modelDescription, map, element);
    }

    for (LinearVelocityType element : ospModelDescriptionElement.getVariableGroups().getLinearVelocity()) {
      addLinearVelocity(modelDescription, map, element);
    }

    for (PressureType element : ospModelDescriptionElement.getVariableGroups().getPressure()) {
      addPressure(modelDescription, map, element);
    }

    for (TorqueType element : ospModelDescriptionElement.getVariableGroups().getTorque()) {
      addTorque(modelDescription, map, element);
    }

    for (VoltageType element : ospModelDescriptionElement.getVariableGroups().getVoltage()) {
      addVoltage(modelDescription, map, element);
    }

    for (VolumeType element : ospModelDescriptionElement.getVariableGroups().getVolume()) {
      addVolume(modelDescription, map, element);
    }

    for (VolumeFlowRateType element : ospModelDescriptionElement.getVariableGroups().getVolumeFlowRate()) {
      addVolumeFlowRate(modelDescription, map, element);
    }

    for (GenericType element : ospModelDescriptionElement.getVariableGroups().getGeneric()) {
      addGeneric(modelDescription, map, element);
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

  private void addSystemStructureObjects(SystemStructure systemStructure, OspSystemStructure ospSystemStructureElement, Map<Object, Object> map) {
    List<Simulators.Simulator> simulatorElements = ospSystemStructureElement.getSimulators().getSimulator();
    List<Connections.VariableConnection> variableConnectionElements = ospSystemStructureElement.getConnections().getVariableConnection();
    List<Connections.VariableGroupConnection> variableGroupConnectionElements = ospSystemStructureElement.getConnections().getVariableGroupConnection();

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
  }
}
