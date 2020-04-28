package com.opensimulationplatform.core.model.modeldescription.variablegroup.generic;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.Charge;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.LinearDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.displacement.Volume;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.effort.Voltage;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.AngularVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.Current;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.LinearVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.flow.VolumeFlowRate;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.AngularMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.ElectromagneticPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.HydraulicPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.port.LinearMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport.HydraulicQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.quasiport.LinearMechanicalQuasiPort;

import java.util.ArrayList;
import java.util.List;

public class Generic extends VariableGroup {

  private final List<Generic> generics = new ArrayList<>();

  private final List<AngularDisplacement> angularDisplacements = new ArrayList<>();
  private final List<Charge> charges = new ArrayList<>();
  private final List<LinearDisplacement> linearDisplacements = new ArrayList<>();
  private final List<Volume> volumes = new ArrayList<>();

  private final List<Force> forces = new ArrayList<>();
  private final List<Pressure> pressures = new ArrayList<>();
  private final List<Torque> torques = new ArrayList<>();
  private final List<Voltage> voltages = new ArrayList<>();

  private final List<AngularVelocity> angularVelocities = new ArrayList<>();
  private final List<Current> currents = new ArrayList<>();
  private final List<LinearVelocity> linearVelocities = new ArrayList<>();
  private final List<VolumeFlowRate> volumeFlowRates = new ArrayList<>();

  private final List<AngularMechanicalPort> angularMechanicalPorts = new ArrayList<>();
  private final List<ElectromagneticPort> electromagneticPorts = new ArrayList<>();
  private final List<HydraulicPort> hydraulicPorts = new ArrayList<>();
  private final List<LinearMechanicalPort> linearMechanicalPorts = new ArrayList<>();

  private final List<AngularMechanicalQuasiPort> angularMechanicalQuasiPorts = new ArrayList<>();
  private final List<ElectromagneticQuasiPort> electromagneticQuasiPorts = new ArrayList<>();
  private final List<HydraulicQuasiPort> hydraulicQuasiPorts = new ArrayList<>();
  private final List<LinearMechanicalQuasiPort> linearMechanicalQuasiPorts = new ArrayList<>();

  @Override
  public void setVariables(List<Variable> variables) {
    this.variables = variables;
  }

  @Override
  public List<VariableGroup> getVariableGroups() {
    List<VariableGroup> variableGroups = new ArrayList<>();

    variableGroups.addAll(generics);
    variableGroups.addAll(angularDisplacements);
    variableGroups.addAll(charges);
    variableGroups.addAll(linearDisplacements);
    variableGroups.addAll(volumes);
    variableGroups.addAll(forces);
    variableGroups.addAll(pressures);
    variableGroups.addAll(torques);
    variableGroups.addAll(voltages);
    variableGroups.addAll(angularVelocities);
    variableGroups.addAll(currents);
    variableGroups.addAll(linearVelocities);
    variableGroups.addAll(volumeFlowRates);
    variableGroups.addAll(angularMechanicalPorts);
    variableGroups.addAll(electromagneticPorts);
    variableGroups.addAll(hydraulicPorts);
    variableGroups.addAll(linearMechanicalPorts);
    variableGroups.addAll(angularMechanicalQuasiPorts);
    variableGroups.addAll(electromagneticQuasiPorts);
    variableGroups.addAll(hydraulicQuasiPorts);
    variableGroups.addAll(linearMechanicalQuasiPorts);

    return variableGroups;
  }

  public List<Generic> getGenerics() {
    return generics;
  }

  public List<AngularDisplacement> getAngularDisplacements() {
    return angularDisplacements;
  }

  public List<Charge> getCharges() {
    return charges;
  }

  public List<LinearDisplacement> getLinearDisplacements() {
    return linearDisplacements;
  }

  public List<Volume> getVolumes() {
    return volumes;
  }

  public List<Force> getForces() {
    return forces;
  }

  public List<Pressure> getPressures() {
    return pressures;
  }

  public List<Torque> getTorques() {
    return torques;
  }

  public List<Voltage> getVoltages() {
    return voltages;
  }

  public List<AngularVelocity> getAngularVelocities() {
    return angularVelocities;
  }

  public List<Current> getCurrents() {
    return currents;
  }

  public List<LinearVelocity> getLinearVelocities() {
    return linearVelocities;
  }

  public List<VolumeFlowRate> getVolumeFlowRates() {
    return volumeFlowRates;
  }

  public List<AngularMechanicalPort> getAngularMechanicalPorts() {
    return angularMechanicalPorts;
  }

  public List<ElectromagneticPort> getElectromagneticPorts() {
    return electromagneticPorts;
  }

  public List<HydraulicPort> getHydraulicPorts() {
    return hydraulicPorts;
  }

  public List<LinearMechanicalPort> getLinearMechanicalPorts() {
    return linearMechanicalPorts;
  }

  public List<AngularMechanicalQuasiPort> getAngularMechanicalQuasiPorts() {
    return angularMechanicalQuasiPorts;
  }

  public List<ElectromagneticQuasiPort> getElectromagneticQuasiPorts() {
    return electromagneticQuasiPorts;
  }

  public List<HydraulicQuasiPort> getHydraulicQuasiPorts() {
    return hydraulicQuasiPorts;
  }

  public List<LinearMechanicalQuasiPort> getLinearMechanicalQuasiPorts() {
    return linearMechanicalQuasiPorts;
  }
}
