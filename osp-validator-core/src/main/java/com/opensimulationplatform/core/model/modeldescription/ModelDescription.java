package com.opensimulationplatform.core.model.modeldescription;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalport.AngularMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.current.Current;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticport.ElectromagneticPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicquasiport.HydraulicQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalport.LinearMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix.NmeaGgaFix;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggalatitudelongitude.NmeaGgaLatitudeLongitude;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatime.NmeaTime;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volume.Volume;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;
import com.opensimulationplatform.core.util.modeldescription.ModelDescriptionUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelDescription {
  private final List<Unit> units = new ArrayList<>();

  private final List<Variable> variables = new ArrayList<>();

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

  private final List<NmeaGgaFix> nmeaGgaFixs = new ArrayList<>();
  private List<NmeaTime> nmeaTimes = new ArrayList<>();
  private List<NmeaGgaLatitudeLongitude> getNmeaGgaLatitudeLongitudes = new ArrayList<>();

  public List<Unit> getUnits() {
    return units;
  }

  public List<Variable> getVariables() {
    return variables;
  }

  public List<VariableGroup> getVariableGroups() {
    return Collections.unmodifiableList(ModelDescriptionUtil.getVariableGroups(this));
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

  public List<NmeaGgaFix> getNmeaGgaFixs() {
    return nmeaGgaFixs;
  }

  public List<NmeaTime> getNmeaTimes() {
    return nmeaTimes;
  }

  public List<NmeaGgaLatitudeLongitude> getNmeaGgaLatitudeLongitudes() {
    return getNmeaGgaLatitudeLongitudes;
  }
}
