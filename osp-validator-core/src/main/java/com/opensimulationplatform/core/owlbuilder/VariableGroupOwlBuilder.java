/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.core.owlbuilder;

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
import com.opensimulationplatform.core.model.modeldescription.variablegroup.generic.Generic;
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
import com.opensimulationplatform.core.model.systemstructure.Simulator;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.opensimulationplatform.gen.owl.model.OntologyClasses.VariableGroup;
import static com.opensimulationplatform.gen.owl.model.OntologyIndividuals.*;
import static com.opensimulationplatform.gen.owl.model.OntologyObjectProperties.*;

public class VariableGroupOwlBuilder extends OspOwlBuilder<VariableGroup> {

  Map<Class<? extends VariableGroup>, String> typeMap = new HashMap<>();

  public VariableGroupOwlBuilder() {
    typeMap.put(LinearAcceleration.class, ind_vgtype_linear_acceleration);
    typeMap.put(NmeaGga.class, ind_vgtype_nmea_gga);
    typeMap.put(ElectromagneticQuasiPort.class, ind_vgtype_electromagnetic_quasi_port);
    typeMap.put(NmeaMwv.class, ind_vgtype_nmea_mwv);
    typeMap.put(Charge.class, ind_vgtype_charge);
    typeMap.put(NmeaStatus.class, ind_vgtype_nmea_status);
    typeMap.put(LinearVelocity.class, ind_vgtype_linear_velocity);
    typeMap.put(NmeaGst.class, ind_vgtype_nmea_gst);
    typeMap.put(NmeaWindSpeed.class, ind_vgtype_nmea_wind_speed);
    typeMap.put(NmeaGgaLatitudeLongitude.class, ind_vgtype_nmea_gga_latitude_longitude);
    typeMap.put(NmeaThs.class, ind_vgtype_nmea_ths);
    typeMap.put(LinearDisplacement.class, ind_vgtype_linear_displacement);
    typeMap.put(LinearMechanicalPort.class, ind_vgtype_linear_mechanical_port);
    typeMap.put(Pressure.class, ind_vgtype_pressure);
    typeMap.put(Current.class, ind_vgtype_current);
    typeMap.put(AngularAcceleration.class, ind_vgtype_angular_acceleration);
    typeMap.put(NmeaTime.class, ind_vgtype_nmea_time);
    typeMap.put(NmeaSxn.class, ind_vgtype_nmea_sxn);
    typeMap.put(BladePitch.class, ind_vgtype_blade_pitch);
    typeMap.put(ElectricPower.class, ind_vgtype_electric_power);
    typeMap.put(LinearMechanicalQuasiPort.class, ind_vgtype_linear_mechanical_quasi_port);
    typeMap.put(HydraulicQuasiPort.class, ind_vgtype_hydraulic_quasi_port);
    typeMap.put(BusFeedback.class, ind_vgtype_bus_feedback);
    typeMap.put(NmeaWindDirection.class, ind_vgtype_nmea_wind_direction);
    typeMap.put(AzimuthAngle.class, ind_vgtype_azimuth_angle);
    typeMap.put(NmeaGstEllipse.class, ind_vgtype_nmea_gst_ellipse);
    typeMap.put(ElectromagneticPort.class, ind_vgtype_electromagnetic_port);
    typeMap.put(GeneratorFeedback.class, ind_vgtype_generator_feedback);
    typeMap.put(Voltage.class, ind_vgtype_voltage);
    typeMap.put(NmeaTrueHeading.class, ind_vgtype_nmea_true_heading);
    typeMap.put(AngularVelocity.class, ind_vgtype_angular_velocity);
    typeMap.put(BatteryFeedback.class, ind_vgtype_battery_feedback);
    typeMap.put(Generic.class, ind_vgtype_generic);
    typeMap.put(AngularMechanicalQuasiPort.class, ind_vgtype_angular_mechanical_quasi_port);
    typeMap.put(NmeaGgaFix.class, ind_vgtype_nmea_gga_fix);
    typeMap.put(HydraulicPort.class, ind_vgtype_hydraulic_port);
    typeMap.put(VolumeFlowRate.class, ind_vgtype_volume_flow_rate);
    typeMap.put(AzimuthThrusterSetpoint.class, ind_vgtype_azimuth_thruster_setpoint);
    typeMap.put(AngularDisplacement.class, ind_vgtype_angular_displacement);
    typeMap.put(FixedThrusterFeedback.class, ind_vgtype_fixed_thruster_feedback);
    typeMap.put(Force.class, ind_vgtype_force);
    typeMap.put(NmeaGstPositionError.class, ind_vgtype_nmea_gst_position_error);
    typeMap.put(ShaftSpeed.class, ind_vgtype_shaft_speed);
    typeMap.put(Torque.class, ind_vgtype_torque);
    typeMap.put(AngularMechanicalPort.class, ind_vgtype_angular_mechanical_port);
    typeMap.put(AzimuthThrusterFeedback.class, ind_vgtype_azimuth_thruster_feedback);
    typeMap.put(Volume.class, ind_vgtype_volume);
    typeMap.put(Frequency.class, ind_vgtype_frequency);
    typeMap.put(FixedThrusterSetpoint.class, ind_vgtype_fixed_thruster_setpoint);
    typeMap.put(NmeaGstRms.class, ind_vgtype_nmea_gst_rms);
  }

