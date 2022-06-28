/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.opensimulationplatform.modeldescription.xml.converter.ospmodeldescriptiontype;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
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
import com.opensimulationplatform.modeldescription.util.FmuHelper_Fmi1;
import com.opensimulationplatform.modeldescription.util.FmuHelper_Fmi2;
import com.opensimulationplatform.modeldescription.xml.converter.Converter;
import com.opensimulationplatform.modeldescription.xml.converter.ConverterContext;
import com.opensimulationplatform.modeldescription.xml.model.*;
import no.ntnu.ihb.fmi4j.modeldescription.util.FmiModelDescriptionUtil;

import java.net.URI;
import java.util.List;

public class OspModelDescriptionTypeConverter extends Converter<OspModelDescriptionType, ModelDescription> {

  public OspModelDescriptionTypeConverter(ConverterContext converterContext) {
    super(converterContext);
  }

  public OspModelDescriptionTypeConverter(URI fmu) {
	try {
		String fmiVersion = FmiModelDescriptionUtil.extractVersion(fmu.toURL());
		if ("1.0".equals(fmiVersion)) {
			context.fmiModelDescription = context.fmi1ModelDescriptionConverter.convert(FmuHelper_Fmi1.getFmiModelDescription(fmu));
		} else if ("2.0".equals(fmiVersion)) {
			context.fmiModelDescription = context.fmi2ModelDescriptionConverter.convert(FmuHelper_Fmi2.getFmiModelDescription(fmu));
		} else {
			throw new RuntimeException("fmiVersion " + fmiVersion + " not supported");
		}
	} catch (Exception e) {
		throw new RuntimeException(e);
	}
  }

