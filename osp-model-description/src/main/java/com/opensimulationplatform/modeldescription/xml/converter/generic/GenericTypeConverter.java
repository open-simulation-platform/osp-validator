/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.generic;

import com.opensimulationplatform.core.model.modeldescription.Variable;
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
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.*;

import java.util.List;

public class GenericTypeConverter extends Converter<GenericType, Generic> {

  public GenericTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  @Override
  public Generic convert(GenericType genericType) {
    Generic generic = new Generic();

    generic.setName(genericType.getName());

    List<GenericType> genericTypes = genericType.getGeneric();
    List<Generic> generics = context.genericTypeConverter.convert(genericTypes);
    generic.getGenerics().addAll(generics);
    context.modelDescription.getGenerics().addAll(generics);

    List<VariableType> variable = genericType.getVariable();
    List<Variable> variables = context.variableTypeConverter.convert(variable);
    generic.setVariables(variables);

    List<AngularDisplacementType> angularDisplacement = genericType.getAngularDisplacement();
    List<AngularDisplacement> angularDisplacements = context.angularDisplacementTypeConverter.convert(angularDisplacement);
    generic.getAngularDisplacements().addAll(angularDisplacements);
    context.modelDescription.getAngularDisplacements().addAll(angularDisplacements);

    List<ChargeType> charge = genericType.getCharge();
    List<Charge> charges = context.chargeTypeConverter.convert(charge);
    generic.getCharges().addAll(charges);
    context.modelDescription.getCharges().addAll(charges);

    List<LinearDisplacementType> linearDisplacement = genericType.getLinearDisplacement();
    List<LinearDisplacement> linearDisplacements = context.linearDisplacementTypeConverter.convert(linearDisplacement);
    generic.getLinearDisplacements().addAll(linearDisplacements);
    context.modelDescription.getLinearDisplacements().addAll(linearDisplacements);

    List<VolumeType> volume = genericType.getVolume();
    List<Volume> volumes = context.volumeTypeConverter.convert(volume);
    generic.getVolumes().addAll(volumes);
    context.modelDescription.getVolumes().addAll(volumes);

    List<ForceType> force = genericType.getForce();
    List<Force> forces = context.forceTypeConverter.convert(force);
    generic.getForces().addAll(forces);
    context.modelDescription.getForces().addAll(forces);

    List<PressureType> pressure = genericType.getPressure();
    List<Pressure> pressures = context.pressureTypeConverter.convert(pressure);
    generic.getPressures().addAll(pressures);
    context.modelDescription.getPressures().addAll(pressures);

    List<TorqueType> torque = genericType.getTorque();
    List<Torque> torques = context.torqueTypeConverter.convert(torque);
    generic.getTorques().addAll(torques);
    context.modelDescription.getTorques().addAll(torques);

    List<VoltageType> voltage = genericType.getVoltage();
    List<Voltage> voltages = context.voltageTypeConverter.convert(voltage);
    generic.getVoltages().addAll(voltages);
    context.modelDescription.getVoltages().addAll(voltages);

    List<AngularVelocityType> angularVelocity = genericType.getAngularVelocity();
    List<AngularVelocity> angularVelocities = context.angularVelocityTypeConverter.convert(angularVelocity);
    generic.getAngularVelocities().addAll(angularVelocities);
    context.modelDescription.getAngularVelocities().addAll(angularVelocities);

    List<CurrentType> current = genericType.getCurrent();
    List<Current> currents = context.currentTypeConverter.convert(current);
    generic.getCurrents().addAll(currents);
    context.modelDescription.getCurrents().addAll(currents);

    List<LinearVelocityType> linearVelocity = genericType.getLinearVelocity();
    List<LinearVelocity> linearVelocities = context.linearVelocityTypeConverter.convert(linearVelocity);
    generic.getLinearVelocities().addAll(linearVelocities);
    context.modelDescription.getLinearVelocities().addAll(linearVelocities);

    List<VolumeFlowRateType> volumeFlowRate = genericType.getVolumeFlowRate();
    List<VolumeFlowRate> volumeFlowRates = context.volumeFlowRateTypeConverter.convert(volumeFlowRate);
    generic.getVolumeFlowRates().addAll(volumeFlowRates);
    context.modelDescription.getVolumeFlowRates().addAll(volumeFlowRates);

    List<AngularMechanicalPortType> angularMechanicalPort = genericType.getAngularMechanicalPort();
    List<AngularMechanicalPort> angularMechanicalPorts = context.angularMechanicalPortTypeConverter.convert(angularMechanicalPort);
    generic.getAngularMechanicalPorts().addAll(angularMechanicalPorts);
    context.modelDescription.getAngularMechanicalPorts().addAll(angularMechanicalPorts);

    List<ElectromagneticPortType> electromagneticPort = genericType.getElectromagneticPort();
    List<ElectromagneticPort> electromagneticPorts = context.electromagneticPortTypeConverter.convert(electromagneticPort);
    generic.getElectromagneticPorts().addAll(electromagneticPorts);
    context.modelDescription.getElectromagneticPorts().addAll(electromagneticPorts);

    List<HydraulicPortType> hydraulicPort = genericType.getHydraulicPort();
    List<HydraulicPort> hydraulicPorts = context.hydraulicPortTypeConverter.convert(hydraulicPort);
    generic.getHydraulicPorts().addAll(hydraulicPorts);
    context.modelDescription.getHydraulicPorts().addAll(hydraulicPorts);

    List<LinearMechanicalPortType> linearMechanicalPort = genericType.getLinearMechanicalPort();
    List<LinearMechanicalPort> linearMechanicalPorts = context.linearMechanicalPortTypeConverter.convert(linearMechanicalPort);
    generic.getLinearMechanicalPorts().addAll(linearMechanicalPorts);
    context.modelDescription.getLinearMechanicalPorts().addAll(linearMechanicalPorts);

    List<AngularMechanicalQuasiPortType> angularMechanicalQuasiPort = genericType.getAngularMechanicalQuasiPort();
    List<AngularMechanicalQuasiPort> angularMechanicalQuasiPorts = context.angularMechanicalQuasiPortTypeConverter.convert(angularMechanicalQuasiPort);
    generic.getAngularMechanicalQuasiPorts().addAll(angularMechanicalQuasiPorts);
    context.modelDescription.getAngularMechanicalQuasiPorts().addAll(angularMechanicalQuasiPorts);

    List<ElectromagneticQuasiPortType> electromagneticQuasiPort = genericType.getElectromagneticQuasiPort();
    List<ElectromagneticQuasiPort> electromagneticQuasiPorts = context.electromagneticQuasiPortTypeConverter.convert(electromagneticQuasiPort);
    generic.getElectromagneticQuasiPorts().addAll(electromagneticQuasiPorts);
    context.modelDescription.getElectromagneticQuasiPorts().addAll(electromagneticQuasiPorts);

    List<HydraulicQuasiPortType> hydraulicQuasiPort = genericType.getHydraulicQuasiPort();
    List<HydraulicQuasiPort> hydraulicQuasiPorts = context.hydraulicQuasiPortTypeConverter.convert(hydraulicQuasiPort);
    generic.getHydraulicQuasiPorts().addAll(hydraulicQuasiPorts);
    context.modelDescription.getHydraulicQuasiPorts().addAll(hydraulicQuasiPorts);

    List<LinearMechanicalQuasiPortType> linearMechanicalQuasiPort = genericType.getLinearMechanicalQuasiPort();
    List<LinearMechanicalQuasiPort> linearMechanicalQuasiPorts = context.linearMechanicalQuasiPortTypeConverter.convert(linearMechanicalQuasiPort);
    generic.getLinearMechanicalQuasiPorts().addAll(linearMechanicalQuasiPorts);
    context.modelDescription.getLinearMechanicalQuasiPorts().addAll(linearMechanicalQuasiPorts);

    List<NmeaGgaFixType> nmeaGgaFixTypes = genericType.getNmeaGgaFix();
    List<NmeaGgaFix> nmeaGgaFixes = context.nmeaGgaFixTypeConverter.convert(nmeaGgaFixTypes);
    generic.getNmeaGgaFixs().addAll(nmeaGgaFixes);
    context.modelDescription.getNmeaGgaFixs().addAll(nmeaGgaFixes);

    List<NmeaGgaLatitudeLongitudeType> nmeaGgaLatitudeLongitudeTypes = genericType.getNmeaGgaLatitudeLongitude();
    List<NmeaGgaLatitudeLongitude> nmeaGgaLatitudeLongitude = context.nmeaGgaLatitudeLongitudeTypeConverter.convert(nmeaGgaLatitudeLongitudeTypes);
    generic.getNmeaGgaLatitudeLongitudes().addAll(nmeaGgaLatitudeLongitude);
    context.modelDescription.getNmeaGgaLatitudeLongitudes().addAll(nmeaGgaLatitudeLongitude);

    List<NmeaGgaType> nmeaGgaTypes = genericType.getNmeaGga();
    List<NmeaGga> nmeaGga = context.nmeaGgaTypeConverter.convert(nmeaGgaTypes);
    generic.getNmeaGgas().addAll(nmeaGga);
    context.modelDescription.getNmeaGgas().addAll(nmeaGga);

    List<NmeaGstEllipseType> nmeaGstEllipseTypes = genericType.getNmeaGstEllipse();
    List<NmeaGstEllipse> nmeaGstEllipse = context.nmeaGstEllipseTypeConverter.convert(nmeaGstEllipseTypes);
    generic.getNmeaGstEllipses().addAll(nmeaGstEllipse);
    context.modelDescription.getNmeaGstEllipses().addAll(nmeaGstEllipse);

    List<NmeaGstPositionErrorType> nmeaGstPositionErrorTypes = genericType.getNmeaGstPositionError();
    List<NmeaGstPositionError> nmeaGstPositionError = context.nmeaGstPositionErrorTypeConverter.convert(nmeaGstPositionErrorTypes);
    generic.getNmeaGstPositionErrors().addAll(nmeaGstPositionError);
    context.modelDescription.getNmeaGstPositionErrors().addAll(nmeaGstPositionError);

    List<NmeaGstRmsType> nmeaGstRmsTypes = genericType.getNmeaGstRms();
    List<NmeaGstRms> nmeaGstRms = context.nmeaGstRmsTypeConverter.convert(nmeaGstRmsTypes);
    generic.getNmeaGstRmss().addAll(nmeaGstRms);
    context.modelDescription.getNmeaGstRmss().addAll(nmeaGstRms);

    List<NmeaGstType> nmeaGstTypes = genericType.getNmeaGst();
    List<NmeaGst> nmeaGst = context.nmeaGstTypeConverter.convert(nmeaGstTypes);
    generic.getNmeaGsts().addAll(nmeaGst);
    context.modelDescription.getNmeaGsts().addAll(nmeaGst);

    List<NmeaMwvType> nmeaMwvTypes = genericType.getNmeaMwv();
    List<NmeaMwv> nmeaMwv = context.nmeaMwvTypeConverter.convert(nmeaMwvTypes);
    generic.getNmeaMwvs().addAll(nmeaMwv);
    context.modelDescription.getNmeaMwvs().addAll(nmeaMwv);

    List<NmeaStatusType> nmeaStatusTypes = genericType.getNmeaStatus();
    List<NmeaStatus> nmeaStatus = context.nmeaStatusTypeConverter.convert(nmeaStatusTypes);
    generic.getNmeaStatuses().addAll(nmeaStatus);
    context.modelDescription.getNmeaStatuses().addAll(nmeaStatus);

    List<NmeaSxnType> nmeaSxnTypes = genericType.getNmeaSxn();
    List<NmeaSxn> nmeaSxn = context.nmeaSxnTypeConverter.convert(nmeaSxnTypes);
    generic.getNmeaSxns().addAll(nmeaSxn);
    context.modelDescription.getNmeaSxns().addAll(nmeaSxn);

    List<NmeaThsType> nmeaThsTypes = genericType.getNmeaThs();
    List<NmeaThs> nmeaThs = context.nmeaThsTypeConverter.convert(nmeaThsTypes);
    generic.getNmeaThss().addAll(nmeaThs);
    context.modelDescription.getNmeaThss().addAll(nmeaThs);

    List<NmeaTimeType> nmeaTimeTypes = genericType.getNmeaTime();
    List<NmeaTime> nmeaTime = context.nmeaTimeTypeConverter.convert(nmeaTimeTypes);
    generic.getNmeaTimes().addAll(nmeaTime);
    context.modelDescription.getNmeaTimes().addAll(nmeaTime);

    List<NmeaTrueHeadingType> nmeaTrueHeadingTypes = genericType.getNmeaTrueHeading();
    List<NmeaTrueHeading> nmeaTrueHeading = context.nmeaTrueHeadingTypeConverter.convert(nmeaTrueHeadingTypes);
    generic.getNmeaTrueHeadings().addAll(nmeaTrueHeading);
    context.modelDescription.getNmeaTrueHeadings().addAll(nmeaTrueHeading);

    List<NmeaWindDirectionType> nmeaWindDirectionTypes = genericType.getNmeaWindDirection();
    List<NmeaWindDirection> nmeaWindDirection = context.nmeaWindDirectionTypeConverter.convert(nmeaWindDirectionTypes);
    generic.getNmeaWindDirections().addAll(nmeaWindDirection);
    context.modelDescription.getNmeaWindDirections().addAll(nmeaWindDirection);

    List<NmeaWindSpeedType> nmeaWindSpeedTypes = genericType.getNmeaWindSpeed();
    List<NmeaWindSpeed> nmeaWindSpeed = context.nmeaWindSpeedTypeConverter.convert(nmeaWindSpeedTypes);
    generic.getNmeaWindSpeeds().addAll(nmeaWindSpeed);
    context.modelDescription.getNmeaWindSpeeds().addAll(nmeaWindSpeed);

    List<AngularAccelerationType> angularAccelerationTypes = genericType.getAngularAcceleration();
    List<AngularAcceleration> angularAcceleration = context.angularAccelerationTypeConverter.convert(angularAccelerationTypes);
    generic.getAngularAccelerations().addAll(angularAcceleration);
    context.modelDescription.getAngularAccelerations().addAll(angularAcceleration);

    List<AzimuthAngleType> azimuthAngleTypes = genericType.getAzimuthAngle();
    List<AzimuthAngle> azimuthAngle = context.azimuthAngleTypeConverter.convert(azimuthAngleTypes);
    generic.getAzimuthAngles().addAll(azimuthAngle);
    context.modelDescription.getAzimuthAngles().addAll(azimuthAngle);

    List<AzimuthThrusterFeedbackType> azimuthThrusterFeedbackTypes = genericType.getAzimuthThrusterFeedback();
    List<AzimuthThrusterFeedback> azimuthThrusterFeedback = context.azimuthThrusterFeedbackTypeConverter.convert(azimuthThrusterFeedbackTypes);
    generic.getAzimuthThrusterFeedbacks().addAll(azimuthThrusterFeedback);
    context.modelDescription.getAzimuthThrusterFeedbacks().addAll(azimuthThrusterFeedback);

    List<AzimuthThrusterSetpointType> azimuthThrusterSetpointTypes = genericType.getAzimuthThrusterSetpoint();
    List<AzimuthThrusterSetpoint> azimuthThrusterSetpoint = context.azimuthThrusterSetpointTypeConverter.convert(azimuthThrusterSetpointTypes);
    generic.getAzimuthThrusterSetpoints().addAll(azimuthThrusterSetpoint);
    context.modelDescription.getAzimuthThrusterSetpoints().addAll(azimuthThrusterSetpoint);

    List<BatteryFeedbackType> batteryFeedbackTypes = genericType.getBatteryFeedback();
    List<BatteryFeedback> batteryFeedback = context.batteryFeedbackTypeConverter.convert(batteryFeedbackTypes);
    generic.getBatteryFeedbacks().addAll(batteryFeedback);
    context.modelDescription.getBatteryFeedbacks().addAll(batteryFeedback);

    List<BladePitchType> bladePitchTypes = genericType.getBladePitch();
    List<BladePitch> bladePitch = context.bladePitchTypeConverter.convert(bladePitchTypes);
    generic.getBladePitches().addAll(bladePitch);
    context.modelDescription.getBladePitches().addAll(bladePitch);

    List<BusFeedbackType> busFeedbackTypes = genericType.getBusFeedback();
    List<BusFeedback> busFeedback = context.busFeedbackTypeConverter.convert(busFeedbackTypes);
    generic.getBusFeedbacks().addAll(busFeedback);
    context.modelDescription.getBusFeedbacks().addAll(busFeedback);

    List<ElectricPowerType> electricPowerTypes = genericType.getElectricPower();
    List<ElectricPower> electricPower = context.electricPowerTypeConverter.convert(electricPowerTypes);
    generic.getElectricPowers().addAll(electricPower);
    context.modelDescription.getElectricPowers().addAll(electricPower);

    List<FixedThrusterFeedbackType> fixedThrusterFeedbackTypes = genericType.getFixedThrusterFeedback();
    List<FixedThrusterFeedback> fixedThrusterFeedback = context.fixedThrusterFeedbackTypeConverter.convert(fixedThrusterFeedbackTypes);
    generic.getFixedThrusterFeedbacks().addAll(fixedThrusterFeedback);
    context.modelDescription.getFixedThrusterFeedbacks().addAll(fixedThrusterFeedback);

    List<FixedThrusterSetpointType> fixedThrusterSetpointTypes = genericType.getFixedThrusterSetpoint();
    List<FixedThrusterSetpoint> fixedThrusterSetpoint = context.fixedThrusterSetpointTypeConverter.convert(fixedThrusterSetpointTypes);
    generic.getFixedThrusterSetpoints().addAll(fixedThrusterSetpoint);
    context.modelDescription.getFixedThrusterSetpoints().addAll(fixedThrusterSetpoint);

    List<FrequencyType> frequencyTypes = genericType.getFrequency();
    List<Frequency> frequency = context.frequencyTypeConverter.convert(frequencyTypes);
    generic.getFrequencies().addAll(frequency);
    context.modelDescription.getFrequencies().addAll(frequency);

    List<GeneratorFeedbackType> generatorFeedbackTypes = genericType.getGeneratorFeedback();
    List<GeneratorFeedback> generatorFeedback = context.generatorFeedbackTypeConverter.convert(generatorFeedbackTypes);
    generic.getGeneratorFeedbacks().addAll(generatorFeedback);
    context.modelDescription.getGeneratorFeedbacks().addAll(generatorFeedback);

    List<LinearAccelerationType> linearAccelerationTypes = genericType.getLinearAcceleration();
    List<LinearAcceleration> linearAcceleration = context.linearAccelerationTypeConverter.convert(linearAccelerationTypes);
    generic.getLinearAccelerations().addAll(linearAcceleration);
    context.modelDescription.getLinearAccelerations().addAll(linearAcceleration);

    List<ShaftSpeedType> shaftSpeedTypes = genericType.getShaftSpeed();
    List<ShaftSpeed> shaftSpeed = context.shaftSpeedTypeConverter.convert(shaftSpeedTypes);
    generic.getShaftSpeeds().addAll(shaftSpeed);
    context.modelDescription.getShaftSpeeds().addAll(shaftSpeed);

    return generic;
  }
}