  @Override
  public OWLNamedIndividual build(VariableGroup variableGroup) {
    OWLNamedIndividual individual = context.owl.dataFactory.getOWLNamedIndividual(variableGroup.getId().toString(), context.owl.prefixManager);
    context.individuals.add(individual);
    context.variableGroups.put(individual, variableGroup);

    setClass(individual);
    setName(variableGroup, individual);
    setType(variableGroup, individual);
    setVariables(variableGroup, individual);
    setVariableGroups(variableGroup, individual);

    return individual;
  }

  private void setClass(OWLNamedIndividual individual) {
    OWLClass clazz = context.owl.dataFactory.getOWLClass(VariableGroup, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLClassAssertionAxiom(clazz, individual);
    context.axioms.add(axiom);
  }

  private void setName(VariableGroup variableGroup, OWLNamedIndividual individual) {
    NameOwlBuilder nameOwlBuilder = new NameOwlBuilder();
    nameOwlBuilder.setContext(context);
    OWLNamedIndividual nameIndividual = nameOwlBuilder.build(variableGroup.getName());
    OWLObjectProperty hasName = context.owl.dataFactory.getOWLObjectProperty(op_has_name, context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasName, individual, nameIndividual);
    context.axioms.add(axiom);
  }

  private void setType(VariableGroup variableGroup, OWLNamedIndividual individual) {
    OWLObjectProperty hasVariableGroupType = context.owl.dataFactory.getOWLObjectProperty(op_has_variable_group_type, context.owl.prefixManager);
    OWLNamedIndividual type = context.owl.dataFactory.getOWLNamedIndividual(typeMap.get(variableGroup.getClass()), context.owl.prefixManager);
    OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariableGroupType, individual, type);
    context.axioms.add(axiom);
  }

  private void setVariableGroups(VariableGroup variableGroup, OWLNamedIndividual variableGroupIndividual) {
    OWLObjectProperty hasVariableGroup = context.owl.dataFactory.getOWLObjectProperty(op_has_variable_group, context.owl.prefixManager);
    List<VariableGroup> variableGroups = variableGroup.getVariableGroups();
    for (VariableGroup vg : variableGroups) {
      vg.getName().setId(getSimulatorName(vg) + "." + vg.getName().get());
      OWLNamedIndividual vgIndividual = this.build(vg);
      OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariableGroup, variableGroupIndividual, vgIndividual);
      context.axioms.add(axiom);
    }
  }

  private void setVariables(VariableGroup variableGroup, OWLNamedIndividual variableGroupIndividual) {
    VariableOwlBuilder variableOwlBuilder = new VariableOwlBuilder();
    variableOwlBuilder.setContext(context);
    OWLObjectProperty hasVariable = context.owl.dataFactory.getOWLObjectProperty(op_has_variable, context.owl.prefixManager);
    List<Variable> variables = variableGroup.getVariables();
    for (Variable v : variables) {
      v.getName().setId(getSimulatorName(v) + "." + v.getName().get());
      OWLNamedIndividual variableIndividual = variableOwlBuilder.build(v);
      OWLAxiom axiom = context.owl.dataFactory.getOWLObjectPropertyAssertionAxiom(hasVariable, variableGroupIndividual, variableIndividual);
      context.axioms.add(axiom);
    }
  }

  private String getSimulatorName(VariableGroup variableGroup) {
    Stream<Simulator> simulators = context.simulators.values().stream();

    Optional<Simulator> simulator = simulators
        .filter(s -> s.getModelDescription().getVariableGroups().contains(variableGroup))
        .findFirst();

    return simulator.map(value -> value.getName().get()).orElse(null);
  }

  private String getSimulatorName(Variable variable) {
    Stream<Simulator> simulators = context.simulators.values().stream();

    Optional<Simulator> simulator = simulators
        .filter(s -> s.getModelDescription().getVariables().contains(variable))
        .findFirst();

    return simulator.map(value -> value.getName().get()).orElse(null);
  }
}
