package com.opensimulationplatform.core.model.modeldescription.variablegroup.generic;

import com.opensimulationplatform.core.model.modeldescription.Variable;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularacceleration.AngularAcceleration;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angulardisplacement.AngularDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalport.AngularMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.angularvelocity.AngularVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.azimuthangle.AzimuthAngle;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.azimuththrusterfeedback.AzimuthThrusterFeedback;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.azimuththrustersetpoint.AzimuthThrusterSetpoint;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.batteryfeedback.BatteryFeedback;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.bladepitch.BladePitch;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.busfeedback.BusFeedback;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.charge.Charge;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.current.Current;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electricpower.ElectricPower;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticport.ElectromagneticPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.electromagneticquasiport.ElectromagneticQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedthrusterfeedback.FixedThrusterFeedback;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.fixedthrustersetpoint.FixedThrusterSetpoint;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.force.Force;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.frequency.Frequency;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generatorfeedback.GeneratorFeedback;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicport.HydraulicPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.hydraulicquasiport.HydraulicQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearacceleration.LinearAcceleration;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.lineardisplacement.LinearDisplacement;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalport.LinearMechanicalPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPort;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.linearvelocity.LinearVelocity;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagga.NmeaGga;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggafix.NmeaGgaFix;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaggalatitudelongitude.NmeaGgaLatitudeLongitude;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagst.NmeaGst;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstellipse.NmeaGstEllipse;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstpositionerror.NmeaGstPositionError;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeagstrms.NmeaGstRms;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeamwv.NmeaMwv;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeastatus.NmeaStatus;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeasxn.NmeaSxn;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeaths.NmeaThs;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatime.NmeaTime;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeatrueheading.NmeaTrueHeading;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawinddirection.NmeaWindDirection;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.nmeawindspeed.NmeaWindSpeed;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.shaftspeed.ShaftSpeed;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.torque.Torque;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.voltage.Voltage;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volume.Volume;
import com.opensimulationplatform.core.model.modeldescription.variablegroup.volumeflowrate.VolumeFlowRate;

