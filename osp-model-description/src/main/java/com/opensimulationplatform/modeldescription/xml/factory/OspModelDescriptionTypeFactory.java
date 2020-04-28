package com.opensimulationplatform.modeldescription.xml.factory;

import com.opensimulationplatform.core.model.modeldescription.ModelDescription;
import com.opensimulationplatform.core.model.modeldescription.Unit;
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
import com.opensimulationplatform.modeldescription.xml.model.*;

import java.util.ArrayList;
import java.util.List;

public class OspModelDescriptionTypeFactory {
  public OspModelDescriptionType create(ModelDescription modelDescription) {
    OspModelDescriptionType ospModelDescriptionType = new OspModelDescriptionType();

    ospModelDescriptionType.setVersion(ospModelDescriptionType.getVersion());
    ospModelDescriptionType.setUnitDefinitions(getUnitDefinitionsType(modelDescription));
    ospModelDescriptionType.setVariableGroups(getVariableGroupsType(modelDescription));

    return ospModelDescriptionType;
  }

  private VariableGroupsType getVariableGroupsType(ModelDescription modelDescription) {
    VariableGroupsType variableGroups = new VariableGroupsType();
    List<ForceType> forceTypes = variableGroups.getForce();
    for (Force force : modelDescription.getForces()) {
      forceTypes.add(getForceType(force));
    }

    List<LinearVelocityType> linearVelocityTypes = variableGroups.getLinearVelocity();
    for (LinearVelocity linearVelocity : modelDescription.getLinearVelocities()) {
      linearVelocityTypes.add(getLinearVelocityType(linearVelocity));
    }

    List<LinearDisplacementType> linearDisplacementTypes = variableGroups.getLinearDisplacement();
    for (LinearDisplacement linearDisplacement : modelDescription.getLinearDisplacements()) {
      linearDisplacementTypes.add(getLinearDisplacementType(linearDisplacement));
    }

    List<TorqueType> torqueTypes = variableGroups.getTorque();
    for (Torque torque : modelDescription.getTorques()) {
      torqueTypes.add(getTorqueType(torque));
    }

    List<AngularVelocityType> angularVelocityTypes = variableGroups.getAngularVelocity();
    for (AngularVelocity angularVelocity : modelDescription.getAngularVelocities()) {
      angularVelocityTypes.add(getAngularVelocityType(angularVelocity));
    }

    List<AngularDisplacementType> angularDisplacementTypes = variableGroups.getAngularDisplacement();
    for (AngularDisplacement angularDisplacement : modelDescription.getAngularDisplacements()) {
      angularDisplacementTypes.add(getAngularDisplacementType(angularDisplacement));
    }

    List<VoltageType> voltageTypes = variableGroups.getVoltage();
    for (Voltage voltage : modelDescription.getVoltages()) {
      voltageTypes.add(getVoltageType(voltage));
    }

    List<CurrentType> currentTypes = variableGroups.getCurrent();
    for (Current current : modelDescription.getCurrents()) {
      currentTypes.add(getCurrentType(current));
    }

    List<ChargeType> chargeTypes = variableGroups.getCharge();
    for (Charge charge : modelDescription.getCharges()) {
      chargeTypes.add(getChargeType(charge));
    }

    List<PressureType> pressureTypes = variableGroups.getPressure();
    for (Pressure pressure : modelDescription.getPressures()) {
      pressureTypes.add(getPressureType(pressure));
    }

    List<VolumeFlowRateType> volumeFlowRateTypes = variableGroups.getVolumeFlowRate();
    for (VolumeFlowRate volumeFlowRate : modelDescription.getVolumeFlowRates()) {
      volumeFlowRateTypes.add(getVolumeFlowRateType(volumeFlowRate));
    }

    List<VolumeType> volumeTypes = variableGroups.getVolume();
    for (Volume volume : modelDescription.getVolumes()) {
      volumeTypes.add(getVolumeType(volume));
    }

    List<LinearMechanicalPortType> linearMechanicalPortTypes = variableGroups.getLinearMechanicalPort();
    for (LinearMechanicalPort linearMechanicalPort : modelDescription.getLinearMechanicalPorts()) {
      linearMechanicalPortTypes.add(getLinearMechanicalPortType(linearMechanicalPort));
    }

    List<AngularMechanicalPortType> angularMechanicalPortTypes = variableGroups.getAngularMechanicalPort();
    for (AngularMechanicalPort angularMechanicalPort : modelDescription.getAngularMechanicalPorts()) {
      angularMechanicalPortTypes.add(getAngularMechanicalPortType(angularMechanicalPort));
    }

    List<ElectromagneticPortType> electromagneticPortTypes = variableGroups.getElectromagneticPort();
    for (ElectromagneticPort electromagneticPort : modelDescription.getElectromagneticPorts()) {
      electromagneticPortTypes.add(getElectromagneticPortType(electromagneticPort));
    }

    List<HydraulicPortType> hydraulicPortTypes = variableGroups.getHydraulicPort();
    for (HydraulicPort hydraulicPort : modelDescription.getHydraulicPorts()) {
      hydraulicPortTypes.add(getHydraulicPortType(hydraulicPort));
    }

    List<LinearMechanicalQuasiPortType> linearMechanicalQuasiPortTypes = variableGroups.getLinearMechanicalQuasiPort();
    for (LinearMechanicalQuasiPort linearMechanicalQuasiPort : modelDescription.getLinearMechanicalQuasiPorts()) {
      linearMechanicalQuasiPortTypes.add(getLinearMechanicalQuasiPortType(linearMechanicalQuasiPort));
    }

    List<AngularMechanicalQuasiPortType> angularMechanicalQuasiPortTypes = variableGroups.getAngularMechanicalQuasiPort();
    for (AngularMechanicalQuasiPort angularMechanicalQuasiPort : modelDescription.getAngularMechanicalQuasiPorts()) {
      angularMechanicalQuasiPortTypes.add(getAngularMechanicalQuasiPortType(angularMechanicalQuasiPort));
    }

    List<ElectromagneticQuasiPortType> electromagneticQuasiPortTypes = variableGroups.getElectromagneticQuasiPort();
    for (ElectromagneticQuasiPort electromagneticQuasiPort : modelDescription.getElectromagneticQuasiPorts()) {
      electromagneticQuasiPortTypes.add(getElectromagneticQuasiPortType(electromagneticQuasiPort));
    }

    List<HydraulicQuasiPortType> hydraulicQuasiPortTypes = variableGroups.getHydraulicQuasiPort();
    for (HydraulicQuasiPort hydraulicQuasiPort : modelDescription.getHydraulicQuasiPorts()) {
      hydraulicQuasiPortTypes.add(getHydraulicQuasiPortType(hydraulicQuasiPort));
    }
    return variableGroups;
  }