  @Override
  public ModelDescription convert(OspModelDescriptionType ospModelDescriptionType) {
    ModelDescription modelDescription = context.modelDescription;

    modelDescription.getVariables().addAll(context.fmiModelDescription.getVariables());
    modelDescription.getUnits().addAll(context.fmiModelDescription.getUnits());

    UnitDefinitionsType unitDefinitions = ospModelDescriptionType.getUnitDefinitions();
    if (unitDefinitions != null) {
      List<UnitType> unitTypes = unitDefinitions.getUnit();
      List<Unit> units = context.unitTypeConverter.convert(unitTypes);
      modelDescription.getUnits().addAll(units);
    }

    VariableGroupsType variableGroups = ospModelDescriptionType.getVariableGroups();
    if (variableGroups != null) {
      List<GenericType> genericTypes = variableGroups.getGeneric();
      List<Generic> generics = context.genericTypeConverter.convert(genericTypes);
      modelDescription.getGenerics().addAll(generics);

      List<AngularDisplacementType> angularDisplacementTypes = variableGroups.getAngularDisplacement();
      List<AngularDisplacement> angularDisplacements = context.angularDisplacementTypeConverter.convert(angularDisplacementTypes);
      modelDescription.getAngularDisplacements().addAll(angularDisplacements);

      List<ChargeType> chargeTypes = variableGroups.getCharge();
      List<Charge> charges = context.chargeTypeConverter.convert(chargeTypes);
      modelDescription.getCharges().addAll(charges);

      List<LinearDisplacementType> linearDisplacementTypes = variableGroups.getLinearDisplacement();
      List<LinearDisplacement> linearDisplacements = context.linearDisplacementTypeConverter.convert(linearDisplacementTypes);
      modelDescription.getLinearDisplacements().addAll(linearDisplacements);

      List<VolumeType> volumeTypes = variableGroups.getVolume();
      List<Volume> volumes = context.volumeTypeConverter.convert(volumeTypes);
      modelDescription.getVolumes().addAll(volumes);

      List<ForceType> forceTypes = variableGroups.getForce();
      List<Force> forces = context.forceTypeConverter.convert(forceTypes);
      modelDescription.getForces().addAll(forces);

      List<PressureType> pressureTypes = variableGroups.getPressure();
      List<Pressure> pressures = context.pressureTypeConverter.convert(pressureTypes);
      modelDescription.getPressures().addAll(pressures);

      List<TorqueType> torqueTypes = variableGroups.getTorque();
      List<Torque> torques = context.torqueTypeConverter.convert(torqueTypes);
      modelDescription.getTorques().addAll(torques);

      List<VoltageType> voltageTypes = variableGroups.getVoltage();
      List<Voltage> voltages = context.voltageTypeConverter.convert(voltageTypes);
      modelDescription.getVoltages().addAll(voltages);

      List<AngularVelocityType> angularVelocityTypes = variableGroups.getAngularVelocity();
      List<AngularVelocity> angularVelocities = context.angularVelocityTypeConverter.convert(angularVelocityTypes);
      modelDescription.getAngularVelocities().addAll(angularVelocities);

      List<CurrentType> currentTypes = variableGroups.getCurrent();
      List<Current> currents = context.currentTypeConverter.convert(currentTypes);
      modelDescription.getCurrents().addAll(currents);

      List<LinearVelocityType> linearVelocityTypes = variableGroups.getLinearVelocity();
      List<LinearVelocity> linearVelocities = context.linearVelocityTypeConverter.convert(linearVelocityTypes);
      modelDescription.getLinearVelocities().addAll(linearVelocities);

      List<VolumeFlowRateType> volumeFlowRateTypes = variableGroups.getVolumeFlowRate();
      List<VolumeFlowRate> volumeFlowRates = context.volumeFlowRateTypeConverter.convert(volumeFlowRateTypes);
      modelDescription.getVolumeFlowRates().addAll(volumeFlowRates);

      List<AngularMechanicalPortType> angularMechanicalPortTypes = variableGroups.getAngularMechanicalPort();
      List<AngularMechanicalPort> angularMechanicalPorts = context.angularMechanicalPortTypeConverter.convert(angularMechanicalPortTypes);
      modelDescription.getAngularMechanicalPorts().addAll(angularMechanicalPorts);

      List<ElectromagneticPortType> electromagneticPortTypes = variableGroups.getElectromagneticPort();
      List<ElectromagneticPort> electromagneticPorts = context.electromagneticPortTypeConverter.convert(electromagneticPortTypes);
      modelDescription.getElectromagneticPorts().addAll(electromagneticPorts);

      List<HydraulicPortType> hydraulicPortTypes = variableGroups.getHydraulicPort();
      List<HydraulicPort> hydraulicPorts = context.hydraulicPortTypeConverter.convert(hydraulicPortTypes);
      modelDescription.getHydraulicPorts().addAll(hydraulicPorts);

      List<LinearMechanicalPortType> linearMechanicalPortTypes = variableGroups.getLinearMechanicalPort();
      List<LinearMechanicalPort> linearMechanicalPorts = context.linearMechanicalPortTypeConverter.convert(linearMechanicalPortTypes);
      modelDescription.getLinearMechanicalPorts().addAll(linearMechanicalPorts);

      List<AngularMechanicalQuasiPortType> angularMechanicalQuasiPortTypes = variableGroups.getAngularMechanicalQuasiPort();
      List<AngularMechanicalQuasiPort> angularMechanicalQuasiPorts = context.angularMechanicalQuasiPortTypeConverter.convert(angularMechanicalQuasiPortTypes);
      modelDescription.getAngularMechanicalQuasiPorts().addAll(angularMechanicalQuasiPorts);

      List<ElectromagneticQuasiPortType> electromagneticQuasiPortTypes = variableGroups.getElectromagneticQuasiPort();
      List<ElectromagneticQuasiPort> electromagneticQuasiPorts = context.electromagneticQuasiPortTypeConverter.convert(electromagneticQuasiPortTypes);
      modelDescription.getElectromagneticQuasiPorts().addAll(electromagneticQuasiPorts);

      List<HydraulicQuasiPortType> hydraulicQuasiPortTypes = variableGroups.getHydraulicQuasiPort();
      List<HydraulicQuasiPort> hydraulicQuasiPorts = context.hydraulicQuasiPortTypeConverter.convert(hydraulicQuasiPortTypes);
      modelDescription.getHydraulicQuasiPorts().addAll(hydraulicQuasiPorts);

      List<LinearMechanicalQuasiPortType> linearMechanicalQuasiPortTypes = variableGroups.getLinearMechanicalQuasiPort();
      List<LinearMechanicalQuasiPort> linearMechanicalQuasiPorts = context.linearMechanicalQuasiPortTypeConverter.convert(linearMechanicalQuasiPortTypes);
      modelDescription.getLinearMechanicalQuasiPorts().addAll(linearMechanicalQuasiPorts);

      List<NmeaGgaFixType> nmeaGgaFixTypes = variableGroups.getNmeaGgaFix();
      List<NmeaGgaFix> nmeaGgaFixes = context.nmeaGgaFixTypeConverter.convert(nmeaGgaFixTypes);
      modelDescription.getNmeaGgaFixs().addAll(nmeaGgaFixes);

      List<NmeaGgaLatitudeLongitudeType> nmeaGgaLatitudeLongitudeTypes = variableGroups.getNmeaGgaLatitudeLongitude();
      List<NmeaGgaLatitudeLongitude> nmeaGgaLatitudeLongitude = context.nmeaGgaLatitudeLongitudeTypeConverter.convert(nmeaGgaLatitudeLongitudeTypes);
      modelDescription.getNmeaGgaLatitudeLongitudes().addAll(nmeaGgaLatitudeLongitude);

      List<NmeaGgaType> nmeaGgaTypes = variableGroups.getNmeaGga();
      List<NmeaGga> nmeaGga = context.nmeaGgaTypeConverter.convert(nmeaGgaTypes);
      modelDescription.getNmeaGgas().addAll(nmeaGga);

      List<NmeaGstEllipseType> nmeaGstEllipseTypes = variableGroups.getNmeaGstEllipse();
      List<NmeaGstEllipse> nmeaGstEllipse = context.nmeaGstEllipseTypeConverter.convert(nmeaGstEllipseTypes);
      modelDescription.getNmeaGstEllipses().addAll(nmeaGstEllipse);

      List<NmeaGstPositionErrorType> nmeaGstPositionErrorTypes = variableGroups.getNmeaGstPositionError();
      List<NmeaGstPositionError> nmeaGstPositionError = context.nmeaGstPositionErrorTypeConverter.convert(nmeaGstPositionErrorTypes);
      modelDescription.getNmeaGstPositionErrors().addAll(nmeaGstPositionError);

      List<NmeaGstRmsType> nmeaGstRmsTypes = variableGroups.getNmeaGstRms();
      List<NmeaGstRms> nmeaGstRms = context.nmeaGstRmsTypeConverter.convert(nmeaGstRmsTypes);
      modelDescription.getNmeaGstRmss().addAll(nmeaGstRms);

      List<NmeaGstType> nmeaGstTypes = variableGroups.getNmeaGst();
      List<NmeaGst> nmeaGst = context.nmeaGstTypeConverter.convert(nmeaGstTypes);
      modelDescription.getNmeaGsts().addAll(nmeaGst);

      List<NmeaMwvType> nmeaMwvTypes = variableGroups.getNmeaMwv();
      List<NmeaMwv> nmeaMwv = context.nmeaMwvTypeConverter.convert(nmeaMwvTypes);
      modelDescription.getNmeaMwvs().addAll(nmeaMwv);

      List<NmeaStatusType> nmeaStatusTypes = variableGroups.getNmeaStatus();
      List<NmeaStatus> nmeaStatus = context.nmeaStatusTypeConverter.convert(nmeaStatusTypes);
      modelDescription.getNmeaStatuses().addAll(nmeaStatus);

      List<NmeaSxnType> nmeaSxnTypes = variableGroups.getNmeaSxn();
      List<NmeaSxn> nmeaSxn = context.nmeaSxnTypeConverter.convert(nmeaSxnTypes);
      modelDescription.getNmeaSxns().addAll(nmeaSxn);

      List<NmeaThsType> nmeaThsTypes = variableGroups.getNmeaThs();
      List<NmeaThs> nmeaThs = context.nmeaThsTypeConverter.convert(nmeaThsTypes);
      modelDescription.getNmeaThss().addAll(nmeaThs);

      List<NmeaTimeType> nmeaTimeTypes = variableGroups.getNmeaTime();
      List<NmeaTime> nmeaTime = context.nmeaTimeTypeConverter.convert(nmeaTimeTypes);
      modelDescription.getNmeaTimes().addAll(nmeaTime);

      List<NmeaTrueHeadingType> nmeaTrueHeadingTypes = variableGroups.getNmeaTrueHeading();
      List<NmeaTrueHeading> nmeaTrueHeading = context.nmeaTrueHeadingTypeConverter.convert(nmeaTrueHeadingTypes);
      modelDescription.getNmeaTrueHeadings().addAll(nmeaTrueHeading);

      List<NmeaWindDirectionType> nmeaWindDirectionTypes = variableGroups.getNmeaWindDirection();
      List<NmeaWindDirection> nmeaWindDirection = context.nmeaWindDirectionTypeConverter.convert(nmeaWindDirectionTypes);
      modelDescription.getNmeaWindDirections().addAll(nmeaWindDirection);

      List<NmeaWindSpeedType> nmeaWindSpeedTypes = variableGroups.getNmeaWindSpeed();
      List<NmeaWindSpeed> nmeaWindSpeed = context.nmeaWindSpeedTypeConverter.convert(nmeaWindSpeedTypes);
      modelDescription.getNmeaWindSpeeds().addAll(nmeaWindSpeed);

      List<AngularAccelerationType> angularAccelerationTypes = variableGroups.getAngularAcceleration();
      List<AngularAcceleration> angularAcceleration = context.angularAccelerationTypeConverter.convert(angularAccelerationTypes);
      modelDescription.getAngularAccelerations().addAll(angularAcceleration);

      List<AzimuthAngleType> azimuthAngleTypes = variableGroups.getAzimuthAngle();
      List<AzimuthAngle> azimuthAngle = context.azimuthAngleTypeConverter.convert(azimuthAngleTypes);
      modelDescription.getAzimuthAngles().addAll(azimuthAngle);

      List<AzimuthThrusterFeedbackType> azimuthThrusterFeedbackTypes = variableGroups.getAzimuthThrusterFeedback();
      List<AzimuthThrusterFeedback> azimuthThrusterFeedback = context.azimuthThrusterFeedbackTypeConverter.convert(azimuthThrusterFeedbackTypes);
      modelDescription.getAzimuthThrusterFeedbacks().addAll(azimuthThrusterFeedback);

      List<AzimuthThrusterSetpointType> azimuthThrusterSetpointTypes = variableGroups.getAzimuthThrusterSetpoint();
      List<AzimuthThrusterSetpoint> azimuthThrusterSetpoint = context.azimuthThrusterSetpointTypeConverter.convert(azimuthThrusterSetpointTypes);
      modelDescription.getAzimuthThrusterSetpoints().addAll(azimuthThrusterSetpoint);

      List<BatteryFeedbackType> batteryFeedbackTypes = variableGroups.getBatteryFeedback();
      List<BatteryFeedback> batteryFeedback = context.batteryFeedbackTypeConverter.convert(batteryFeedbackTypes);
      modelDescription.getBatteryFeedbacks().addAll(batteryFeedback);

      List<BladePitchType> bladePitchTypes = variableGroups.getBladePitch();
      List<BladePitch> bladePitch = context.bladePitchTypeConverter.convert(bladePitchTypes);
      modelDescription.getBladePitches().addAll(bladePitch);

      List<BusFeedbackType> busFeedbackTypes = variableGroups.getBusFeedback();
      List<BusFeedback> busFeedback = context.busFeedbackTypeConverter.convert(busFeedbackTypes);
      modelDescription.getBusFeedbacks().addAll(busFeedback);

      List<ElectricPowerType> electricPowerTypes = variableGroups.getElectricPower();
      List<ElectricPower> electricPower = context.electricPowerTypeConverter.convert(electricPowerTypes);
      modelDescription.getElectricPowers().addAll(electricPower);

      List<FixedThrusterFeedbackType> fixedThrusterFeedbackTypes = variableGroups.getFixedThrusterFeedback();
      List<FixedThrusterFeedback> fixedThrusterFeedback = context.fixedThrusterFeedbackTypeConverter.convert(fixedThrusterFeedbackTypes);
      modelDescription.getFixedThrusterFeedbacks().addAll(fixedThrusterFeedback);

      List<FixedThrusterSetpointType> fixedThrusterSetpointTypes = variableGroups.getFixedThrusterSetpoint();
      List<FixedThrusterSetpoint> fixedThrusterSetpoint = context.fixedThrusterSetpointTypeConverter.convert(fixedThrusterSetpointTypes);
      modelDescription.getFixedThrusterSetpoints().addAll(fixedThrusterSetpoint);

      List<FrequencyType> frequencyTypes = variableGroups.getFrequency();
      List<Frequency> frequency = context.frequencyTypeConverter.convert(frequencyTypes);
      modelDescription.getFrequencies().addAll(frequency);

      List<GeneratorFeedbackType> generatorFeedbackTypes = variableGroups.getGeneratorFeedback();
      List<GeneratorFeedback> generatorFeedback = context.generatorFeedbackTypeConverter.convert(generatorFeedbackTypes);
      modelDescription.getGeneratorFeedbacks().addAll(generatorFeedback);

      List<LinearAccelerationType> linearAccelerationTypes = variableGroups.getLinearAcceleration();
      List<LinearAcceleration> linearAcceleration = context.linearAccelerationTypeConverter.convert(linearAccelerationTypes);
      modelDescription.getLinearAccelerations().addAll(linearAcceleration);

      List<ShaftSpeedType> shaftSpeedTypes = variableGroups.getShaftSpeed();
      List<ShaftSpeed> shaftSpeed = context.shaftSpeedTypeConverter.convert(shaftSpeedTypes);
      modelDescription.getShaftSpeeds().addAll(shaftSpeed);
    }

    return modelDescription;
  }
}
