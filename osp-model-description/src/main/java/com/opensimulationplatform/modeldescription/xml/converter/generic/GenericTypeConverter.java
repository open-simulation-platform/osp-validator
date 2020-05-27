package com.opensimulationplatform.modeldescription.xml.converter.generic;

import com.opensimulationplatform.core.model.modeldescription.Variable;
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
import com.opensimulationplatform.core.model.modeldescription.variablegroup.pressure.Pressure;
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

    return generic;
  }
}