  private UnitDefinitionsType getUnitDefinitionsType(ModelDescription modelDescription) {
    UnitDefinitionsType unitDefinitions = new UnitDefinitionsType();
    List<UnitType> unitTypes = unitDefinitions.getUnit();
    for (Unit unit : modelDescription.getUnits()) {
      unitTypes.add(getUnitType(unit));
    }
    return unitDefinitions;
  }

  private UnitType getUnitType(Unit unit) {
    UnitType unitType = new UnitType();
    unitType.setName(unit.getName().get());
    Fmi2Unit.BaseUnit baseUnit = new Fmi2Unit.BaseUnit();
    baseUnit.setA(unit.getExponent(Unit.Exponent.AMPERE));
    baseUnit.setCd(unit.getExponent(Unit.Exponent.CANDELA));
    baseUnit.setK(unit.getExponent(Unit.Exponent.KELVIN));
    baseUnit.setKg(unit.getExponent(Unit.Exponent.KILOGRAM));
    baseUnit.setM(unit.getExponent(Unit.Exponent.METER));
    baseUnit.setMol(unit.getExponent(Unit.Exponent.MOL));
    baseUnit.setRad(unit.getExponent(Unit.Exponent.RADIAN));
    baseUnit.setS(unit.getExponent(Unit.Exponent.SECOND));
    baseUnit.setFactor(unit.getFactor());
    baseUnit.setOffset(unit.getOffset());
    unitType.setBaseUnit(baseUnit);
    return unitType;
  }