import java.util.ArrayList;
import java.util.Collections;
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

  private final List<NmeaGgaFix> nmeaGgaFixs = new ArrayList<>();
  private final List<NmeaTime> nmeaTimes = new ArrayList<>();
  private final List<NmeaGgaLatitudeLongitude> nmeaGgaLatitudeLongitudes = new ArrayList<>();
  private final List<NmeaGstEllipse> nmeaGstEllipses = new ArrayList<>();
  private final List<NmeaGstPositionError> nmeaGstPositionErrors = new ArrayList<>();
  private final List<NmeaGstRms> nmeaGstRmss = new ArrayList<>();
  private final List<NmeaStatus> nmeaStatuses = new ArrayList<>();
  private final List<NmeaWindDirection> nmeaWindDirections = new ArrayList<>();
  private final List<NmeaWindSpeed> nmeaWindSpeeds = new ArrayList<>();
  private final List<NmeaTrueHeading> nmeaTrueHeadings = new ArrayList<>();
  private final List<AzimuthAngle> azimuthAngles = new ArrayList<>();
  private final List<BladePitch> bladePitches = new ArrayList<>();
  private final List<ShaftSpeed> shaftSpeeds = new ArrayList<>();
  private final List<ElectricPower> electricPowers = new ArrayList<>();
  private final List<Frequency> frequencies = new ArrayList<>();
  private final List<NmeaGga> nmeaGgas = new ArrayList<>();
  private final List<NmeaGst> nmeaGsts = new ArrayList<>();
  private final List<NmeaMwv> nmeaMwvs = new ArrayList<>();
  private final List<NmeaSxn> nmeaSxns = new ArrayList<>();
  private final List<NmeaThs> nmeaThss = new ArrayList<>();
  private final List<AngularAcceleration> angularAccelerations = new ArrayList<>();
  private final List<AzimuthThrusterFeedback> azimuthThrusterFeedbacks = new ArrayList<>();
  private final List<AzimuthThrusterSetpoint> azimuthThrusterSetpoints = new ArrayList<>();
  private final List<BatteryFeedback> batteryFeedbacks = new ArrayList<>();
  private final List<BusFeedback> busFeedbacks = new ArrayList<>();
  private final List<FixedThrusterFeedback> fixedThrusterFeedbacks = new ArrayList<>();
  private final List<FixedThrusterSetpoint> fixedThrusterSetpoints = new ArrayList<>();
  private final List<GeneratorFeedback> generatorFeedbacks = new ArrayList<>();
  private final List<LinearAcceleration> linearAccelerations = new ArrayList<>();

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
    variableGroups.addAll(nmeaGgaFixs);
    variableGroups.addAll(nmeaTimes);
    variableGroups.addAll(nmeaGgaLatitudeLongitudes);
    variableGroups.addAll(nmeaGstEllipses);
    variableGroups.addAll(nmeaGstPositionErrors);
    variableGroups.addAll(nmeaGstRmss);
    variableGroups.addAll(nmeaStatuses);
    variableGroups.addAll(nmeaWindDirections);
    variableGroups.addAll(nmeaWindSpeeds);
    variableGroups.addAll(nmeaTrueHeadings);
    variableGroups.addAll(azimuthAngles);
    variableGroups.addAll(bladePitches);
    variableGroups.addAll(shaftSpeeds);
    variableGroups.addAll(electricPowers);
    variableGroups.addAll(frequencies);
    variableGroups.addAll(nmeaGgas);
    variableGroups.addAll(nmeaGsts);
    variableGroups.addAll(nmeaMwvs);
    variableGroups.addAll(nmeaSxns);
    variableGroups.addAll(nmeaThss);
    variableGroups.addAll(angularAccelerations);
    variableGroups.addAll(azimuthThrusterFeedbacks);
    variableGroups.addAll(azimuthThrusterSetpoints);
    variableGroups.addAll(batteryFeedbacks);
    variableGroups.addAll(busFeedbacks);
    variableGroups.addAll(fixedThrusterFeedbacks);
    variableGroups.addAll(fixedThrusterSetpoints);
    variableGroups.addAll(generatorFeedbacks);
    variableGroups.addAll(linearAccelerations);

    return Collections.unmodifiableList(variableGroups);
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
    return nmeaGgaLatitudeLongitudes;
  }

  public List<NmeaGstEllipse> getNmeaGstEllipses() {
    return nmeaGstEllipses;
  }

  public List<NmeaGstPositionError> getNmeaGstPositionErrors() {
    return nmeaGstPositionErrors;
  }

  public List<NmeaGstRms> getNmeaGstRmss() {
    return nmeaGstRmss;
  }

  public List<NmeaStatus> getNmeaStatuses() {
    return nmeaStatuses;
  }

  public List<NmeaWindDirection> getNmeaWindDirections() {
    return nmeaWindDirections;
  }

  public List<NmeaWindSpeed> getNmeaWindSpeeds() {
    return nmeaWindSpeeds;
  }

  public List<NmeaTrueHeading> getNmeaTrueHeadings() {
    return nmeaTrueHeadings;
  }

  public List<AzimuthAngle> getAzimuthAngles() {
    return azimuthAngles;
  }

  public List<BladePitch> getBladePitches() {
    return bladePitches;
  }

  public List<ShaftSpeed> getShaftSpeeds() {
    return shaftSpeeds;
  }

  public List<ElectricPower> getElectricPowers() {
    return electricPowers;
  }

  public List<Frequency> getFrequencies() {
    return frequencies;
  }

  public List<NmeaGga> getNmeaGgas() {
    return nmeaGgas;
  }

  public List<NmeaGst> getNmeaGsts() {
    return nmeaGsts;
  }

  public List<NmeaMwv> getNmeaMwvs() {
    return nmeaMwvs;
  }

  public List<NmeaSxn> getNmeaSxns() {
    return nmeaSxns;
  }

  public List<NmeaThs> getNmeaThss() {
    return nmeaThss;
  }

  public List<AngularAcceleration> getAngularAccelerations() {
    return angularAccelerations;
  }

  public List<AzimuthThrusterFeedback> getAzimuthThrusterFeedbacks() {
    return azimuthThrusterFeedbacks;
  }

  public List<AzimuthThrusterSetpoint> getAzimuthThrusterSetpoints() {
    return azimuthThrusterSetpoints;
  }

  public List<BatteryFeedback> getBatteryFeedbacks() {
    return batteryFeedbacks;
  }

  public List<BusFeedback> getBusFeedbacks() {
    return busFeedbacks;
  }

  public List<FixedThrusterFeedback> getFixedThrusterFeedbacks() {
    return fixedThrusterFeedbacks;
  }

  public List<FixedThrusterSetpoint> getFixedThrusterSetpoints() {
    return fixedThrusterSetpoints;
  }

  public List<GeneratorFeedback> getGeneratorFeedbacks() {
    return generatorFeedbacks;
  }

  public List<LinearAcceleration> getLinearAccelerations() {
    return linearAccelerations;
  }
}