  private LinearMechanicalPortType getLinearMechanicalPortType(LinearMechanicalPort linearMechanicalPort) {
    LinearMechanicalPortType linearMechanicalPortType = new LinearMechanicalPortType();
    linearMechanicalPortType.setName(linearMechanicalPort.getName().get());
    linearMechanicalPortType.setForce(getForceType(linearMechanicalPort.getForce()));
    linearMechanicalPortType.setLinearVelocity(getLinearVelocityType(linearMechanicalPort.getLinearVelocity()));
    return linearMechanicalPortType;
  }

  private AngularMechanicalPortType getAngularMechanicalPortType(AngularMechanicalPort angularMechanicalPort) {
    AngularMechanicalPortType angularMechanicalPortType = new AngularMechanicalPortType();
    angularMechanicalPortType.setName(angularMechanicalPort.getName().get());
    angularMechanicalPortType.setTorque(getTorqueType(angularMechanicalPort.getTorque()));
    angularMechanicalPortType.setAngularVelocity(getAngularVelocityType(angularMechanicalPort.getAngularVelocity()));
    return angularMechanicalPortType;
  }

  private ElectromagneticPortType getElectromagneticPortType(ElectromagneticPort electromagneticPort) {
    ElectromagneticPortType electromagneticPortType = new ElectromagneticPortType();
    electromagneticPortType.setName(electromagneticPort.getName().get());
    electromagneticPortType.setVoltage(getVoltageType(electromagneticPort.getVoltage()));
    electromagneticPortType.setCurrent(getCurrentType(electromagneticPort.getCurrent()));
    return electromagneticPortType;
  }

  private HydraulicPortType getHydraulicPortType(HydraulicPort hydraulicPort) {
    HydraulicPortType hydraulicPortType = new HydraulicPortType();
    hydraulicPortType.setName(hydraulicPort.getName().get());
    hydraulicPortType.setPressure(getPressureType(hydraulicPort.getPressure()));
    hydraulicPortType.setVolumeFlowRate(getVolumeFlowRateType(hydraulicPort.getVolumeFlowRate()));
    return hydraulicPortType;
  }

  private LinearMechanicalQuasiPortType getLinearMechanicalQuasiPortType(LinearMechanicalQuasiPort linearMechanicalQuasiPort) {
    LinearMechanicalQuasiPortType linearMechanicalQuasiPortType = new LinearMechanicalQuasiPortType();
    linearMechanicalQuasiPortType.setName(linearMechanicalQuasiPort.getName().get());
    linearMechanicalQuasiPortType.setForce(getForceType(linearMechanicalQuasiPort.getForce()));
    linearMechanicalQuasiPortType.setLinearDisplacement(getLinearDisplacementType(linearMechanicalQuasiPort.getLinearDisplacement()));
    return linearMechanicalQuasiPortType;
  }

  private AngularMechanicalQuasiPortType getAngularMechanicalQuasiPortType(AngularMechanicalQuasiPort angularMechanicalQuasiPort) {
    AngularMechanicalQuasiPortType angularMechanicalQuasiPortType = new AngularMechanicalQuasiPortType();
    angularMechanicalQuasiPortType.setName(angularMechanicalQuasiPort.getName().get());
    angularMechanicalQuasiPortType.setTorque(getTorqueType(angularMechanicalQuasiPort.getTorque()));
    angularMechanicalQuasiPortType.setAngularDisplacement(getAngularDisplacementType(angularMechanicalQuasiPort.getAngularDisplacement()));
    return angularMechanicalQuasiPortType;
  }

  private ElectromagneticQuasiPortType getElectromagneticQuasiPortType(ElectromagneticQuasiPort electromagneticQuasiPort) {
    ElectromagneticQuasiPortType electromagneticQuasiPortType = new ElectromagneticQuasiPortType();
    electromagneticQuasiPortType.setName(electromagneticQuasiPort.getName().get());
    electromagneticQuasiPortType.setVoltage(getVoltageType(electromagneticQuasiPort.getVoltage()));
    electromagneticQuasiPortType.setCharge(getChargeType(electromagneticQuasiPort.getCharge()));
    return electromagneticQuasiPortType;
  }

  private HydraulicQuasiPortType getHydraulicQuasiPortType(HydraulicQuasiPort hydraulicQuasiPort) {
    HydraulicQuasiPortType hydraulicQuasiPortType = new HydraulicQuasiPortType();
    hydraulicQuasiPortType.setName(hydraulicQuasiPort.getName().get());
    hydraulicQuasiPortType.setPressure(getPressureType(hydraulicQuasiPort.getPressure()));
    hydraulicQuasiPortType.setVolume(getVolumeType(hydraulicQuasiPort.getVolume()));
    return hydraulicQuasiPortType;
  }

  private ForceType getForceType(Force force) {
    ForceType forceType = new ForceType();
    forceType.setName(force.getName().get());
    forceType.getVariable().addAll(getVariableTypes(force));
    return forceType;
  }

  private LinearVelocityType getLinearVelocityType(LinearVelocity linearVelocity) {
    LinearVelocityType linearVelocityType = new LinearVelocityType();
    linearVelocityType.setName(linearVelocity.getName().get());
    linearVelocityType.getVariable().addAll(getVariableTypes(linearVelocity));
    return linearVelocityType;
  }

  private LinearDisplacementType getLinearDisplacementType(LinearDisplacement linearDisplacement) {
    LinearDisplacementType linearDisplacementType = new LinearDisplacementType();
    linearDisplacementType.setName(linearDisplacement.getName().get());
    linearDisplacementType.getVariable().addAll(getVariableTypes(linearDisplacement));
    return linearDisplacementType;
  }

  private TorqueType getTorqueType(Torque torque) {
    TorqueType torqueType = new TorqueType();
    torqueType.setName(torque.getName().get());
    torqueType.getVariable().addAll(getVariableTypes(torque));
    return torqueType;
  }

  private AngularVelocityType getAngularVelocityType(AngularVelocity angularVelocity) {
    AngularVelocityType angularVelocityType = new AngularVelocityType();
    angularVelocityType.setName(angularVelocity.getName().get());
    angularVelocityType.getVariable().addAll(getVariableTypes(angularVelocity));
    return angularVelocityType;
  }

  private AngularDisplacementType getAngularDisplacementType(AngularDisplacement angularDisplacement) {
    AngularDisplacementType angularDisplacementType = new AngularDisplacementType();
    angularDisplacementType.setName(angularDisplacement.getName().get());
    angularDisplacementType.getVariable().addAll(getVariableTypes(angularDisplacement));
    return angularDisplacementType;
  }

  private VoltageType getVoltageType(Voltage voltage) {
    VoltageType voltageType = new VoltageType();
    voltageType.setName(voltage.getName().get());
    voltageType.getVariable().addAll(getVariableTypes(voltage));
    return voltageType;
  }

  private CurrentType getCurrentType(Current current) {
    CurrentType currentType = new CurrentType();
    currentType.setName(current.getName().get());
    currentType.getVariable().addAll(getVariableTypes(current));
    return currentType;
  }

  private ChargeType getChargeType(Charge charge) {
    ChargeType chargeType = new ChargeType();
    chargeType.setName(charge.getName().get());
    chargeType.getVariable().addAll(getVariableTypes(charge));
    return chargeType;
  }

  private PressureType getPressureType(Pressure pressure) {
    PressureType pressureType = new PressureType();
    pressureType.setName(pressure.getName().get());
    pressureType.getVariable().addAll(getVariableTypes(pressure));
    return pressureType;
  }

  private VolumeFlowRateType getVolumeFlowRateType(VolumeFlowRate volumeFlowRate) {
    VolumeFlowRateType volumeFlowRateType = new VolumeFlowRateType();
    volumeFlowRateType.setName(volumeFlowRate.getName().get());
    volumeFlowRateType.getVariable().addAll(getVariableTypes(volumeFlowRate));
    return volumeFlowRateType;
  }

  private VolumeType getVolumeType(Volume volume) {
    VolumeType volumeType = new VolumeType();
    volumeType.setName(volume.getName().get());
    volumeType.getVariable().addAll(getVariableTypes(volume));
    return volumeType;
  }

  private List<VariableType> getVariableTypes(VariableGroup variableGroup) {
    List<Variable> variables = variableGroup.getVariables();
    List<VariableType> variableTypes = new ArrayList<>();
    for (Variable variable : variables) {
      VariableType variableType = new VariableType();
      variableType.setRef(variable.getName().get());
      variableType.setUnit(variable.getUnit().getName().get());
      variableTypes.add(variableType);
    }
    return variableTypes;
  }
}
